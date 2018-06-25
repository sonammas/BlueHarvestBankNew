package com.mas.sonam.transactions.controller;

import com.mas.sonam.transactions.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TransactionCommandController {

    @Autowired
    private final TransactionsService transactionService;

    public TransactionCommandController(TransactionsService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/transactions/from/{from}/to/{to}/amount/{amount}")
    public Long createTransaction(@PathVariable int fromAccount,
                                  @PathVariable int toAccount,
                                  @PathVariable BigDecimal amount){
        return transactionService.createTransaction(fromAccount, toAccount, amount);
    }
}
