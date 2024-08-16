package com.accenture.ms_accounts.mapper;

import com.accenture.ms_accounts.dto.AccountDto;
import com.accenture.ms_accounts.dto.AccountWithCustomerDto;
import com.accenture.ms_accounts.dto.CreateAccountRequestDto;
import com.accenture.ms_accounts.entity.Account;

public class AccountMapper {
    public static AccountDto mapAccountToDto(Account account, AccountDto accountDto) {
        accountDto.setCustomerId(account.getCustomerId());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBranch(account.getBranch());
        accountDto.setBalance(account.getBalance());
        return accountDto;
    }
    public static AccountWithCustomerDto mapAccountToFullDto(Account account, AccountWithCustomerDto accountDto) {
        accountDto.setCustomerId(account.getCustomerId());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBranch(account.getBranch());
        accountDto.setBalance(account.getBalance());
        return accountDto;
    }

    public static Account mapDtoToAccount(AccountDto accountDto, Account account) {
        account.setCustomerId(accountDto.getCustomerId());
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBranch(accountDto.getBranch());
        account.setBalance(accountDto.getBalance());
        return account;
    }
    public static Account mapCreateAccountRequestDtoToAccount(CreateAccountRequestDto createAccountRequestDto, Account account) {
        account.setCustomerId(createAccountRequestDto.getCustomerId());
        account.setAccountType(createAccountRequestDto.getAccountType());
        account.setBranch(createAccountRequestDto.getBranch());
        return account;
    }
}
