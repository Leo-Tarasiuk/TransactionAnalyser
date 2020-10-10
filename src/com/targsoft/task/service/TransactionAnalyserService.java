package com.targsoft.task.service;

import com.targsoft.task.model.Statistic;
import com.targsoft.task.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionAnalyserService {

    Statistic getStatistic(List<Transaction> transactions, LocalDateTime fromDate, LocalDateTime toDate, String merchant);
}
