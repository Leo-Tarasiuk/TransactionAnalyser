package com.targsoft.task.service.impl;

import com.targsoft.task.model.Statistic;
import com.targsoft.task.model.Transaction;
import com.targsoft.task.model.TransactionType;
import com.targsoft.task.service.TransactionAnalyserService;
import com.targsoft.task.utils.Utils;

import java.time.LocalDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TransactionAnalyserServiceImpl implements TransactionAnalyserService {

    @Override
    public Statistic getStatistic(List<Transaction> transactions, LocalDateTime fromDate,
                                  LocalDateTime toDate, String merchant) {
        DoubleSummaryStatistics statistics = searchTransactions(transactions, fromDate, toDate, merchant);

        Statistic statistic = new Statistic();
        statistic.setCountTransactions(statistics.getCount());
        statistic.setAverage(Utils.numberFormat(statistics.getAverage()));

        return statistic;
    }

    private DoubleSummaryStatistics searchTransactions(List<Transaction> transactions, LocalDateTime fromDate,
                                                       LocalDateTime toDate, String merchant) {
        Set<String> relatedTransaction = getRelatedTransaction(transactions);

        return transactions.parallelStream()
                .filter(transaction -> transaction.getType().equals(TransactionType.PAYMENT))
                .filter(transaction -> transaction.getMerchant().equalsIgnoreCase(merchant))
                .filter(transaction -> Utils.compareFromDate(transaction.getDate(), fromDate))
                .filter(transaction -> Utils.compareToDate(transaction.getDate(), toDate))
                .filter(transaction -> !relatedTransaction.contains(transaction.getId()))
                .mapToDouble(Transaction::getAmount)
                .summaryStatistics();
    }

    private Set<String> getRelatedTransaction(List<Transaction> transactions) {
        return transactions.parallelStream()
                .filter(transaction -> transaction.getType().equals(TransactionType.REVERSAL))
                .map(Transaction::getRelatedTransaction)
                .collect(Collectors.toSet());
    }
}
