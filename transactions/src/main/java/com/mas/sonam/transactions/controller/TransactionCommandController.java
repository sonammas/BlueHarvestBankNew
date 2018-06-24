package com.mas.sonam.transactions.controller;

import com.mas.sonam.transactions.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "transaction")
public class TransactionCommandController {

    @Autowired
    private final TransactionsService transactionService;

    public TransactionCommandController(TransactionsService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping(value = "/create/from/{from}/to/{to}/amount/{amount}")
    public void createTransaction(@PathVariable int fromAccount,
                                  @PathVariable int toAccount,
                                  @PathVariable BigDecimal amount){
        transactionService.createTransaction(fromAccount, toAccount, amount);
    }
}
