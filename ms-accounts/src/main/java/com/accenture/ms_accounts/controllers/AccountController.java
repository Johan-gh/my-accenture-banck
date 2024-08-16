package com.accenture.ms_accounts.controllers;

import com.accenture.ms_accounts.dto.*;
import com.accenture.ms_accounts.services.IAccountService;
import com.accenture.ms_accounts.services.ITransactionsService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
public class AccountController {
    @NotNull
    private IAccountService accountService;
    @NotNull
    private ITransactionsService transactionService;
    @NotNull
    private Environment environment;

    @NotNull
    private SupportInfoDto supportInfoDto;


    @GetMapping(value = "/java-home")
    public ResponseEntity<String> getJavaHome() {
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
    }

    @GetMapping(value = "/support-info")
    public ResponseEntity<SupportInfoDto> getSupportInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(supportInfoDto);
    }

    @PostMapping(value = "/create-account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountRequestDto accountDto) {
        AccountDto createdAccount = accountService.create(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @GetMapping(value = "/fetch-account/{accountNumber}")
    public ResponseEntity<AccountDto> fetchAccount(@PathVariable Long accountNumber) {
        AccountDto accountDto = accountService.fetch(accountNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountDto);
    }

    @GetMapping(value = "/retrieve-customer-accounts/{customerId}")
    public ResponseEntity<List<AccountDto>> fetchAllCustomerAccounts(@PathVariable Long customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.findAll(customerId));
    }

    @PostMapping(value = "/create-transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> createTransactions(@RequestBody TransactionsRequestDto transactionsDto) {
        transactionService.create(transactionsDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Transacción creada exitosamente"));
    }

    @GetMapping(value = "/retrieve-transactions/{accountNumber}")
    public ResponseEntity<List<TransactionsDto>> fetchAllCustomerTransactions(@PathVariable Long accountNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.fetchAccountTransactions(accountNumber));
    }

}
