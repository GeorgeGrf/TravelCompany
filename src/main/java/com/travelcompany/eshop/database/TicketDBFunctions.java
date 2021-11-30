package com.travelcompany.eshop.database;

import com.travelcompany.eshop.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class TicketDBFunctions implements DatabaseFunctions<Order>{

    public int insert(Connection connection,Properties sqlCommands, Order order) {
        PreparedStatement statement = null;
        int key = 0;
        try {
            statement = connection.prepareStatement(sqlCommands.getProperty("insert.tickets"), PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getTicket().getPassengerId());
            statement.setInt(2, order.getTicket().getItineraryId());
            statement.setString(3, order.getPayment().getMethod());
            statement.setBigDecimal(4, order.getPayment().getAmount());

            int rowsAffected = statement.executeUpdate();
            System.out.println("Ticket inserted:" + rowsAffected);
        } catch (SQLException e) {
            logger.info("SQLException in Ticket insert.");
        } finally {
            return key;
        }

    }


    public Order select(Connection connection, Properties sqlCommands, int id) {
        PreparedStatement statement = null;
        Order order = new Order();
        try {
            statement = connection.prepareStatement(sqlCommands.getProperty("select.tickets"));
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            order.getTicket().setId(resultSet.getInt("Id"));
            order.getTicket().setPassengerId(resultSet.getInt("PassengerId"));
            order.getTicket().setItineraryId(resultSet.getInt("ItineraryId"));
            order.getPayment().setMethod(resultSet.getString("PaymentMethod"));
            order.getPayment().setAmount(resultSet.getBigDecimal("Amount"));
        } catch (SQLException e) {
            logger.info("SQLException in Ticket select.");
        } finally {
            return order;
        }
    }

    public ArrayList<Order> selectAll(Connection connection,Properties sqlCommands) {
        PreparedStatement statement = null;
        ArrayList<Order> orders = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sqlCommands.getProperty("select.all.tickets"));
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Ticket ticket = new Ticket();
                Payment payment = new Payment();
                ticket.setId(resultSet.getInt("Id"));
                ticket.setPassengerId(resultSet.getInt("PassengerId"));
                ticket.setItineraryId(resultSet.getInt("ItineraryId"));
                payment.setMethod(resultSet.getString("PaymentMethod"));
                payment.setAmount(resultSet.getBigDecimal("Amount"));
                Order order = new Order(ticket,payment);
                orders.add(order);
            }
        } catch (SQLException e) {
            logger.info("SQLException in Order selectAll.");
        } finally {
            return orders;
        }
    }
}
