package com.accenture.ms_accounts.dto;

import com.accenture.ms_accounts.dto.ms_customers.CustomerDto;
import lombok.Data;

@Data
public class AccountWithCustomerDto {
    private Long accountNumber;
    private Long customerId;
    private String accountType;
    private String branch;
    private Float balance;
    private CustomerDto customer;

}
