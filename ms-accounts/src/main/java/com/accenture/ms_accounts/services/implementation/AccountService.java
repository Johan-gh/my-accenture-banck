package com.accenture.ms_accounts.services.implementation;

import com.accenture.ms_accounts.dto.AccountDto;
import com.accenture.ms_accounts.dto.AccountWithCustomerDto;
import com.accenture.ms_accounts.dto.CreateAccountRequestDto;
import com.accenture.ms_accounts.dto.ms_customers.CustomerDto;
import com.accenture.ms_accounts.entity.Account;
import com.accenture.ms_accounts.exception.ResourceNotFound;
import com.accenture.ms_accounts.mapper.AccountMapper;
import com.accenture.ms_accounts.repository.AccountRepository;
import com.accenture.ms_accounts.services.IAccountService;
import com.accenture.ms_accounts.services.client.CustomersFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class AccountService implements IAccountService {
    private AccountRepository accountRepository;
    private CustomersFeignClient customersFeignClient;

    @Override
    public AccountDto create(CreateAccountRequestDto accountDto) {
        Account account = AccountMapper.mapCreateAccountRequestDtoToAccount(accountDto, new Account());
        Long accountNumber = 100000000L + new Random().nextInt(900000000);
        account.setAccountNumber(accountNumber);
        account.setBalance(0.0F);
        Account newAccount = accountRepository.save(account);
        return AccountMapper.mapAccountToDto(newAccount, new AccountDto());
    }

    @Override
    public AccountDto fetch(Long accountNumber) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(
                () -> new ResourceNotFound("cuenta", "número de cuenta", accountNumber.toString())
        );
        return AccountMapper.mapAccountToDto(account, new AccountDto());
    }

    @Override
    public AccountWithCustomerDto fetchFullAccount(Long accountNumber) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(
                () -> new ResourceNotFound("cuenta", "número de cuenta", accountNumber.toString())
        );

        AccountWithCustomerDto full_account = AccountMapper.mapAccountToFullDto(account, new AccountWithCustomerDto());
        System.out.println("Fetching account");
        System.out.println(full_account);
        CustomerDto customer = customersFeignClient.findCustomerById(account.getCustomerId()).getBody();
        System.out.println("Fetching customer");
        System.out.println(customer);
        full_account.setCustomer(customer);
        return full_account;
    }

    @Override
    public List<AccountDto> findAll(Long customerId) {

        return accountRepository.findAllByCustomerIdOrderByAccountNumber(customerId)
                .stream()
                .map(account -> AccountMapper.mapAccountToDto(account, new AccountDto())).toList();
    }

    @Override
    public AccountDto update(AccountDto accountDto) {
        return null;
    }
}
