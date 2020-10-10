package com.targsoft.task;

import com.targsoft.task.exception.DateConvertException;
import com.targsoft.task.model.Statistic;
import com.targsoft.task.model.Transaction;
import com.targsoft.task.service.FileReaderService;
import com.targsoft.task.service.TransactionAnalyserService;
import com.targsoft.task.service.factory.ServiceFactory;
import com.targsoft.task.service.factory.impl.ServiceFactoryImpl;
import com.targsoft.task.utils.Constants;
import com.targsoft.task.utils.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransactionAnalyserStarter {

    private static final File FILE = new File(Constants.FILES_PATH);
    private static final List<Transaction> TRANSACTIONS = new ArrayList<>();

    private static String fromDate;
    private static String toDate;
    private static String merchant;

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static ServiceFactory factory = ServiceFactoryImpl.getInstance();
    private static FileReaderService fileReaderService = factory.getFileReaderService();
    private static TransactionAnalyserService transactionAnalyserService = factory.getTransactionAnalyserService();

    public static void main(String[] args) {
        System.out.println("Transaction Analysis System is ready to use");
        while (true) {
            System.out.println("Please input the arguments");
            System.out.println("Note: Allowed date format is " + Constants.DATE_FORMAT);

            try {
                readInput();
            } catch (IOException e) {
                System.err.println("Please enter correct arguments! " + e.getMessage());
            }

            List<File> files = getCSVFiles();

            files.forEach(file -> {
                try {
                    TRANSACTIONS.addAll(fileReaderService.readFile(file));
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            });

            try {
                Statistic statistic = transactionAnalyserService.getStatistic(TRANSACTIONS,
                        Utils.convertToLocalDateTime(fromDate.trim()),
                        Utils.convertToLocalDateTime(toDate.trim()),
                        merchant.trim());
                System.out.println("Number of transactions = " + statistic.getCountTransactions());
                System.out.println("Average Transaction Value = " + statistic.getAverage() + "\n\n\n");
            } catch (DateConvertException e) {
                System.err.println(e.getMessage());
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static List<File> getCSVFiles() {
        List<File> files = new ArrayList<>();
        File[] children = FILE.listFiles();
        if (children != null) {
            files.addAll(Arrays.asList(children));
        }
        return files;
    }

    private static void readInput() throws IOException {
        System.out.print("from date: ");
        fromDate = reader.readLine();
        System.out.print("to date: ");
        toDate = reader.readLine();
        System.out.print("merchant: ");
        merchant = reader.readLine();
    }
}
