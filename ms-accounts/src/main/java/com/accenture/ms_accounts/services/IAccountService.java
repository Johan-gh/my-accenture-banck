package com.accenture.ms_accounts.services;

import com.accenture.ms_accounts.dto.AccountDto;
import com.accenture.ms_accounts.dto.AccountWithCustomerDto;
import com.accenture.ms_accounts.dto.CreateAccountRequestDto;

import java.util.List;

public interface IAccountService {
    AccountDto create(CreateAccountRequestDto createAccountRequestDto);

    AccountDto fetch(Long accountNumber);

    List<AccountDto> findAll(Long customerId);

    AccountDto update(AccountDto accountDto);

    AccountWithCustomerDto fetchFullAccount(Long accountNumber);

    // TODO: inactivate an account
}
