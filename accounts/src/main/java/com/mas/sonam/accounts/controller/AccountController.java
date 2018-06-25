package com.mas.sonam.accounts.controller;

import com.mas.sonam.accounts.model.dto.CustomerDto;
import com.mas.sonam.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "customer")
public class AccountController {

    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "{id}/open/{initialCredit}")
    public void openSecondaryAccountForCustomer(@PathVariable Long id, @PathVariable BigDecimal initialCredit){
        accountService.openSecondaryAccountForCustomer(id, initialCredit);
    }

    @GetMapping(value = "{id}/transactions/type/{type}")
    public CustomerDto getTransactionForCustomer(@PathVariable Long id, @PathVariable String type){
        return accountService.getTransactionForCustomer(id, type);
    }
}
