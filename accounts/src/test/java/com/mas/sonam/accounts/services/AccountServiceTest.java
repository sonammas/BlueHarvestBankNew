package com.mas.sonam.accounts.services;

import com.mas.sonam.accounts.model.AccountType;
import com.mas.sonam.accounts.model.entity.Account;
import com.mas.sonam.accounts.model.entity.Customer;
import com.mas.sonam.accounts.repository.AccountRepository;
import com.mas.sonam.accounts.repository.CustomerRepository;
import com.mas.sonam.accounts.service.AccountService;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @InjectMocks
    private AccountService accountService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AccountRepository accountRepository;

    private Account account;

    private Customer customer;

    @Before
    public void setUp() {
        customer = Customer.builder().name("Sonam").surname("masuriha").build();

        account = Account.builder().customer(customer).accountType(AccountType.PRIMARY).accountNumber(12).build();
    }

    @Test
    public void openSecondaryAccountForCustomer() {
        Mockito.when(customerRepository.findById(1L)).thenReturn(customer);
        Mockito.when(accountRepository.findByCustomerAndAccountType(customer, AccountType.PRIMARY)).thenReturn(account);
        final Account account = accountService.openSecondaryAccountForCustomer(1L, BigDecimal.ZERO);
        assertEquals(AccountType.SECONDARY, account.getAccountType());
    }

}