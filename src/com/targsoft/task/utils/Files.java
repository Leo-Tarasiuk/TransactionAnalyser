package com.targsoft.task.utils;

import java.io.File;

public class Files {

    public static boolean isEmpty(File file) {
        return file.length() == 0;
    }

    public static boolean isCSVFile(File file) {
        return file.getName().endsWith(Constants.FILE_TYPE);
    }

}
