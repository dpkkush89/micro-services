package com.dpktech.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dpktech.accounts.constants.AccountsConstants;
import com.dpktech.accounts.dto.CustomerDto;
import com.dpktech.accounts.dto.ResponseDto;
import com.dpktech.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountsController {

	private IAccountsService iAccountsService;

	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
		
		iAccountsService.createAccount(customerDto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));

	}
//	@GetMapping("/fetch/{mobileNumber}")
//	public ResponseEntity<CustomerDto> fetchAccountsDetails(@PathVariable String mobileNumber){
//		
//		CustomerDto customerDto =iAccountsService.fetchAccount(mobileNumber);
//		
//		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
//
//	}
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountsDetails(@RequestParam String mobileNumber){
		
		CustomerDto customerDto =iAccountsService.fetchAccount(mobileNumber);
		
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);

	}

}
