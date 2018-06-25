package com.mas.sonam.accounts.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@RibbonClient(name="transactions")
public interface AccountTransactionServiceProxy {

    @PostMapping(value = "/create/from/{from}/to/{to}/amount/{amount}")
    public AccountTransactionBean retrieveExchangeValue
            (@PathVariable("from") int from, @PathVariable("to") int to,
             @PathVariable("amount") BigDecimal amount);
}
