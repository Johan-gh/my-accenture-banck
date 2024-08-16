package com.accenture.ms_accounts.dto;

import lombok.Data;

@Data
public class CreateAccountRequestDto {
    private Long customerId;
    private String accountType;
    private String branch;
}
