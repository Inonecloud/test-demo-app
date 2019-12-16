package com.example.testdemoapp.repository;

import com.example.testdemoapp.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByPassportNumber(String passportNumber);
}
