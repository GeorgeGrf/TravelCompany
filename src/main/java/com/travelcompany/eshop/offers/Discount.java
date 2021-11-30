package com.travelcompany.eshop.offers;

import com.travelcompany.eshop.model.Payment;
import com.travelcompany.eshop.model.Customer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;

@Data
public class Discount extends Offers {
    private HashMap<String, BigDecimal> discounts = new HashMap<>();

    public Discount(){
        discounts.put("BUSINESS",BigDecimal.valueOf(20));
        discounts.put("Credit Card",BigDecimal.valueOf(20));
    }

    @Override
    public void calculateOffer(Customer c, Payment p) {
        for (String s:discounts.keySet()) {
            if(c.getCategory().equals(s) || p.getMethod().equals(s))
                offer.add(discounts.get(s));
        }
    }


    /*
    private final BigDecimal business = BigDecimal.valueOf(20);
    private final BigDecimal individual = BigDecimal.valueOf(0);
    private final BigDecimal credit = BigDecimal.valueOf(20);
    private final BigDecimal cash = BigDecimal.valueOf(0);

 */
}
