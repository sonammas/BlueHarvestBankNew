package com.mas.sonam.transactions.service;

import com.mas.sonam.accounts.model.entity.Account;
import com.mas.sonam.accounts.repository.AccountRepository;
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
    private final AccountRepository accountRepository;

    public TransactionsService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public void createTransaction(Integer fromAccount,
                                  Integer toAccount,
                                   BigDecimal amount) {
        Account fromAcc = accountRepository.findByAccountNumber(fromAccount);
        Account toAcc = accountRepository.findByAccountNumber(toAccount);
        if(fromAcc != null) {
            Transaction fromTransaction = new Transaction();
            fromTransaction.setAccount(fromAcc);
            fromTransaction.setAmount(amount);
            fromTransaction.setTransactionType(TransactionType.DEBIT);
            fromTransaction.setLocalDate(LocalDate.now());
            transactionRepository.save(fromTransaction);
        }

        Transaction totransaction = new Transaction();
        totransaction.setAccount(toAcc);
        totransaction.setAmount(amount);
        totransaction.setTransactionType(TransactionType.CREDIT);
        totransaction.setLocalDate(LocalDate.now());
        transactionRepository.save(totransaction);

    }
}
