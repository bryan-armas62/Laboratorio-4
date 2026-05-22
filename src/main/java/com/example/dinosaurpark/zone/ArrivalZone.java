package com.example.dinosaurpark.zone;

import com.example.dinosaurpark.model.Tourist;
import com.example.dinosaurpark.model.TouristStatus;

import java.util.List;

public class ArrivalZone implements ParkZone {

    private final int maxCapacity;
    private final double ticketPrice;

    public ArrivalZone(int maxCapacity, double ticketPrice) {
        this.maxCapacity = maxCapacity;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String getName() {
        return "Arrival Zone";
    }

    public double processTourists(List<Tourist> tourists) {

        double total = 0;

        for (Tourist tourist : tourists) {
            tourist.setStatus(TouristStatus.IN_PARK);
            tourist.spend(ticketPrice);
            total += ticketPrice;
        }

        return total;
    }
}