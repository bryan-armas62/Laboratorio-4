package com.example.dinosaurpark.zone;

import java.util.Random;

import com.example.dinosaurpark.model.Tourist;
import com.example.dinosaurpark.persistence.DatabaseService;

public class PowerPlant implements ParkZone {

    private double energy;
    private boolean operational;

    private final double consumptionPerStep;
    private final double failureProbability;
    private final double repairCost;

    public PowerPlant(double initialEnergy,
                      double consumptionPerStep,
                      double failureProbability,
                      double repairCost) {

        this.energy = initialEnergy;
        this.consumptionPerStep = consumptionPerStep;
        this.failureProbability = failureProbability;
        this.repairCost = repairCost;

        this.operational = true;
    }

    public void tick(Random rng, DatabaseService db) {

        energy -= consumptionPerStep;

        if (rng.nextDouble() < failureProbability) {
            triggerFailure(db);
        }
    }

    public void triggerFailure(DatabaseService db) {

        operational = false;

        db.saveExpense(
                "BLACKOUT",
                repairCost,
                "PowerPlant failure"
        );
    }

    public void repair() {
        operational = true;
    }

    public boolean isOperational() {
        return operational;
    }

    public double getEnergy() {
        return energy;
    }

    @Override
    public String getName() {
        return "PowerPlant";
    }

    @Override
    public boolean hasCapacity() {
        return true;
    }

    @Override
    public int getCurrentOccupancy() {
        return 0;
    }

    @Override
    public int getMaxCapacity() {
        return 0;
    }

    @Override
    public void enter(Tourist tourist) {
    }

    @Override
    public void exit(Tourist tourist) {
    }
}