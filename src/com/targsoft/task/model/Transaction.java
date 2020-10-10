package com.targsoft.task.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Transaction {

    private String id;
    private LocalDateTime date;
    private double amount;
    private String merchant;
    private TransactionType type;
    private String relatedTransaction;
}
