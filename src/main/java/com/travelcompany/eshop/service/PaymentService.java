package com.travelcompany.eshop.service;

import com.travelcompany.eshop.offers.Discount;
import com.travelcompany.eshop.model.Payment;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.offers.Surcharge;

import java.math.BigDecimal;

public class PaymentService {
    private Payment payment;
    private BigDecimal offer;

    public PaymentService(Payment payment) {
        this.payment = payment;
    }


    public BigDecimal calculateAmount(Customer c, Payment p) {
        offer = new BigDecimal(0);
        Discount discount = new Discount();
        Surcharge surcharge = new Surcharge();
        discount.calculateOffer(c,p);
        surcharge.calculateOffer(c,p);
        offer = discount.getOffer();
        return offer;
    }
}
