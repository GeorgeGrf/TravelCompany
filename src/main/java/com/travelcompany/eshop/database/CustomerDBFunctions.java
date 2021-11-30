package com.travelcompany.eshop.database;

import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.CustomerCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;

public class CustomerDBFunctions implements DatabaseFunctions<Customer> {

    public int insert(Connection connection, Properties sqlCommands, Customer customer) {
        PreparedStatement statement = null;
        int key = 0;
        int rowsAffected=0;
        try {
            statement = connection.prepareStatement(sqlCommands.getProperty("insert.customers"), PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getNationality());
            statement.setString(5, customer.getCategory().toString());

            rowsAffected += statement.executeUpdate();
            System.out.println("Customers inserted:" + rowsAffected);
        } catch (SQLException e) {
            logger.info("SQLException in Customer insert.");
        } finally {
            return key;
        }

    }


    public Customer select(Connection connection,Properties sqlCommands, int id) {
        PreparedStatement statement = null;
        Customer customer = new Customer();
        try {
            statement = connection.prepareStatement(sqlCommands.getProperty("select.customers"));
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            customer.setId(resultSet.getInt("Id"));
            customer.setName(resultSet.getString("Name"));
            customer.setEmail(resultSet.getString("Email"));
            customer.setAddress(resultSet.getString("Address"));
            customer.setNationality(resultSet.getString("Nationality"));
            customer.setCategory(CustomerCategory.getCustomerCategory(resultSet.getString("Category")));
        } catch (SQLException e) {
            logger.info("SQLException in Customer select.");
        } finally {
            return customer;
        }
    }

    public ArrayList<Customer> selectAll(Connection connection,Properties sqlCommands) {
        PreparedStatement statement = null;
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sqlCommands.getProperty("select.all.customers"));
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("Id"));
                customer.setName(resultSet.getString("Name"));
                customer.setEmail(resultSet.getString("Email"));
                customer.setAddress(resultSet.getString("AddressCity"));
                customer.setNationality(resultSet.getString("Nationality"));
                customer.setCategory(CustomerCategory.getCustomerCategory(resultSet.getString("Category")));
                customers.add(customer);
            }
        } catch (SQLException e) {
            logger.info("SQLException in Customer selectAll.");
        } finally {
            return customers;
        }
    }



}


