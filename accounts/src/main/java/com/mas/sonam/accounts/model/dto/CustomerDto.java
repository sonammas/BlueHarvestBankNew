package com.mas.sonam.accounts.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CustomerDto {

    private String name;

    private String surname;

    private BigDecimal balance;

    private List<TransactionDto> transactionDtos;

    public CustomerDto() {
    }

    public CustomerDto(String name, String surname, BigDecimal balance, List<TransactionDto> transactionDtos) {
        this.name = name;
        this.surname = surname;
        this.balance = balance;
        this.transactionDtos = transactionDtos;
    }

    public void setTransactionDto(List<TransactionDto> transactionDtos) {
        this.transactionDtos = transactionDtos;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
