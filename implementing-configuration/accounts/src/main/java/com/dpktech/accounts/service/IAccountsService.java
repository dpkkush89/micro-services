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
	
	/**
	 * 
	 * @param CustomerDto
	 * @return boolean indicating if the update of Account details is successful or not
	 */
	boolean updateAccount(CustomerDto customerDto);
	
	/**
	 * 
	 * @param Mobile Number
	 * @return boolean indicating if the deletion of Account details is successful or not
	 */
	boolean deleteAccount(String mobileNumber);
	
	
	

}
