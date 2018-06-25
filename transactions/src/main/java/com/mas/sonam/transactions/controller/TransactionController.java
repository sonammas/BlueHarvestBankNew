package com.mas.sonam.transactions.controller;

import com.mas.sonam.transactions.model.entity.Transaction;
import com.mas.sonam.transactions.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private final TransactionsService transactionService;

    public TransactionController(TransactionsService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "/from/{from}/to/{to}/amount/{amount}", method = RequestMethod.GET)
    public Long createTransaction(@PathVariable int from,
                                  @PathVariable int to,
                                  @PathVariable BigDecimal amount){
        return transactionService.createTransaction(from, to, amount);
    }

    @RequestMapping(value = "/transaction/{accountId}", method = RequestMethod.GET)
    public List<Transaction> getTransactionForAccount(@PathVariable int accountId){
        return transactionService.getTransactionForAccountId(accountId);
    }
}
