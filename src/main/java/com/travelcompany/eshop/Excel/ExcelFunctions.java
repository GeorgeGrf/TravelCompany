package com.travelcompany.eshop.Excel;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public interface ExcelFunctions<T> {
    static final Logger logger = Logger.getLogger(ExcelFunctions.class.getName());
    void writeToExcelFile(List<T> objects, String filename);
}
