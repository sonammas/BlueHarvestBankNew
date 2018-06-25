package com.mas.sonam.accounts.controller;

import com.mas.sonam.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "customer")
public class AccountCommandController {

    @Autowired
    private final AccountService accountService;

    public AccountCommandController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "{id}/open/{initialCredit}")
    public void openSecondaryAccountForCustomer(@PathVariable Long id, @PathVariable BigDecimal initialCredit){
        accountService.openSecondaryAccountForCustomer(id, initialCredit);
    }

    @GetMapping(value = "{id}/transactions")
    public void getTransactionForCustomer(@PathVariable Long id){
        accountService.getTransactionForCustomer(id);
    }
}
