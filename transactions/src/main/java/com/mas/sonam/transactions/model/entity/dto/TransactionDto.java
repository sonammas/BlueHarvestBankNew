package com.mas.sonam.transactions.model.entity.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDto {
    private Long id;

    private LocalDate localDate;

    private int fromAccount;

    private int toAccount;

    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(int fromAccount) {
        this.fromAccount = fromAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    public void setToAccount(int toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
