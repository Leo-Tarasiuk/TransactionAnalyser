package com.targsoft.task.service;

import com.targsoft.task.model.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileReaderService {

    List<Transaction> readFile(File file) throws IOException;
}
