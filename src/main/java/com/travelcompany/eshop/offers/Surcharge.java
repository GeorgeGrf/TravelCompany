package com.travelcompany.eshop.offers;

import com.travelcompany.eshop.model.Payment;
import com.travelcompany.eshop.model.Customer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;

@Data
public class Surcharge extends Offers {
    private HashMap<String, BigDecimal> surcharges = new HashMap<>();

    public Surcharge() {
        surcharges.put("INDIVIDUAL", BigDecimal.valueOf(20));
    }

    @Override
    public void calculateOffer(Customer c, Payment p) {
        for (String s:surcharges.keySet()) {
            if(c.getCategory().equals(s) || p.getMethod().equals(s))
                offer.subtract(surcharges.get(s));
        }
    }
//private final BigDecimal individuals = BigDecimal.valueOf(20);

}

