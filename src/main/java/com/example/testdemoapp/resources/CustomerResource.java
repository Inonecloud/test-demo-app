package com.example.testdemoapp.resources;

import com.example.testdemoapp.service.CustomerService;
import com.example.testdemoapp.service.dto.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customer")
public class CustomerResource {

    private CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/new_customer")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('USER')")
    public void newCustomer(@RequestBody CustomerDto customerDto){
        customerService.newCustomer(customerDto);
    }
}
