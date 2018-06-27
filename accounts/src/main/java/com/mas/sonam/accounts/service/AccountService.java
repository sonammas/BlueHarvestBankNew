package com.mas.sonam.accounts.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mas.sonam.accounts.model.AccountType;
import com.mas.sonam.accounts.model.dto.CustomerDto;
import com.mas.sonam.accounts.model.dto.TransactionDto;
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

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;


    public AccountService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public Account openSecondaryAccountForCustomer(final Long customerId, final BigDecimal initialCredit) {
        Customer customer = customerRepository.findById(customerId);
        if (customer != null) {
            //create new account
            Account account = Account.builder()
                    .accountOpeningDate(LocalDate.now())
                    .accountType(AccountType.SECONDARY)
                    .balance(initialCredit)
                    .customer(customer)
                    .build();
            Account accountCreated = accountRepository.save(account);

            Account primaryAccountOftheCustomer = accountRepository.findByCustomerAndAccountType(customer, AccountType.PRIMARY);

            if (initialCredit.compareTo(BigDecimal.ZERO) != 0) {
                //a new transaction to be sent to the new account from primary account
                //check if enough balance is there on the primary account
                if(primaryAccountOftheCustomer.getBalance().compareTo(initialCredit) > 0) {
                    restTemplate.exchange("http://transactions/from/{from}/to/{to}/amount/{amount}",
                            HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                    }, primaryAccountOftheCustomer.getId(), accountCreated.getId(), initialCredit).getBody();
                }
            }

            //set the new balance on the primary account
            primaryAccountOftheCustomer.setBalance(primaryAccountOftheCustomer.getBalance().subtract(initialCredit));
            accountRepository.save(primaryAccountOftheCustomer);
            return accountCreated;
        }
        return null;
    }

    public CustomerDto getTransactionForCustomer(final Long customerId, final String accountType) {
        Customer customer = customerRepository.findById(customerId);
        Account account = accountRepository.findByCustomerAndAccountType
                (customerRepository.findById(customerId), AccountType.valueOf(accountType));

            String response = restTemplate.exchange("http://transactions/transaction/{accountId}",
                    HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                    }, account.getId()).getBody();
            List<TransactionDto> transactionDtos = fromJSON(new TypeReference<List<TransactionDto>>() {}, response);

        return CustomerDto.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .transactionDtos(transactionDtos)
                .balance(account.getBalance())
                .build();
    }

    public static <T> T fromJSON(final TypeReference<T> type,
                                 final String jsonPacket) {
        T data = null;
        try {
            data = new ObjectMapper().readValue(jsonPacket, type);
        } catch (Exception e) {
            // Handle the problem
        }
        return data;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
