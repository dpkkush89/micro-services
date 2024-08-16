package com.dpktech.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoanAlreadyExistException(String message) {
		super(message);
	}
	
	

}
