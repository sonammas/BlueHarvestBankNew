package com.mas.sonam.accounts.controller;

import java.math.BigDecimal;

public class AccountTransactionBean {

    private Long id;
    private int from;
    private int to;
    private BigDecimal amount;
    private int port;

    public AccountTransactionBean() {}

    public AccountTransactionBean(Long id, int from, int to, BigDecimal amount, int port) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.port = port;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
