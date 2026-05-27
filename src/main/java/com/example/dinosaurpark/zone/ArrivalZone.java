package com.example.dinosaurpark.zone;

import java.util.LinkedList;
import java.util.Queue;

import com.example.dinosaurpark.model.Tourist;
import com.example.dinosaurpark.model.TouristStatus;
import com.example.dinosaurpark.persistence.DatabaseService;

public class ArrivalZone implements ParkZone {

    private final Queue<Tourist> tourists;
    private final int maxCapacity;
    private final double ticketPrice;

    public ArrivalZone(int maxCapacity, double ticketPrice) {

        this.maxCapacity = maxCapacity;
        this.ticketPrice = ticketPrice;

        this.tourists = new LinkedList<>();
    }

    public void processBatch(int batchSize,
                             DatabaseService db,
                             double discount) {

        for (int i = 0; i < batchSize && !tourists.isEmpty(); i++) {

            Tourist tourist = tourists.poll();

            tourist.setStatus(TouristStatus.IN_PARK);

            double finalPrice = ticketPrice * (1 - discount);

            tourist.spend(finalPrice);

            db.saveRevenue(
                    "TICKET",
                    finalPrice,
                    tourist.getId(),
                    getName()
            );
        }
    }

    @Override
    public String getName() {
        return "ArrivalZone";
    }

    @Override
    public boolean hasCapacity() {
        return tourists.size() < maxCapacity;
    }

    @Override
    public int getCurrentOccupancy() {
        return tourists.size();
    }

    @Override
    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public void enter(Tourist tourist) {
        tourists.offer(tourist);
    }

    @Override
    public void exit(Tourist tourist) {
        tourists.remove(tourist);
    }
}