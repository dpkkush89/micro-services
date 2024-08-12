package com.dpktech.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpktech.accounts.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Optional<Customer> findByMobileNumber(String mobileNumber);
//	Optional<Customer> findByMobileNumberAndCustomerId(String mobileNumber, Long customerId);
}
