package com.targsoft.task.service.impl;

import com.targsoft.task.dao.DAOFileReader;
import com.targsoft.task.dao.impl.DAOFileReaderImpl;
import com.targsoft.task.exception.DateConvertException;
import com.targsoft.task.exception.ParsingException;
import com.targsoft.task.model.Transaction;
import com.targsoft.task.model.TransactionType;
import com.targsoft.task.service.FileReaderService;
import com.targsoft.task.utils.Constants;
import com.targsoft.task.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderServiceImpl implements FileReaderService {

    private DAOFileReader daoFileReader = new DAOFileReaderImpl();

    @Override
    public List<Transaction> readFile(final File file) throws IOException {
        List<Transaction> transactions = new ArrayList<>();

        try {
            List<String> strTransactions = daoFileReader.readFile(file);
            convertData(strTransactions, transactions);
        } catch (IOException e) {
            throw new IOException("File read exception. " + e.getMessage());
        }

        return transactions;
    }

    private void convertData(List<String> strTransactions, List<Transaction> transactions) {
        strTransactions.forEach(data -> {
            String[] inf = data.split(Constants.SEPARATOR);

            try {
                Transaction transaction = null;
                if (inf.length == Constants.SIZE_OF_TRANSACTION - 1 || inf.length == Constants.SIZE_OF_TRANSACTION) {
                    transaction = Transaction.builder()
                            .id(inf[0].trim())
                            .date(Utils.convertToLocalDateTime(inf[1].trim()))
                            .amount(Double.parseDouble(inf[2].trim()))
                            .merchant(inf[3].trim())
                            .type(TransactionType.valueOf(inf[4].trim()))
                            .build();
                }

                if (inf.length == Constants.SIZE_OF_TRANSACTION) {
                    transaction.setRelatedTransaction(inf[5].trim());
                    transactions.add(transaction);
                }

            } catch (DateConvertException e) {
                throw new ParsingException("Exception parsing for a transaction. " + e.getMessage());
            }

        });
    }
}
