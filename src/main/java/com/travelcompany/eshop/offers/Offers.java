package com.travelcompany.eshop.offers;


import com.travelcompany.eshop.model.Payment;
import com.travelcompany.eshop.model.Customer;
import lombok.Data;

import java.math.BigDecimal;

@Data
public abstract class Offers {
    protected BigDecimal offer = new BigDecimal(0);
    public abstract void calculateOffer(Customer c, Payment p);


}
