package com.mas.sonam.accounts.controller;

import com.mas.sonam.accounts.service.CustomerService;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "customer")
public class CustomerCommandController {

    @Autowired
    private final CustomerService customerService;

    public CustomerCommandController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "{id}/open/{initialCredit}")
    public void openAccountForCustomer(@PathVariable Long id, @PathVariable BigDecimal initialCredit){
        customerService.openAccountForCustomer(id, initialCredit);
    }
}
