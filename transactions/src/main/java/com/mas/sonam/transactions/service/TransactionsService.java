package com.mas.sonam.transactions.service;

import com.mas.sonam.transactions.model.entity.Transaction;
import com.mas.sonam.transactions.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
        transaction.setLocalDate(LocalDate.now());
        Transaction save = transactionRepository.save(transaction);
        return save.getId();
    }

    public List<Transaction> getTransactionForAccountId(final int accountId) {
       return transactionRepository.findByAccountId(accountId);
    }
}
