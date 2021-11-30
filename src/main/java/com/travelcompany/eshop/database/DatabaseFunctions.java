package com.travelcompany.eshop.database;

import com.travelcompany.eshop.csv.CsvFunctions;
import com.travelcompany.eshop.csv.CustomerCsvImpl;
import com.travelcompany.eshop.csv.ItinerariesCsvImpl;
import com.travelcompany.eshop.csv.TicketsCsvImpl;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Order;
import org.h2.tools.Server;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

public interface DatabaseFunctions<T> {
    static final Logger logger = Logger.getLogger(DatabaseFunctions.class.getName());


    int insert(Connection connection,Properties sqlCommands, T object);
    T select(Connection connection,Properties sqlCommands, int id);
    ArrayList<T> selectAll(Connection connection,Properties sqlCommands);

/*
    private void loadSQLCommands() {
        InputStream inputStream = DatabaseFunctions.class.getClassLoader().getResourceAsStream("sql.properties");
        try {
            sqlCommands.load(inputStream);
        } catch (IOException e) {
            logger.info("SQL properties file failed to load!");
        }
        System.out.println("SQL properties file loaded!");
    }

    private void createTables(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.addBatch(sqlCommands.getProperty("create.itineraries"));
            statement.addBatch(sqlCommands.getProperty("create.customers"));
            statement.addBatch(sqlCommands.getProperty("create.tickets"));
            int[] rowsAffected = statement.executeBatch();
            System.out.println("Successful:" + rowsAffected);
        } catch (SQLException e) {
            logger.info("SQLException in DatabaseFunctions in createTables.");
        }
    }

    private void dropTables(Connection connection){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.addBatch(sqlCommands.getProperty("drop.itineraries"));
            statement.addBatch(sqlCommands.getProperty("drop.customers"));
            statement.addBatch(sqlCommands.getProperty("drop.tickets"));
            int[] rowsAffected = statement.executeBatch();
            System.out.println("Successful:" + rowsAffected);
        } catch (SQLException e) {
            logger.info("SQLException in DatabaseFunctions in dropTables.");
        }

    }

    private void backupTables(Connection connection){
        ArrayList<Customer> customers = new CustomerDBFunctions().selectAll(connection);
        new CustomerCsvImpl().writeToCSVFile(customers, "customers.csv");

        ArrayList<Itinerary> itineraries = new ItineraryDBFunctions().selectAll(connection);
        new ItinerariesCsvImpl().writeToCSVFile(itineraries, "itineraries.csv");

        ArrayList<Order> orders = new TicketDBFunctions().selectAll(connection);
        new TicketsCsvImpl().writeToCSVFile(orders, "orders.csv");
        
    }
 */

}