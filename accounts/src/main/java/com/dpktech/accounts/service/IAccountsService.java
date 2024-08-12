package com.dpktech.accounts.service;

import com.dpktech.accounts.dto.CustomerDto;

public interface IAccountsService {
	
	/**
	 * 
	 * @param customerDto - CustomerDto Object
	 */
	void createAccount(CustomerDto customerDto);
	
	/**
	 * 
	 * @param mobileNumber
	 * @return
	 */
	CustomerDto fetchAccount(String mobileNumber);
	

}
