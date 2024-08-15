package com.dpktech.accounts.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dpktech.accounts.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Handles Error Response whenever any field validation failed E If user gives
	 * any invalid value the this method handle the input validation error
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> validationErrors = new HashMap<>();

		// below lines gives all validation errors failed in the input which is received
		List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

		/**
		 * Iterating each error and getting the field name and validation failed message
		 * for each field
		 */
		validationErrorList.forEach((error) -> {
			String filedName = ((FieldError) error).getField();
			String validationMsg = error.getDefaultMessage();

			// putting each error and message in HashMap
			validationErrors.put(filedName, validationMsg);
		});

		/**
		 * sending the validation error as part of response entity with status as Bad
		 * Request
		 */
		return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest request) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(request.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), LocalDateTime.now());

		return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomerAlreadyExistException.class)
	public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistException(CustomerAlreadyExistException exception,
			WebRequest request) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(request.getDescription(false), HttpStatus.BAD_REQUEST,
				exception.getMessage(), LocalDateTime.now());

		return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest request) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(request.getDescription(false), HttpStatus.NOT_FOUND,
				exception.getMessage(), LocalDateTime.now());

		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	}

}
