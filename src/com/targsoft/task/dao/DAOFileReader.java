package com.targsoft.task.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface DAOFileReader {

    List<String> readFile(File file) throws IOException;
}
