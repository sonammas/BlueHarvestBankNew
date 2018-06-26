package com.mas.sonam.accounts;

import com.mas.sonam.accounts.model.AccountType;
import com.mas.sonam.accounts.model.entity.Account;
import com.mas.sonam.accounts.model.entity.Customer;
import com.mas.sonam.accounts.repository.AccountRepository;
import com.mas.sonam.accounts.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DataLoader implements ApplicationRunner {

	private final CustomerRepository customerRepository;

	private final AccountRepository accountRepository;

	@Autowired
	public DataLoader(CustomerRepository customerRepository, AccountRepository accountRepository) {
		this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// customer Sonam
		Customer customer = new Customer();
		customer.setSurname("masuriha");
		customer.setName("sonam");

		// customer sumit
		Customer customer2 = new Customer();
		customer2.setSurname("Jhawar");
		customer2.setName("Sumit");

		customerRepository.save(customer);
		customerRepository.save(customer2);

        // customer Sonam
        Account account = new Account();
        account.setAccountNumber(123);
        account.setAccountType(AccountType.PRIMARY);
        account.setCustomer(customer);
        account.setBalance(new BigDecimal(123.66));
        account.setAccountOpeningDate(LocalDate.now());

		accountRepository.save(account);

    }
}
