package com.mas.sonam.transactions.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionDto {
    private Long id;

    private LocalDate localDate;

    private int fromAccount;

    private int toAccount;

    private BigDecimal amount;
}
