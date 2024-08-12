package com.dpktech.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpktech.accounts.entity.Accounts;
import com.dpktech.accounts.entity.Customer;

public interface AccountsRepository extends JpaRepository<Accounts, Long>{
	Optional<Accounts> findByCustomerId(Long long1);

}
