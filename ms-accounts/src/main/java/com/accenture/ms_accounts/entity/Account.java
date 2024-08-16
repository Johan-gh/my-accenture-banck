package com.accenture.ms_accounts.entity;

import jakarta.persistence.*;
import lombok.*;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Account extends BaseEntity {
    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch")
    private String branch;

    @Column(name = "balance")
    private Float balance;
}



