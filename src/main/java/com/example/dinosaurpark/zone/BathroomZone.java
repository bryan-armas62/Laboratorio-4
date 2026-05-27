package com.example.dinosaurpark.zone;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.dinosaurpark.model.Tourist;
import com.example.dinosaurpark.persistence.DatabaseService;

public class BathroomZone implements ParkZone {

    private final List<Tourist> tourists;

    private final int maxCapacity;
    private final int useDurationSteps;

    private final double spaPrice;
    private final double spaProbability;

    public BathroomZone(int maxCapacity,
                        int useDurationSteps,
                        double spaPrice,
                        double spaProbability) {

        this.maxCapacity = maxCapacity;
        this.useDurationSteps = useDurationSteps;
        this.spaPrice = spaPrice;
        this.spaProbability = spaProbability;

        this.tourists = new ArrayList<>();
    }

    public void useServices(Tourist tourist,
                            Random rng,
                            DatabaseService db,
                            double discount) {

        if (rng.nextDouble() < spaProbability) {

            double finalPrice = spaPrice * (1 - discount);

            tourist.spend(finalPrice);

            db.saveRevenue(
                    "SPA_SERVICE",
                    finalPrice,
                    tourist.getId(),
                    getName()
            );
        }
    }

    public int getUseDurationSteps() {
        return useDurationSteps;
    }

    @Override
    public String getName() {
        return "BathroomZone";
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
        tourists.add(tourist);
    }

    @Override
    public void exit(Tourist tourist) {
        tourists.remove(tourist);
    }
}