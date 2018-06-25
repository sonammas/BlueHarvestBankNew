package com.mas.sonam.accounts.service;

import com.mas.sonam.accounts.model.AccountType;
import com.mas.sonam.accounts.model.entity.Account;
import com.mas.sonam.accounts.model.entity.Customer;
import com.mas.sonam.accounts.repository.AccountRepository;
import com.mas.sonam.accounts.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class AccountService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;


    public AccountService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public void openSecondaryAccountForCustomer(final Long customerId, final BigDecimal initialCredit) {

        if (customerRepository.findById(customerId) !=null) {
            Customer customer = customerRepository.findById(customerId);
            //create new account
            Account account = new Account();
            account.setAccountOpeningDate(LocalDate.now());
            account.setAccountType(AccountType.SECONDARY);
            account.setBalance(initialCredit);
            account.setCustomer(customerRepository.findById(customerId));
            Account save = accountRepository.save(account);

            Account primaryAccountOftheCustomer = accountRepository.findByCustomerAndAccountType(customer, AccountType.PRIMARY);

            if (initialCredit.compareTo(BigDecimal.ZERO) != 0) {
                //a new transaction to be sent to the new account from primary account
                //check if enough balance is there on the primary account
                if(primaryAccountOftheCustomer.getBalance().compareTo(initialCredit) > 0) {
                    String response = restTemplate.exchange("http://transactions/from/{from}/to/{to}/amount/{amount}",
                            HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                    }, primaryAccountOftheCustomer.getId(), save.getId(), initialCredit).getBody();
                }
            }
            //set the new balance on the primary account
            primaryAccountOftheCustomer.setBalance(primaryAccountOftheCustomer.getBalance().subtract(initialCredit));
            accountRepository.save(primaryAccountOftheCustomer);
        }
    }

    public void getTransactionForCustomer(final Long customerId) {
        //get accounts for customerId
        List<Account> customerAccounts = accountRepository.findByCustomer(customerRepository.findById(customerId));
        customerAccounts.forEach(account -> {
            String response = restTemplate.exchange("http://transactions/transaction/{accountId}",
                    HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                    }, account.getId()).getBody();
        });

    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
