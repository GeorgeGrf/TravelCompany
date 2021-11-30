package com.travelcompany.eshop.Excel;

import com.travelcompany.eshop.csv.CsvFunctions;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Directory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class CustomerEXCELImpl implements ExcelFunctions<Customer>{

    @Override
    public void writeToExcelFile(List<Customer> customers, String filename) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(Directory.FILE_DIRECTORY.getPath() + filename);
            XSSFWorkbook workbook = new XSSFWorkbook()){
            XSSFSheet sheet = workbook.createSheet("Customers");
            int rowIndex = 0;
            for (Customer customer : customers) {
                XSSFRow row = sheet.createRow(rowIndex);
                XSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(customer.getId());
                XSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(customer.getName());
                XSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(customer.getEmail());
                XSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(customer.getAddress());
                XSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(customer.getNationality());
                XSSFCell cell5 = row.createCell(5);
                cell4.setCellValue(customer.getCategory().toString());
                rowIndex++;
            }
            workbook.write(fileOutputStream);
        } catch (IOException ioException) {
            logger.info("IOException in CustomerEXCELImpl write");
        }
    }
}
