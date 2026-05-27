package com.example.dinosaurpark.zone;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.dinosaurpark.model.Tourist;
import com.example.dinosaurpark.persistence.DatabaseService;

public class CentralHub implements ParkZone {

    private final List<Tourist> tourists;

    private final int maxCapacity;
    private final double souvenirPrice;
    private final double purchaseProbability;

    public CentralHub(int maxCapacity,
                      double souvenirPrice,
                      double purchaseProbability) {

        this.maxCapacity = maxCapacity;
        this.souvenirPrice = souvenirPrice;
        this.purchaseProbability = purchaseProbability;

        this.tourists = new ArrayList<>();
    }

    public void offerSouvenirs(Tourist tourist,
                               Random rng,
                               DatabaseService db,
                               double discount) {

        if (rng.nextDouble() < purchaseProbability) {

            double finalPrice = souvenirPrice * (1 - discount);

            tourist.spend(finalPrice);

            db.saveRevenue(
                    "SOUVENIR",
                    finalPrice,
                    tourist.getId(),
                    getName()
            );
        }
    }

    @Override
    public String getName() {
        return "CentralHub";
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