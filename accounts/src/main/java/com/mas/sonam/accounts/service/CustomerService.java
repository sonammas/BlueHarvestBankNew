package com.mas.sonam.accounts.service;

import com.mas.sonam.accounts.controller.AccountTransactionBean;
import com.mas.sonam.accounts.controller.AccountTransactionServiceProxy;
import com.mas.sonam.accounts.model.AccountType;
import com.mas.sonam.accounts.model.entity.Account;
import com.mas.sonam.accounts.repository.AccountRepository;
import com.mas.sonam.accounts.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Transactional
@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final AccountTransactionServiceProxy accountTransactionServiceProxy;

    public CustomerService(CustomerRepository customerRepository, AccountRepository accountRepository, AccountTransactionServiceProxy accountTransactionServiceProxy) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.accountTransactionServiceProxy = accountTransactionServiceProxy;
    }

    public void openAccountForCustomer(final Long customerId, final BigDecimal initialCredit) {
        if(customerRepository.findById(customerId).isPresent()) {
            if (initialCredit.compareTo(BigDecimal.ZERO) != 0) {
                //create new account
                Account account = new Account();
                account.setAccountOpeningDate(LocalDate.now());
                account.setAccountType(AccountType.SECONDARY);
                account.setBalance(initialCredit);
                account.setCustomer(customerRepository.findById(customerId).get());
                accountRepository.save(account);
                //a new transaction to be sent to the new account
                AccountTransactionBean accountTransactionBean =
                        accountTransactionServiceProxy.retrieveExchangeValue(0, account.getAccountNumber(), initialCredit);
            }
        }
    }
}
