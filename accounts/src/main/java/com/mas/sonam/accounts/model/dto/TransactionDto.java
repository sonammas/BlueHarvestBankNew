package com.mas.sonam.accounts.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.ws.BindingType;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionDto {
    private Long id;

    @JsonIgnore
    private LocalDate localDate;

    private int fromAccount;

    private int toAccount;

    private BigDecimal amount;

}
