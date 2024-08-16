package com.accenture.ms_accounts.services.implementation;

import com.accenture.ms_accounts.dto.TransactionsDto;
import com.accenture.ms_accounts.dto.TransactionsRequestDto;
import com.accenture.ms_accounts.exception.BusinessException;
import com.accenture.ms_accounts.mapper.TransactionsMapper;
import com.accenture.ms_accounts.entity.Account;
import com.accenture.ms_accounts.entity.Transactions;
import com.accenture.ms_accounts.repository.AccountRepository;
import com.accenture.ms_accounts.repository.TransactionsRepository;
import com.accenture.ms_accounts.services.ITransactionsService;
import com.accenture.ms_accounts.exception.ResourceNotFound;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TransactionService implements ITransactionsService {
    private TransactionsRepository transactionsRepository;
    private AccountRepository accountRepository;

    @Override
    public void create(TransactionsRequestDto transactionsRequestDto) {
        Account account = accountRepository.findById(transactionsRequestDto.getAccountNumber())
                .orElseThrow(() -> new ResourceNotFound("cuenta", "número de cuenta", transactionsRequestDto.getAccountNumber().toString()));

        Transactions transactions = TransactionsMapper.mapRequestDtoToTransactions(transactionsRequestDto, new Transactions());

        if (account.getBalance() >= transactionsRequestDto.getAmount()) {
            throw new BusinessException("El saldo es insuficiente");
        }
        transactions.setBalance(account.getBalance() + transactionsRequestDto.getAmount());

        account.setBalance(transactions.getBalance());
        accountRepository.save(account);
        transactionsRepository.save(transactions);
    }

    @Override
    public List<TransactionsDto> fetchAccountTransactions(Long accountNumber) {
        return transactionsRepository.findAllTransactionsByAccountNumberOrderByTransactionId(accountNumber)
                .stream()
                .map(transactions -> TransactionsMapper.mapTransactionsToDto(transactions, new TransactionsDto())).toList();
    }
}
