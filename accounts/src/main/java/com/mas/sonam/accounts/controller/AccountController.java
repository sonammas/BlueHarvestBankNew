package com.mas.sonam.accounts.controller;

import com.mas.sonam.accounts.model.dto.CustomerDto;
import com.mas.sonam.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "account")
public class AccountController {

    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * This rest end point is used to open secondary account for the existing customers
     * @param id id of the customer
     * @param initialCredit the initial amount to open the secondary account
     */
    @PostMapping(value = "{id}/open/{initialCredit}")
    public Long openSecondaryAccountForCustomer(@PathVariable Long id, @PathVariable BigDecimal initialCredit){
        return accountService.openSecondaryAccountForCustomer(id, initialCredit);
    }

    /**
     * This rest end point is to fetch all the transactions, name, surname, balance for the customer
     * @param id id of the customer
     * @param type type of the account - AccountType (PRIMARY/SECONDARY)
     */
    @GetMapping(value = "{id}/transactions/type/{type}")
    public CustomerDto getTransactionForCustomer(@PathVariable Long id, @PathVariable String type){
        return accountService.getTransactionForCustomer(id, type);
    }
}
