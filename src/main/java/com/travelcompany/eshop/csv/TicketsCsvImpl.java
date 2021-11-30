package com.travelcompany.eshop.csv;

import com.travelcompany.eshop.model.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class TicketsCsvImpl implements CsvFunctions<Order> {

    @Override
    public Optional<List<Order>> readFromCSVFile(String filename) {
        Path path = Paths.get(Directory.FILE_DIRECTORY.getPath() + filename);
        try (Reader reader = Files.newBufferedReader(path);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            ArrayList<Order> orders = new ArrayList<>();
            List<CSVRecord> recordList = csvParser.getRecords();
            for (CSVRecord record : recordList) {
                if (record.getRecordNumber() != 1) {
                    Ticket ticket = new Ticket();
                    ticket.setId(Integer.parseInt(record.get(0)));
                    ticket.setPassengerId(Integer.parseInt(record.get(1)));
                    ticket.setItineraryId(Integer.parseInt(record.get(2)));
                    Payment payment = new Payment();
                    payment.setMethod(record.get(3));
                    payment.setAmount(new BigDecimal(record.get(4)));
                    Order order = new Order(ticket, payment);
                    orders.add(order);
                }
            }
            return Optional.of(orders);
        } catch (IOException ioException) {
            logger.info("IOException in TicketsCsvImpl read");
        }
        return Optional.empty();
    }

    @Override
    public void writeToCSVFile(List<Order> orders, String filename) {
        Path path = Paths.get(Directory.FILE_DIRECTORY.getPath() + filename);
        try (Writer writer = Files.newBufferedWriter(path);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            orders.forEach(order -> {
                try {
                    Ticket ticket = order.getTicket();
                    Payment payment = order.getPayment();
                    csvPrinter.printRecord(ticket.getId(), ticket.getPassengerId(), ticket.getItineraryId(),
                            payment.getMethod(), payment.getAmount());
                } catch (IOException e) {
                    logger.info("IOException in TicketsCsvImpl write 1");
                }
            });
        } catch (IOException ioException) {
            logger.info("IOException in TicketsCsvImpl write 2");
        }
    }



}
