package com.dpktech.cards.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.dpktech.cards.constants.CardsConstants;
import com.dpktech.cards.dto.CardsDto;
import com.dpktech.cards.entity.Cards;
import com.dpktech.cards.exception.CardAlreadyExistsException;
import com.dpktech.cards.exception.ResourceNotFoundException;
import com.dpktech.cards.mapper.CardsMapper;
import com.dpktech.cards.repository.CardsRepository;
import com.dpktech.cards.service.ICardsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService{
	
	private CardsRepository cardsRepository;

	/**
     * @param mobileNumber - Mobile Number of the Customer
     */
	@Override
	public void createCard(String mobileNumber) {

//		Cards card= cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new CardAlreadyExistsException("Card already registered with given mobile number "+mobileNumber));
//		cardsRepository.save(createNewCard(mobileNumber));
		
		Optional<Cards> optionalCards= cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
	}
	
	/**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
	private Cards createNewCard(String mobileNumber) {
		Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
	}

	/**
    *
    * @param mobileNumber - Input mobile Number
    * @return Card Details based on a given mobileNumber
    */
	@Override
	public CardsDto fetchCard(String mobileNumber) {
		Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardsMapper.mapToCardsDto(cards, new CardsDto());
	}

	/**
    *
    * @param cardsDto - CardsDto Object
    * @return boolean indicating if the update of card details is successful or not
    */
   @Override
   public boolean updateCard(CardsDto cardsDto) {
       Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
               () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
       CardsMapper.mapToCards(cardsDto, cards);
       cardsRepository.save(cards);
       return  true;
   }

   /**
    * @param mobileNumber - Input MobileNumber
    * @return boolean indicating if the delete of card details is successful or not
    */
   @Override
   public boolean deleteCard(String mobileNumber) {
       Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
               () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
       );
       cardsRepository.deleteById(cards.getCardId());
       return true;
   }
	
	

}
