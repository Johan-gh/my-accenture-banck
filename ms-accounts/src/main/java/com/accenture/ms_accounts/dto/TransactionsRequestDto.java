package com.accenture.ms_accounts.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TransactionsRequestDto {
    private Long accountNumber;
    private Float amount;
}
