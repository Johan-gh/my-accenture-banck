package com.accenture.ms_accounts.dto;

import com.accenture.ms_accounts.dto.ms_customers.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
public class AccountDto {
    private Long accountNumber;
    private Long customerId;
    private String accountType;
    private String branch;
    private Float balance;

}
