package com.accenture.ms_customers.dto.ms_accounts;

import lombok.Data;

@Data
public class AccountDto {
    private Long accountNumber;
    private Long customerId;
    private String accountType;
    private String branch;
    private Float balance;
}
