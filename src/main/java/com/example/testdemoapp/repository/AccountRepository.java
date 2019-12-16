package com.example.testdemoapp.repository;

import com.example.testdemoapp.domain.Account;
import com.example.testdemoapp.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByOwner(Customer owner);
}
