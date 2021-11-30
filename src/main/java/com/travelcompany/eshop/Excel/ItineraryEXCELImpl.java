package com.travelcompany.eshop.Excel;

import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Directory;
import com.travelcompany.eshop.model.Itinerary;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class ItineraryEXCELImpl implements ExcelFunctions<Itinerary>{

    @Override
    public void writeToExcelFile(List<Itinerary> itineraries, String filename) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(Directory.FILE_DIRECTORY.getPath() + filename);
            XSSFWorkbook workbook = new XSSFWorkbook()){
            XSSFSheet sheet = workbook.createSheet("Customers");
            int rowIndex = 0;
            for (Itinerary itinerary : itineraries) {
                XSSFRow row = sheet.createRow(rowIndex);
                XSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(itinerary.getId());
                XSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(itinerary.getDepartureId());
                XSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(itinerary.getDestinationId());
                XSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(itinerary.getDepartureDate().toString());
                XSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(itinerary.getAirline());
                XSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(itinerary.getCost().toString());
                rowIndex++;
            }
            workbook.write(fileOutputStream);
        } catch (IOException ioException) {
            logger.info("IOException in ItineraryEXCELImpl write");
        }
    }
}
