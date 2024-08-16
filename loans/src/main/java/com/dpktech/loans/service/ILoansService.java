package com.dpktech.loans.service;

import com.dpktech.loans.Dto.LoansDto;

public interface ILoansService {
	
	
	/**
	 * 
	 * @param mobileNumber - Mobile Number of the customer
	 */
	void createLoan(String mobileNumber);
	
	/**
	 * 
	 * @param mobileNumber - Input Mobile Number
	 * @return Loan Details based on a given mobile number
	 */
	LoansDto fetchLoan(String mobileNumber);
	
	/**
	 * 
	 * @param loansDto -  LoansDto Object
	 * @return boolean indicating is the update of Loan details is  or not
	 */
	boolean updateLoan(LoansDto loansDto);
	
	/**
	 * 
	 * @param mobileNumber - Input Mobile Number
	 * @return
	 */
	boolean deleteLoan(String mobileNumber);

}
