package com.mas.sonam.transactions.service;

import com.mas.sonam.transactions.model.entity.Transaction;
import com.mas.sonam.transactions.model.entity.dto.TransactionDto;
import com.mas.sonam.transactions.repository.TransactionRepository;
import com.mas.sonam.transactions.transformer.TransactionToTransactionDtoTransformer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<TransactionDto> getTransactionForAccountId(final int accountId) {
        return transactionRepository.findByAccountId(accountId)
                .stream()
                .map(TransactionToTransactionDtoTransformer :: transform)
                .collect(Collectors.toList());
    }
}
