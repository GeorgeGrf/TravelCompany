package com.travelcompany.eshop;

import com.travelcompany.eshop.csv.CustomerCsvImpl;
import com.travelcompany.eshop.csv.ItinerariesCsvImpl;
import com.travelcompany.eshop.csv.TicketsCsvImpl;
import com.travelcompany.eshop.database.*;
import com.travelcompany.eshop.model.*;
import com.travelcompany.eshop.reports.ReportFunctions;
import com.travelcompany.eshop.service.CustomerService;

import javax.swing.filechooser.FileSystemView;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

public class MainApp {
    public static void main(String[] args) {
        Database database = new Database();
        database.startServer();
        database.loadDBDriver();
        Connection connection = database.getConnection();
        database.loadSQLCommands();
        Properties sqlCommands = database.getSqlCommands();

        //create tables
        database.createTables(connection);

        //read customers.csv and write to the table
        DatabaseFunctions customers = new CustomerDBFunctions();
        CustomerCsvImpl customersCsv = new CustomerCsvImpl();
        Stream<Customer> customerStream = customersCsv.readFromCSVFile("customers.csv").stream().flatMap(List::stream);
        Iterator<Customer> customerIterator = customerStream.iterator();
        while(customerIterator.hasNext()){
            customers.insert(connection ,sqlCommands, customerIterator.next());
        }

        //read itineraries.csv and write to the table
        DatabaseFunctions itineraries = new ItineraryDBFunctions();
        ItinerariesCsvImpl itinerariesCsv = new ItinerariesCsvImpl();
        Stream<Itinerary> itineraryStream = itinerariesCsv.readFromCSVFile("itineraries.csv").stream().flatMap(List::stream);
        Iterator<Itinerary> itineraryIterator = itineraryStream.iterator();
        while(itineraryIterator.hasNext()){
            itineraries.insert(connection ,sqlCommands, itineraryIterator.next());
        }

        //read orders.csv and write to the table
        DatabaseFunctions orders = new TicketDBFunctions();
        TicketsCsvImpl ordersCsv = new TicketsCsvImpl();
        Stream<Order> orderStream = ordersCsv.readFromCSVFile("orders.csv").stream().flatMap(List::stream);
        Iterator<Order> orderIterator = orderStream.iterator();
        while(orderIterator.hasNext()){
            orders.insert(connection ,sqlCommands, orderIterator.next());
        }

        //order a ticket, customer service
        CustomerService customerService = new CustomerService();
        Ticket ticket = new Ticket(1,3);
        Payment payment = new Payment("Cash", BigDecimal.valueOf(200.00));
        customerService.orderTicket(ticket,payment);

        //report1+2
        ReportFunctions reportFunctions = new ReportFunctions();
        reportFunctions.reportTicketsPerCustomer(connection,sqlCommands);
        reportFunctions.reportAllItineraries(connection,sqlCommands);


        database.backupTables(connection);
//      database.stopH2Server();
    }
}
