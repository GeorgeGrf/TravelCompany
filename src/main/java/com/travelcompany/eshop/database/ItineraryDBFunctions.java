package com.travelcompany.eshop.database;

import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.CustomerCategory;
import com.travelcompany.eshop.model.Itinerary;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Properties;

public class ItineraryDBFunctions implements DatabaseFunctions<Itinerary>{

    public int insert(Connection connection,Properties sqlCommands, Itinerary itinerary) {
        PreparedStatement statement = null;
        int key = 0;
        try {
            statement = connection.prepareStatement(sqlCommands.getProperty("insert.itineraries"), PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, itinerary.getDepartureId());
            statement.setString(2, itinerary.getDestinationId());
            statement.setDate(3, java.sql.Date.valueOf(itinerary.getDepartureDate().toLocalDate()));
            statement.setString(4, itinerary.getAirline());
            statement.setBigDecimal(5, itinerary.getCost());

            int rowsAffected = statement.executeUpdate();
            System.out.println("Itinerary inserted:" + rowsAffected);
        } catch (SQLException e) {
            logger.info("SQLException in Itinerary insert.");
        } finally {
            return key;
        }

    }


    public Itinerary select(Connection connection, Properties sqlCommands, int id) {
        PreparedStatement statement = null;
        Itinerary itinerary = new Itinerary();
        try {
            statement = connection.prepareStatement(sqlCommands.getProperty("select.itineraries"));
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            itinerary.setId(resultSet.getInt("Id"));
            itinerary.setDepartureId(resultSet.getString("DepartureAirportId"));
            itinerary.setDestinationId(resultSet.getString("DestinationAirportId"));
            itinerary.setDepartureDate(resultSet.getTimestamp("DepartureDate").toLocalDateTime());
            itinerary.setAirline(resultSet.getString("Airline"));
            itinerary.setCost(resultSet.getBigDecimal("Cost"));
        } catch (SQLException e) {
            logger.info("SQLException in Itinerary select.");
        } finally {
            return itinerary;
        }
    }

    public ArrayList<Itinerary> selectAll(Connection connection,Properties sqlCommands) {
        PreparedStatement statement = null;
        ArrayList<Itinerary> itineraries = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sqlCommands.getProperty("select.all.itineraries"));
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Itinerary itinerary = new Itinerary();
                itinerary.setId(resultSet.getInt("Id"));
                itinerary.setDepartureId(resultSet.getString("DepartureAirportId"));
                itinerary.setDestinationId(resultSet.getString("DestinationAirportId"));
                itinerary.setDepartureDate(resultSet.getTimestamp("DepartureDate").toLocalDateTime());
                itinerary.setAirline(resultSet.getString("Airline"));
                itinerary.setCost(resultSet.getBigDecimal("Cost"));
                itineraries.add(itinerary);
            }
        } catch (SQLException e) {
            logger.info("SQLException in Order selectAll.");
        } finally {
            return itineraries;
        }
    }
}
