package com.dpktech.accounts.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.dpktech.accounts.constants.AccountsConstants;
import com.dpktech.accounts.dto.AccountsDto;
import com.dpktech.accounts.dto.CustomerDto;
import com.dpktech.accounts.entity.Accounts;
import com.dpktech.accounts.entity.Customer;
import com.dpktech.accounts.exception.CustomerAlreadyExistException;
import com.dpktech.accounts.exception.ResourceNotFoundException;
import com.dpktech.accounts.mapper.AccountsMapper;
import com.dpktech.accounts.mapper.CustomerMapper;
import com.dpktech.accounts.repository.AccountsRepository;
import com.dpktech.accounts.repository.CustomerRepository;
import com.dpktech.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;

	/**
	 * 
	 */
	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
//		Customer optionlCustomer=customerRepository.findByMobileNumber(customerDto.getMobileNumber()).orElseThrow(()-> new CustomerAlreadyExistException("Customer already registered with given mobile number"+customerDto.getMobileNumber()));
		Optional<Customer> optionlCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		if (optionlCustomer.isPresent()) {
			throw new CustomerAlreadyExistException(
					"Customer already registered with given mobile number " + customerDto.getMobileNumber());
		}
//		customer.setCreatedAt(LocalDateTime.now());
//		customer.setCreatedBy("Anonyms");
		Customer savedCustomer = customerRepository.save(customer);
		accountsRepository.save(createNewAccount(savedCustomer));
	}

	private Accounts createNewAccount(Customer customer) {
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
//		newAccount.setCreatedAt(LocalDateTime.now());
//		newAccount.setCreatedBy("Anonyms");

		return newAccount;
	}

	/**
	 * 
	 * @param mobileNumber -Input mobile Number
	 * @return Accounts Details based on a given mobile number
	 */

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
		// TODO Auto-generated method stub
		return customerDto;
	}

	/**
	 * 
	 * @param CustomerDto
	 * @return boolean indicating if the update of Account details is successful or
	 *         not
	 */

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		boolean isUpdated = false;
		AccountsDto accountsDto = customerDto.getAccountsDto();
		if (accountsDto != null) {
			Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
					.orElseThrow(() -> new ResourceNotFoundException("Account", "Account Number",
							accountsDto.getAccountNumber().toString()));

			AccountsMapper.mapToAccounts(accountsDto, accounts);
			accounts = accountsRepository.save(accounts);

			Long customerId = accounts.getCustomerId();
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString()));

			CustomerMapper.mapToCustomer(customerDto, customer);
			customerRepository.save(customer);
			isUpdated=true;
		}
		// TODO Auto-generated method stub
		return isUpdated;
	}
	
	/**
	 * 
	 * @param Mobile Number
	 * @return boolean indicating if the deletion of Account details is successful or not
	 */


	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer= customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		accountsRepository.deleteByCustomerId(customer.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());
		return true;
	}

}
