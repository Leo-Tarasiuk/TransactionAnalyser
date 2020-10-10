package com.targsoft.task.service.factory.impl;

import com.targsoft.task.service.FileReaderService;
import com.targsoft.task.service.TransactionAnalyserService;
import com.targsoft.task.service.factory.ServiceFactory;
import com.targsoft.task.service.impl.FileReaderServiceImpl;
import com.targsoft.task.service.impl.TransactionAnalyserServiceImpl;

public class ServiceFactoryImpl implements ServiceFactory {
    private static final ServiceFactoryImpl INSTANCE = new ServiceFactoryImpl();

    private ServiceFactoryImpl() {}

    public static ServiceFactoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public FileReaderService getFileReaderService() {
        return new FileReaderServiceImpl();
    }

    @Override
    public TransactionAnalyserService getTransactionAnalyserService() {
        return new TransactionAnalyserServiceImpl();
    }
}
