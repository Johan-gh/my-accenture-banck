package com.accenture.ms_customers.mapper;

import com.accenture.ms_customers.dto.CustomerDto;
import com.accenture.ms_customers.dto.CustomersWithAccountsDto;
import com.accenture.ms_customers.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapCustomerToDto(Customer customer, CustomerDto customerDto){
        customerDto.setAddress(customer.getAddress());
        customerDto.setDocument(customer.getDocument());
        customerDto.setName(customer.getName());
        customerDto.setPhone(customer.getPhone());
        customerDto.setEmail(customer.getEmail());

        return customerDto;
    }
    public static CustomersWithAccountsDto mapCustomerToDtoWithAccounts(Customer customer, CustomersWithAccountsDto customersWithAccountsDto){
        customersWithAccountsDto.setAddress(customer.getAddress());
        customersWithAccountsDto.setDocument(customer.getDocument());
        customersWithAccountsDto.setName(customer.getName());
        customersWithAccountsDto.setPhone(customer.getPhone());
        customersWithAccountsDto.setEmail(customer.getEmail());

        return customersWithAccountsDto;
    }
    public static Customer mapDtoToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setAddress(customerDto.getAddress());
        customer.setDocument(customerDto.getDocument());
        customer.setName(customerDto.getName());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());

        return customer;
    }
}
