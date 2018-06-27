package com.mas.sonam.accounts.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {

    private Long id;

    private int accountNumber;

    private LocalDate accountOpeningDate;

    private Long customerId;

    private String accountType;

    private BigDecimal balance;
}
