package com.travelcompany.eshop.model;


public enum CustomerCategory {

    INDIVIDUAL, BUSINESS;

    public static CustomerCategory getCustomerCategory(String value) {
        String valueUpper = value.toUpperCase();
        for(CustomerCategory c : CustomerCategory.values()) {
            if (valueUpper.equals(c.toString()))
                return c;
        }
        return null;
    }
}
