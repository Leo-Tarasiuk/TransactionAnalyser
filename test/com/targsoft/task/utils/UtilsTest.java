package com.targsoft.task.utils;

import com.targsoft.task.exception.DateConvertException;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void convertToLocalDateTime() throws DateConvertException {
        String date = "20/08/2018 13:14:11";

        LocalDateTime expected = LocalDateTime.of(LocalDate.of(2018, 8, 20),
                LocalTime.of(13, 14, 11));
        expected.format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT));

        LocalDateTime result = Utils.convertToLocalDateTime(date);

        assertEquals(expected, result);
    }

    @Test
    public void compareFromDateFirst() throws DateConvertException {
        String date = "20/08/2018 13:14:11";
        String fromDate = "20/08/2018 12:00:00";

        boolean result = Utils.compareFromDate(Utils.convertToLocalDateTime(date), Utils.convertToLocalDateTime(fromDate));

        assertTrue(result);
    }

    @Test
    public void compareFromDateSecond() throws DateConvertException {
        String date = "20/08/2018 13:14:11";
        String fromDate = "20/08/2018 13:14:11";

        boolean result = Utils.compareFromDate(Utils.convertToLocalDateTime(date), Utils.convertToLocalDateTime(fromDate));

        assertTrue(result);
    }

    @Test
    public void compareToDateFirst() throws DateConvertException {
        String date = "20/08/2018 13:14:11";
        String toDate = "20/08/2018 14:00:00";

        boolean result = Utils.compareToDate(Utils.convertToLocalDateTime(date), Utils.convertToLocalDateTime(toDate));

        assertTrue(result);
    }

    @Test
    public void compareToDateSecond() throws DateConvertException {
        String date = "20/08/2018 13:14:11";
        String toDate = "20/08/2018 13:14:11";

        boolean result = Utils.compareToDate(Utils.convertToLocalDateTime(date), Utils.convertToLocalDateTime(toDate));

        assertFalse(result);
    }

    @Test
    public void numberFormat() {
        double value = 5.1495;
        double expected = 5.15;

        double result = Utils.numberFormat(value);

        assertEquals(expected, result, 0.01);
    }

}
