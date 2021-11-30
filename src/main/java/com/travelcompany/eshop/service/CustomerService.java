package com.travelcompany.eshop.service;


import com.travelcompany.eshop.csv.TicketsCsvImpl;
import com.travelcompany.eshop.database.Database;
import com.travelcompany.eshop.database.DatabaseFunctions;
import com.travelcompany.eshop.database.TicketDBFunctions;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.Order;
import com.travelcompany.eshop.model.Payment;
import com.travelcompany.eshop.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
public class CustomerService {
    private Customer customer;


    public void orderTicket(Ticket ticket, Payment payment){
        Order order = new Order(ticket,payment);
        DatabaseFunctions orders = new TicketDBFunctions();
        Database database = new Database();
        Connection connection = database.getConnection();
        Properties sqlCommands = database.getSqlCommands();
        orders.insert(connection, sqlCommands, order);

    }
}
