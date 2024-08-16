package com.accenture.ms_customers.controllers;

import com.accenture.ms_customers.dto.CustomersWithAccountsDto;
import com.accenture.ms_customers.services.ICustomerService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class ClientsControllers {
    @NonNull
    private ICustomerService customerService;

    @GetMapping(value = "/customers/accounts")
    public ResponseEntity<CustomersWithAccountsDto> fetchCustomersWithAccountsByDocument(@RequestParam String document) {
        CustomersWithAccountsDto customersWithAccounts = customerService.fetchCustomerWithAccountsByDocument(document);
        return ResponseEntity.status(HttpStatus.OK).body(customersWithAccounts);
    }
}
