package com.mas.sonam.transactions.transformer;

import com.mas.sonam.transactions.model.entity.Transaction;
import com.mas.sonam.transactions.model.entity.dto.TransactionDto;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class TransactionToTransactionDtoTransformer {
    public static TransactionDto transform(final Transaction stock) {
         TransactionDto dto = new TransactionDto();
         dto.setId(stock.getId());
         dto.setFromAccount(stock.getFromAccount());
         dto.setToAccount(stock.getToAccount());
         dto.setAmount(stock.getAmount());
         dto.setLocalDate(stock.getLocalDate());
         return dto;
    }
}
