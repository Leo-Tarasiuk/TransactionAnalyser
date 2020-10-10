package com.targsoft.task.utils;

import com.targsoft.task.exception.DateConvertException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static LocalDateTime convertToLocalDateTime(String date) throws DateConvertException {
        if (date == null || date.isEmpty() || !date.matches(Constants.DATE_PATTERN)) {
            throw new DateConvertException("Date convert exception");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        return LocalDateTime.parse(date, formatter);
    }

    public static boolean compareFromDate(LocalDateTime transactionDate, LocalDateTime fromDate) {
        return transactionDate.compareTo(fromDate) > 0 || transactionDate.compareTo(fromDate) == 0;
    }

    public static boolean compareToDate(LocalDateTime transactionDate, LocalDateTime toDate) {
        return transactionDate.compareTo(toDate) < 0;
    }

    public static double numberFormat(double value) {
        return Double.parseDouble(String.format("%.2f", value).replace(",","."));
    }
}
