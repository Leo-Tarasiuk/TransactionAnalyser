package com.targsoft.task.service.impl;

import com.targsoft.task.exception.DateConvertException;
import com.targsoft.task.model.Statistic;
import com.targsoft.task.model.Transaction;
import com.targsoft.task.model.TransactionType;
import com.targsoft.task.service.TransactionAnalyserService;
import com.targsoft.task.service.factory.ServiceFactory;
import com.targsoft.task.service.factory.impl.ServiceFactoryImpl;
import com.targsoft.task.utils.Utils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionAnalyserServiceImplTest {

    private static final List<Transaction> TRANSACTIONS = new ArrayList<>();

    private static ServiceFactory factory = ServiceFactoryImpl.getInstance();
    private static TransactionAnalyserService transactionAnalyserService = factory.getTransactionAnalyserService();

    @Before
    public void initTransaction() throws Exception {
        Transaction transaction1 = Transaction.builder()
                .id("WLMFRDGD")
                .date(Utils.convertToLocalDateTime("20/08/2018 12:45:33"))
                .amount(59.99)
                .merchant("Kwik-E-Mart")
                .type(TransactionType.PAYMENT)
                .build();

        Transaction transaction2 = Transaction.builder()
                .id("YGXKOEIA")
                .date(Utils.convertToLocalDateTime("20/08/2018 12:46:17"))
                .amount(10.95)
                .merchant("Kwik-E-Mart")
                .type(TransactionType.PAYMENT)
                .build();

        Transaction transaction3 = Transaction.builder()
                .id("AKNBVHMN")
                .date(Utils.convertToLocalDateTime("20/08/2018 13:14:11"))
                .amount(10.95)
                .type(TransactionType.REVERSAL)
                .merchant("Kwik-E-Mart")
                .relatedTransaction("YGXKOEIA")
                .build();

        TRANSACTIONS.add(transaction1);
        TRANSACTIONS.add(transaction2);
        TRANSACTIONS.add(transaction3);
    }

    @Test
    public void getStatisticFirst() throws DateConvertException {
        String fromDate = "20/08/2018 12:00:00";
        String toDate = "20/08/2018 13:00:00";
        String merchant = "Kwik-E-Mart";

        long expectedTransactions = 1;
        double expectedAverage = 59.99;

        Statistic statistic = transactionAnalyserService.getStatistic(TRANSACTIONS,
                Utils.convertToLocalDateTime(fromDate.trim()),
                Utils.convertToLocalDateTime(toDate.trim()),
                merchant.trim());

        assertEquals(expectedTransactions, statistic.getCountTransactions());
        assertEquals(expectedAverage, statistic.getAverage(), 0.01);
    }

    @Test
    public void getStatisticSecond() throws DateConvertException {
        String fromDate = "20/08/2018 12:00:00";
        String toDate = "20/08/2018 13:00:00";
        String merchant = "MacLaren";

        long expectedTransactions = 0;
        double expectedAverage = 0;

        Statistic statistic = transactionAnalyserService.getStatistic(TRANSACTIONS,
                Utils.convertToLocalDateTime(fromDate.trim()),
                Utils.convertToLocalDateTime(toDate.trim()),
                merchant.trim());

        assertEquals(expectedTransactions, statistic.getCountTransactions());
        assertEquals(expectedAverage, statistic.getAverage(), 0.01);
    }
}
