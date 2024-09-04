package com.dpktech.loans.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s not found with given input data %s : '%s'", resourceName, fieldName, fieldValue));
	}

}
