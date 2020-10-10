package com.targsoft.task.utils;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class FilesTest {

    private static final File FILE = new File(Constants.FILES_PATH + "/transaction.csv");

    @Test
    public void isEmpty() {
        boolean result = Files.isEmpty(FILE);
        assertFalse(result);
    }

    @Test
    public void isCSVFileFirst() {
        boolean result = Files.isCSVFile(FILE);
        assertTrue(result);
    }

    @Test
    public void isCSVFileSecond() throws IOException {
        File file = File.createTempFile("test", ".txt", new File(Constants.FILES_PATH));

        boolean result = Files.isCSVFile(file);
        assertFalse(result);

        file.deleteOnExit();
    }
}
