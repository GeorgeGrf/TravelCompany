package com.travelcompany.eshop.Excel;

import com.travelcompany.eshop.model.Directory;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Order;
import com.travelcompany.eshop.model.Ticket;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class TicketEXCELImpl implements ExcelFunctions<Order>{

    @Override
    public void writeToExcelFile(List<Order> orders, String filename) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(Directory.FILE_DIRECTORY.getPath() + filename);
            XSSFWorkbook workbook = new XSSFWorkbook()){
            XSSFSheet sheet = workbook.createSheet("Orders");
            int rowIndex = 0;
            for (Order order : orders) {
                XSSFRow row = sheet.createRow(rowIndex);
                XSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(order.getTicket().getId());
                XSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(order.getTicket().getPassengerId());
                XSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(order.getTicket().getItineraryId());
                XSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(order.getPayment().getMethod().toString());
                XSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(order.getPayment().getAmount().toString());
                rowIndex++;
            }
            workbook.write(fileOutputStream);
        } catch (IOException ioException) {
            logger.info("IOException in TicketEXCELImpl write");
        }
    }
}
