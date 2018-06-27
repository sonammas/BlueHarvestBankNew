package com.mas.sonam.accounts.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mas.sonam.accounts.model.AccountType;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder
@Data
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private static int accountNumberGenerator = 10000;

    private int accountNumber;

    private LocalDate accountOpeningDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;

    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    private BigDecimal balance;

    public Account() {
        accountNumberGenerator++;
        accountNumber = accountNumberGenerator;
    }
}
