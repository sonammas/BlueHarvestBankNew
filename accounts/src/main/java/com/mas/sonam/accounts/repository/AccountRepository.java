package com.mas.sonam.accounts.repository;

import com.mas.sonam.accounts.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountNumber(int accountNumber);

}
