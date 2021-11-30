package com.travelcompany.eshop.model;

import java.util.Locale;

public enum PaymentMethod {
    CASH, CREDIT_CARD;

    public static PaymentMethod getPaymentMethod(String value) {
        String valueUpper = value.toUpperCase();
        for(PaymentMethod p : PaymentMethod.values()) {
            if(valueUpper.equals(p.toString()))
                return p;
        }
        return null;
    }
}
