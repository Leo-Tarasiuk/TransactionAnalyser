package com.targsoft.task.service.impl;

import com.targsoft.task.model.Transaction;
import com.targsoft.task.model.TransactionType;
import com.targsoft.task.service.FileReaderService;
import com.targsoft.task.service.factory.ServiceFactory;
import com.targsoft.task.service.factory.impl.ServiceFactoryImpl;
import com.targsoft.task.utils.Utils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileReaderServiceImplTest {

    private static final List<Transaction> TRANSACTIONS = new ArrayList<>();
    private static final File FILE = new File("E:/Java/TransactionAnalyser/source/transaction.csv");

    private static ServiceFactory factory = ServiceFactoryImpl.getInstance();
    private static FileReaderService fileReaderService = factory.getFileReaderService();

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

        Transaction transaction4 = Transaction.builder()
                .id("LFVCTEYM")
                .date(Utils.convertToLocalDateTime("20/08/2018 12:50:02"))
                .amount(5.00)
                .merchant("MacLaren")
                .type(TransactionType.PAYMENT)
                .build();

        Transaction transaction5 = Transaction.builder()
                .id("SUOVOISP")
                .date(Utils.convertToLocalDateTime("20/08/2018 13:12:22"))
                .amount(5.00)
                .merchant("Kwik-E-Mart")
                .type(TransactionType.PAYMENT)
                .build();

        Transaction transaction6 = Transaction.builder()
                .id("JYAPKZFZ")
                .date(Utils.convertToLocalDateTime("20/08/2018 14:07:10"))
                .amount(99.50)
                .type(TransactionType.PAYMENT)
                .merchant("MacLaren")
                .build();

        TRANSACTIONS.add(transaction1);
        TRANSACTIONS.add(transaction2);
        TRANSACTIONS.add(transaction4);
        TRANSACTIONS.add(transaction5);
        TRANSACTIONS.add(transaction3);
        TRANSACTIONS.add(transaction6);
    }

    @Test
    public void readFile() throws IOException {
        List<Transaction> transactions = fileReaderService.readFile(FILE);

        assertEquals(TRANSACTIONS, transactions);
    }
}
