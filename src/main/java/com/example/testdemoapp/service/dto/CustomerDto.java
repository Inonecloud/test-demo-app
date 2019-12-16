package com.example.testdemoapp.service.dto;

import com.example.testdemoapp.domain.Customer;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class CustomerDto {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Date birthday;

    @NotNull
    private String passportNumber;

    public CustomerDto() {
    }

    public CustomerDto(Customer customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.birthday = customer.getBirthday();
        this.passportNumber = customer.getPassportNumber();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}
