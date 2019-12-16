package com.example.testdemoapp.service.mapper;

import com.example.testdemoapp.domain.Customer;
import com.example.testdemoapp.service.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toEntity(CustomerDto dto){
        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setBirthday(dto.getBirthday());
        customer.setPassportNumber(dto.getPassportNumber());
        return customer;
    }

    public CustomerDto toDto(Customer customer){
        return new CustomerDto(customer);
    }
}
