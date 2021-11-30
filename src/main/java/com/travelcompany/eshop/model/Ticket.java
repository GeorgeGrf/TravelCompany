package com.travelcompany.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ticket {
    private int id;
    private int passengerId;
    private int itineraryId;

    public Ticket(int passengerId, int itineraryId) {
        this.passengerId = passengerId;
        this.itineraryId = itineraryId;
    }
}
