package com.targsoft.task.dao.impl;

import com.targsoft.task.dao.DAOFileReader;
import com.targsoft.task.utils.Files;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DAOFileReaderImpl implements DAOFileReader {

    @Override
    public List<String> readFile(final File file) throws IOException {
        List<String> transactions = new ArrayList<>();

        if (file.isFile() && Files.isCSVFile(file) && !Files.isEmpty(file)) {
            FileReader reader = new FileReader(file);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                transactions.add(scanner.nextLine());
            }
        }

        return transactions;
    }
}
