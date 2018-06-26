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

@Component
public class DataLoader implements ApplicationRunner {

	private final CustomerRepository customerRepository;

	private final AccountRepository accountRepository;

	@Autowired
	public DataLoader(CustomerRepository customerRepository, AccountRepository accountRepository) {
		this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

	@Override
	public void run(ApplicationArguments args) {
		// customer Sonam
		Customer customer = new Customer();
		customer.setSurname("masuriha");
		customer.setName("sonam");

		// customer sumit
		Customer customer2 = new Customer();
		customer2.setSurname("Jhawar");
		customer2.setName("Sumit");

		// customer sumit
		Customer customer3 = new Customer();
		customer3.setSurname("Hawker");
		customer3.setName("Robert");


		customerRepository.save(customer);
		customerRepository.save(customer2);
		customerRepository.save(customer3);

        // Primary Account for customer Sonam
        Account account = new Account();
        account.setAccountNumber(123);
        account.setAccountType(AccountType.PRIMARY);
        account.setCustomer(customer);
        account.setBalance(new BigDecimal(123.66));
        account.setAccountOpeningDate(LocalDate.now());
		accountRepository.save(account);

		// Primary Account for customer Sumit
		Account account2 = new Account();
		account2.setAccountNumber(456);
		account2.setAccountType(AccountType.PRIMARY);
		account2.setCustomer(customer2);
		account2.setBalance(new BigDecimal(154));
		account2.setAccountOpeningDate(LocalDate.now());
		accountRepository.save(account2);

		// Primary Account for customer Robert
		Account account3 = new Account();
		account3.setAccountNumber(789);
		account3.setAccountType(AccountType.PRIMARY);
		account3.setCustomer(customer3);
		account3.setBalance(new BigDecimal(753));
		account3.setAccountOpeningDate(LocalDate.now());
		accountRepository.save(account3);

    }
}
