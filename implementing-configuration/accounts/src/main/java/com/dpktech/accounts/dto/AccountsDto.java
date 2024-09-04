package com.dpktech.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Accounts", description = "Schema to hold Account details of the Customer")
public class AccountsDto {
	
	@Schema( description = "Account Number of the Customer", example = "1234567890")
	@NotEmpty(message = "AccountNumber cannot be null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})",message = "Account number must be 10 digits")
	private Long accountNumber;
	
	@Schema( description = "Account Type of the Customer",example = "savings")
	@NotEmpty(message = "Account Type cannot be null or empty")
	private String accountType;

	@Schema( description = "Branch Address of the Bank",example = "123 NewYork")
	@NotEmpty(message = "Branch Address cannot be null or empty")
	private String branchAddress;


}
