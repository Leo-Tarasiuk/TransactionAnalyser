package com.targsoft.task.service.factory;

import com.targsoft.task.service.FileReaderService;
import com.targsoft.task.service.TransactionAnalyserService;

public interface ServiceFactory {

    FileReaderService getFileReaderService();

    TransactionAnalyserService getTransactionAnalyserService();
}
