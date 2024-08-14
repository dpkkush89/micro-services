package com.dpktech.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AccountsDto {
	
	@NotEmpty(message = "AccountNumber cannot be null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})",message = "Account number must be 10 digits")
	private Long accountNumber;
	
	@NotEmpty(message = "Account Type cannot be null or empty")
	private String accountType;

	@NotEmpty(message = "Branch Address cannot be null or empty")
	private String branchAddress;


}
