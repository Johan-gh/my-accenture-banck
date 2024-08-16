package com.accenture.ms_customers.services.client;

import com.accenture.ms_customers.dto.ms_accounts.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("ms-accounts")
public interface AccountsFeignClient {
    @GetMapping(value = "/api/retrieve-customer-accounts/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AccountDto>> fetchAllCustomerAccounts(@PathVariable Long customerId);
}
