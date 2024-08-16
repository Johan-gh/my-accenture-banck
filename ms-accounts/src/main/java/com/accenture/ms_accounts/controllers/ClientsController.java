package com.accenture.ms_accounts.controllers;

import com.accenture.ms_accounts.dto.AccountDto;
import com.accenture.ms_accounts.dto.AccountWithCustomerDto;
import com.accenture.ms_accounts.services.IAccountService;
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
public class ClientsController {
    @NonNull
    private IAccountService accountService;

    @GetMapping(value = "/account/detail")
    public ResponseEntity<AccountWithCustomerDto> fetchAccountWithCustomer(@RequestParam Long accountNumber) {
        AccountWithCustomerDto fullAccount = accountService.fetchFullAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(fullAccount);
    }
}
