package com.mas.sonam.transactions.controller;

import com.mas.sonam.transactions.model.entity.dto.TransactionDto;
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

    /**
     * This rest api will be used by Account service to create the transaction
     * @param from
     * @param to
     * @param amount
     * @return transactionId
     */

    @RequestMapping(value = "/from/{from}/to/{to}/amount/{amount}", method = RequestMethod.GET)
    public Long createTransaction(@PathVariable int from,
                                  @PathVariable int to,
                                  @PathVariable BigDecimal amount){
        return transactionService.createTransaction(from, to, amount);
    }

    /**
     * Get all the transaction summary for the account
     * @param accountId
     * @return List of transactions
     */
    @RequestMapping(value = "/transaction/{accountId}", method = RequestMethod.GET)
    public List<TransactionDto> getTransactionForAccount(@PathVariable int accountId){
        return transactionService.getTransactionForAccountId(accountId);
    }
}
