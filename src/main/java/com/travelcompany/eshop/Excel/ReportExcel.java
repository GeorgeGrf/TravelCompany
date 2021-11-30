package com.travelcompany.eshop.Excel;

import com.travelcompany.eshop.model.Directory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

public class ReportExcel {
    static final Logger logger = Logger.getLogger(ReportExcel.class.getName());


    public void writeReportTicketsPerCustomerExcel(List<Integer> id, List<Integer> count, List<BigDecimal> sum, String filename) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(Directory.FILE_DIRECTORY.getPath() + filename);
             XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("ReportTicketsPerCustomer");
            for(int i=0; i<id.size(); i++){
                XSSFRow row = sheet.createRow(i);
                XSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(id.get(i));
                XSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(count.get(i));
                XSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(sum.get(i).toString());
            }
            workbook.write(fileOutputStream);
        } catch (IOException ioException) {
            logger.info("IOException in ReportExcel writeReportTicketsPerCustomerExcel");
        }
    }

    public void writeAllItinerariesExcel(List<String> departureId, List<String> destinationId, List<Integer> count, String filename) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(Directory.FILE_DIRECTORY.getPath() + filename);
             XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("ReportAllItineraries");
            for(int i=0; i<departureId.size(); i++){
                XSSFRow row = sheet.createRow(i);
                XSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(departureId.get(i));
                XSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(destinationId.get(i));
                XSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(count.get(i));
            }
            workbook.write(fileOutputStream);
        } catch (IOException ioException) {
            logger.info("IOException in ReportExcel writeAllItinerariesExcel");
        }
    }
}
