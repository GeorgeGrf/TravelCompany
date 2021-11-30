package com.travelcompany.eshop.csv;

import com.travelcompany.eshop.Excel.ExcelFunctions;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


public interface CsvFunctions<T> {
    static final Logger logger = Logger.getLogger(ExcelFunctions.class.getName());
    Optional<List<T>> readFromCSVFile(String filename);
    void writeToCSVFile(List<T> objects, String filename);
}
