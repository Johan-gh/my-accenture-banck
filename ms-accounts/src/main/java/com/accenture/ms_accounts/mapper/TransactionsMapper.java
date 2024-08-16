package com.accenture.ms_accounts.mapper;

import com.accenture.ms_accounts.dto.TransactionsRequestDto;
import com.accenture.ms_accounts.entity.Transactions;
import com.accenture.ms_accounts.dto.TransactionsDto;

public class TransactionsMapper {
    public static TransactionsDto mapTransactionsToDto(Transactions transactions, TransactionsDto transactionsDto) {
        transactionsDto.setAccountNumber(transactions.getAccountNumber());
        transactionsDto.setBalance(transactions.getBalance());
        transactionsDto.setAmount(transactions.getAmount());
        transactionsDto.setCreatedAt(transactions.getCreatedDate());
        return transactionsDto;
    }

    public static Transactions mapDtoToTransactions(TransactionsDto transactionsDto, Transactions transactions) {
        transactions.setAccountNumber(transactionsDto.getAccountNumber());
        transactions.setBalance(transactionsDto.getBalance());
        transactions.setAmount(transactionsDto.getAmount());
        transactions.setCreatedDate(transactionsDto.getCreatedAt());
        return transactions;
    }

    public static Transactions mapRequestDtoToTransactions(TransactionsRequestDto transactionsRequestDto, Transactions transactions) {
        transactions.setAccountNumber(transactionsRequestDto.getAccountNumber());
        transactions.setAmount(transactionsRequestDto.getAmount());
        return transactions;
    }


}
