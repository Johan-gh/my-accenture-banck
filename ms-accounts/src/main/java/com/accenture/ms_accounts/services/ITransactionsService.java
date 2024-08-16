package com.accenture.ms_accounts.services;

import com.accenture.ms_accounts.dto.TransactionsDto;
import com.accenture.ms_accounts.dto.TransactionsRequestDto;

import java.util.List;

public interface ITransactionsService {
    void create(TransactionsRequestDto transactionsDto);

    List<TransactionsDto> fetchAccountTransactions(Long accountNumber);
}
