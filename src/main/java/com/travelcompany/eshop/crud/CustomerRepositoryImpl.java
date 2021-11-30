package com.travelcompany.eshop.crud;

import com.travelcompany.eshop.database.DatabaseFunctions;
import com.travelcompany.eshop.model.Customer;

import java.util.Optional;

public class CustomerRepositoryImpl implements Repository<Customer>{
    private DatabaseFunctions databaseFunctions;

    public CustomerRepositoryImpl(DatabaseFunctions databaseFunctions) {
        this.databaseFunctions = databaseFunctions;
    }

    @Override
    public Customer create(Customer entity) {
        Customer customer = new Customer();
        return customer;
    }

    @Override
    public Optional<Customer> read(int id) {
        return Optional.empty();
    }

    @Override
    public Customer update(int Id, Customer e) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
