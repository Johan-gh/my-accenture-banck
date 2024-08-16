package com.accenture.ms_accounts.repository;

import com.accenture.ms_accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public List<Account> findAllByCustomerIdOrderByAccountNumber(Long customerId);
}
