package com.mas.sonam.transactions.transformer;

import com.mas.sonam.transactions.model.entity.Transaction;
import com.mas.sonam.transactions.model.entity.dto.TransactionDto;
import org.springframework.stereotype.Component;

@Component
public class TransactionToTransactionDtoTransformer {
    public static TransactionDto transform(final Transaction stock) {
        return TransactionDto.builder().id(stock.getId())
                .amount(stock.getAmount())
                .fromAccount(stock.getFromAccount())
                .toAccount(stock.getToAccount())
                .localDate(stock.getLocalDate())
                .build();
    }
}
