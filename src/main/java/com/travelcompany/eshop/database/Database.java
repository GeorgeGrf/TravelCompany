package com.travelcompany.eshop.database;

import com.travelcompany.eshop.Excel.ReportExcel;
import com.travelcompany.eshop.csv.CustomerCsvImpl;
import com.travelcompany.eshop.csv.ItinerariesCsvImpl;
import com.travelcompany.eshop.csv.TicketsCsvImpl;
import com.travelcompany.eshop.model.*;
import com.travelcompany.eshop.reports.ReportFunctions;
import com.travelcompany.eshop.service.CustomerService;
import org.apache.xmlbeans.GDate;
import org.h2.tools.Server;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Database {
    private static final String DB_CONNECTION_URL_MEMORY_MODE = "jdbc:h2:mem:sample";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";
    protected static final Properties sqlCommands = new Properties();
    private static final Logger logger = Logger.getLogger(Database.class.getName());

    private static Server server;
    // TODO: 25/11/2021 think about having each method create its own connection, and then terminate it. Instead of
    //  having one connection open for all
    private Connection connection = null;


    public void startServer() {
        try {
            server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
            server.start();
        } catch (SQLException e) {
            logger.info("SQLException in DatabaseFunctions in startServer.");
        }

        System.out.println("Server started... " + server.getStatus());
    }

    public void loadDBDriver() {
        org.h2.Driver.load();
        System.out.println("Driver loaded!");
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_CONNECTION_URL_MEMORY_MODE, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            logger.info("SQLException in DatabaseFunctions in getConnection.");
            return null;
        }
        return connection;
    }

    public void loadSQLCommands() {
        InputStream inputStream = DatabaseFunctions.class.getClassLoader().getResourceAsStream("sql.properties");
        try {
            sqlCommands.load(inputStream);
        } catch (IOException e) {
            logger.info("SQL properties file failed to load!");
        }
        System.out.println("SQL properties file loaded!");
    }


    public Properties getSqlCommands(){

        return sqlCommands;
    }


    public void createTables(Connection connection) {
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
            e.printStackTrace();
        }
    }

    public void dropTables(Connection connection){
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

    public void backupTables(Connection connection){
        ArrayList<Customer> customers = new CustomerDBFunctions().selectAll(connection,sqlCommands);
        new CustomerCsvImpl().writeToCSVFile(customers, "customersBackup.csv");

        ArrayList<Itinerary> itineraries = new ItineraryDBFunctions().selectAll(connection,sqlCommands);
        new ItinerariesCsvImpl().writeToCSVFile(itineraries, "itinerariesBackup.csv");

        ArrayList<Order> orders = new TicketDBFunctions().selectAll(connection,sqlCommands);
        new TicketsCsvImpl().writeToCSVFile(orders, "ordersBackup.csv");

    }

}
