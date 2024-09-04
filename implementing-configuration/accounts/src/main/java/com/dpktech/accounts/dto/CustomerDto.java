package com.dpktech.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Customer & Account information")
public class CustomerDto {
	
	@Schema( description = "Name of the Customer",example = "FirstName LastName")
	@NotEmpty(message = "Name cannot be null or empty")
	@Size(min=5, max=35, message = "The length of the customer name should between 5 and 35")
	private String name;
	
	@Schema( description = "Email Address of the Customer",example = "tutor@dpktech.com")
	@NotEmpty(message = "Email cannot be null or empty")
	@Email(message = "Email address should be a valid value")
	private String email;

	@Schema( description = "Mobile of the Customer",example = "0987654321")
	@Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	private String mobileNumber;
	
	@Schema( description = "Accounts details of the Customer")
	private AccountsDto accountsDto;

}
