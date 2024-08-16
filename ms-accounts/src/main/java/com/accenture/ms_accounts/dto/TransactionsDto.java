package com.accenture.ms_accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionsDto {
    private Long accountNumber;
    private Float amount;
    private Float balance;
    private LocalDateTime createdAt;
}
