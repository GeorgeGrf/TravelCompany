package com.travelcompany.eshop.reports;

import com.travelcompany.eshop.Excel.ReportExcel;
import com.travelcompany.eshop.database.DatabaseFunctions;
import com.travelcompany.eshop.model.Order;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class ReportFunctions {
    static final Logger logger = Logger.getLogger(DatabaseFunctions.class.getName());

    public void reportTicketsPerCustomer(Connection connection, Properties sqlCommands) {
        PreparedStatement statement = null;
        List<Integer> id = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        List<BigDecimal> sumAmount = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sqlCommands.getProperty("report.tickets.per.customer"));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id.add(resultSet.getInt("PassengerId"));
                count.add(resultSet.getInt("Count"));
                sumAmount.add(resultSet.getBigDecimal("SumAmount"));
            }
            ReportExcel reportExcel = new ReportExcel();
            reportExcel.writeReportTicketsPerCustomerExcel(id, count, sumAmount,
                    "ReportTicketsPerCustomer.xlsx");
        } catch (SQLException e) {
            logger.info("SQLException in reportTicketsPerCustomer report.");
        }
    }

    public void reportAllItineraries(Connection connection, Properties sqlCommands) {
        PreparedStatement statement = null;
        List<String> departureId = new ArrayList<>();
        List<String> destinationId = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sqlCommands.getProperty("report.itineraries"));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                departureId.add(resultSet.getString("DepartureAirportId"));
                destinationId.add(resultSet.getString("DestinationAirportId"));
                count.add(resultSet.getInt("Count"));
            }
            ReportExcel reportExcel = new ReportExcel();
            reportExcel.writeAllItinerariesExcel(departureId, destinationId, count,
                    "ReportAllItineraries.xlsx");
        } catch (SQLException e) {
            logger.info("SQLException in reportAllItineraries report.");
        }
    }

}
