package com.accenture.ms_accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountWithTransactionsDto {
    private AccountDto account;
    private List<TransactionsDto> transactions;

}
