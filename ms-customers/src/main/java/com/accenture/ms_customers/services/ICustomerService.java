package com.accenture.ms_customers.services;

import com.accenture.ms_customers.dto.CustomerDto;
import com.accenture.ms_customers.dto.CustomersWithAccountsDto;

public interface ICustomerService {
    void createCustomer(CustomerDto customerDto);

    CustomerDto findById(Long id);
    CustomerDto fetchCustomerByDocument(String document);
    CustomerDto fetchCustomerByEmail(String email);
    CustomerDto updateCustomer(CustomerDto customerDto);
    void deleteCustomerByDocument(String document);
    void deleteCustomerByEmail(String email);

    CustomersWithAccountsDto fetchCustomerWithAccountsByDocument(String document);


}