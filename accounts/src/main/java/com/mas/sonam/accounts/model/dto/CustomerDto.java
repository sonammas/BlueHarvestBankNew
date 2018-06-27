package com.mas.sonam.accounts.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String name;

    private String surname;

    private BigDecimal balance;

    private List<TransactionDto> transactionDtos;
}
