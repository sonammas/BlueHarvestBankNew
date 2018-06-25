package com.mas.sonam.transactions.service;


import com.mas.sonam.transactions.model.entity.Transaction;
import com.mas.sonam.transactions.model.entity.TransactionType;
import com.mas.sonam.transactions.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@Transactional
public class TransactionsService {

    private final TransactionRepository transactionRepository;

    public TransactionsService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Long createTransaction(Integer fromAccount,
                                  Integer toAccount,
                                  BigDecimal amount) {

        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.DEBIT);
        transaction.setLocalDate(LocalDate.now());
        Transaction save = transactionRepository.save(transaction);
        return save.getId();
    }
}
