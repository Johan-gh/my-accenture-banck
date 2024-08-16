package com.accenture.ms_accounts.repository;

import com.accenture.ms_accounts.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> findAllTransactionsByAccountNumberOrderByTransactionId(Long accountNumber);
}
