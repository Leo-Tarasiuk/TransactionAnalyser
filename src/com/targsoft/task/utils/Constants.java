package com.targsoft.task.utils;

public interface Constants {
    String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    String DATE_PATTERN = "^(([0-2]?[0-9]|3[0-1])/([0]?[1-9]|1[0-2])/[1-2]\\d{3}) " +
            "(20|21|22|23|[0-1]?\\d{1}):([0-5]?\\d{1}):([0-5]?\\d{1})$";
    String FILE_TYPE = ".csv";
    String FILES_PATH = "E:/Java/TransactionAnalyser/source";
    String SEPARATOR = ",";
    byte SIZE_OF_TRANSACTION = 6;
}
