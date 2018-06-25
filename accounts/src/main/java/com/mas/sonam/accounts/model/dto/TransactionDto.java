package com.mas.sonam.accounts.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDto {
    private Long id;

    @JsonIgnore
    private LocalDate localDate;

    private int fromAccount;

    private int toAccount;

    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionDto() {
    }

    public TransactionDto(Long id, LocalDate localDate, int fromAccount, int toAccount, BigDecimal amount) {
        this.id = id;
        this.localDate = localDate;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }
}
