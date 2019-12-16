package com.example.testdemoapp.service;

import com.example.testdemoapp.domain.Account;
import com.example.testdemoapp.domain.Customer;
import com.example.testdemoapp.repository.AccountRepository;
import com.example.testdemoapp.repository.CustomerRepository;
import com.example.testdemoapp.service.dto.CustomerDto;
import com.example.testdemoapp.service.mapper.CustomerMapper;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerService {

    private CustomerRepository customerRepo;
    private AccountRepository accountRepo;
    private CustomerMapper customerMapper;

    private static final byte ACCOUNT_NUMBER_LENGTH = 16;

    public CustomerService(CustomerRepository customerRepo, AccountRepository accountRepo, CustomerMapper customerMapper) {
        this.customerRepo = customerRepo;
        this.accountRepo = accountRepo;
        this.customerMapper = customerMapper;
    }


    public void newCustomer(CustomerDto customerDto) {
        if (customerRepo.findByPassportNumber(customerDto.getPassportNumber()) != null) {
            throw new RuntimeException("Customer already exists");
        }

        Customer newCustomer = customerMapper.toEntity(customerDto);

        Set<Account> startAccount = new HashSet<>();
        startAccount.add(startAccount(newCustomer));
        newCustomer.setAccounts(startAccount);

        customerRepo.save(newCustomer);
    }

    private Account startAccount(Customer customer) {
        Account account = new Account();
        account.setAccountNumber(RandomStringUtils.random(ACCOUNT_NUMBER_LENGTH, false, true));
        account.setOpened(new Date());
        account.setActive(true);
        account.setBalance(0.0);
        account.setOwner(customer);

        return account;
    }
}
