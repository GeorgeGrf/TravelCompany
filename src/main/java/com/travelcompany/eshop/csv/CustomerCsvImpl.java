package com.travelcompany.eshop.csv;

import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.CustomerCategory;
import com.travelcompany.eshop.model.Directory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CustomerCsvImpl implements CsvFunctions<Customer> {


    @Override
    public Optional<List<Customer>> readFromCSVFile(String filename) {
        Path path = Paths.get(Directory.FILE_DIRECTORY.getPath() + filename);
        try(Reader reader = Files.newBufferedReader(path);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)){
            ArrayList<Customer> customers = new ArrayList<>();
            List<CSVRecord> recordList = csvParser.getRecords();
            for (CSVRecord record : recordList) {
                if (record.getRecordNumber() != 1) {
                    Customer customer = new Customer();
                    customer.setId(Integer.parseInt(record.get(0)));
                    customer.setName(record.get(1));
                    customer.setEmail(record.get(2));
                    customer.setAddress(record.get(3));
                    customer.setNationality(record.get(4));
                    customer.setCategory(CustomerCategory.getCustomerCategory(record.get(5)));
                    customers.add(customer);
                }
            }
            return Optional.of(customers);
        } catch (IOException ioException) {
            logger.info("IOException in CustomerCsvImpl read");
            ioException.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void writeToCSVFile(List<Customer> customers, String filename) {
        Path path = Paths.get(Directory.FILE_DIRECTORY.getPath() + filename);
        try(Writer writer = Files.newBufferedWriter(path);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
            customers.forEach(customer -> {
                try {
                    csvPrinter.printRecord(customer.getId(), customer.getName(), customer.getEmail(),
                            customer.getAddress(), customer.getNationality(),customer.getCategory());
                } catch (IOException e) {
                    logger.info("IOException in CustomerCsvImpl write 1");
                }
            });
        } catch (IOException ioException) {
            logger.info("IOException in CustomerCsvImpl write 2");
        }
    }


}
