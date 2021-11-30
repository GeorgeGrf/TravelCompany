package com.travelcompany.eshop.csv;

import com.travelcompany.eshop.model.Directory;
import com.travelcompany.eshop.model.Itinerary;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class ItinerariesCsvImpl implements CsvFunctions<Itinerary>{

    @Override
    public Optional<List<Itinerary>> readFromCSVFile(String filename) {
        Path path = Paths.get(Directory.FILE_DIRECTORY.getPath() + filename);
        try(Reader reader = Files.newBufferedReader(path);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)){
            ArrayList<Itinerary> itineraries = new ArrayList<>();
            List<CSVRecord> recordList = csvParser.getRecords();
            for (CSVRecord record : recordList) {
                if (record.getRecordNumber() != 1) {
                    Itinerary itinerary = new Itinerary();
                    itinerary.setId(Integer.parseInt(record.get(0)));
                    itinerary.setDepartureId(record.get(1));
                    itinerary.setDestinationId(record.get(2));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime localDateTime = LocalDateTime.parse(record.get(3),formatter);
                    itinerary.setDepartureDate(localDateTime);
                    itinerary.setAirline(record.get(4));
                    itinerary.setCost(new BigDecimal(record.get(5)));
                    itineraries.add(itinerary);
                }
            }
            return Optional.of(itineraries);
        } catch (IOException ioException) {
            logger.info("IOException in ItinerariesCsvImpl read");
        }
        return Optional.empty();
    }

    @Override
    public void writeToCSVFile(List<Itinerary> itineraries, String filename) {
        Path path = Paths.get(Directory.FILE_DIRECTORY.getPath() + filename);
        try(Writer writer = Files.newBufferedWriter(path);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
            itineraries.forEach(itinerary -> {
                try {
                    csvPrinter.printRecord(itinerary.getId(), itinerary.getDepartureId(), itinerary.getDestinationId(),
                            itinerary.getDepartureDate(), itinerary.getAirline(), itinerary.getCost());
                } catch (IOException e) {
                    logger.info("IOException in ItinerariesCsvImpl write 1");
                }
            });
        } catch (IOException ioException) {
            logger.info("IOException in ItinerariesCsvImpl write 2");
        }
    }


}
