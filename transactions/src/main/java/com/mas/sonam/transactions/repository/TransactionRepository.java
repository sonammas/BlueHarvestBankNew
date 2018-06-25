package com.mas.sonam.transactions.repository;

import com.mas.sonam.transactions.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT p FROM Transaction p WHERE p.fromAccount = :accountId OR p.toAccount = :accountId")
    List<Transaction> findByAccountId(@Param("accountId") int accountId);
}