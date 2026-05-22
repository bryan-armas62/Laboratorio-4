package com.example.dinosaurpark.zone;

import java.util.Random;

public class PowerPlant implements ParkZone {

    private double energy;
    private final double maxEnergy;
    private final double consumptionPerStep;
    private final double failureProbability;
    private boolean operational;
    private int failures;

    public PowerPlant(double initialEnergy,
                      double consumptionPerStep,
                      double failureProbability) {

        this.energy = initialEnergy;
        this.maxEnergy = initialEnergy;
        this.consumptionPerStep = consumptionPerStep;
        this.failureProbability = failureProbability;
        this.operational = true;
        this.failures = 0;
    }

    @Override
    public String getName() {
        return "Power Plant";
    }

    public void tick(Random random) {

        if (!operational) {
            System.out.println("[POWER PLANT] System offline");
            return;
        }

        consumeEnergy();

        if (random.nextDouble() < failureProbability) {
            shutdown();
        }

        if (energy <= 0) {
            shutdown();
        }
    }

    public void consumeEnergy() {

        energy -= consumptionPerStep;

        if (energy < 0) {
            energy = 0;
        }
    }

    public void recharge(double amount) {

        if (amount <= 0) {
            return;
        }

        energy += amount;

        if (energy > maxEnergy) {
            energy = maxEnergy;
        }
    }

    public void shutdown() {

        operational = false;
        failures++;

        System.out.println("[POWER PLANT] FAILURE DETECTED");
    }

    public void repair() {

        operational = true;

        if (energy <= 0) {
            energy = maxEnergy * 0.5;
        }

        System.out.println("[POWER PLANT] SYSTEM REPAIRED");
    }

    public boolean hasEnoughEnergy(double required) {
        return energy >= required;
    }

    public boolean isOperational() {
        return operational;
    }

    public double getEnergy() {
        return energy;
    }

    public double getMaxEnergy() {
        return maxEnergy;
    }

    public double getConsumptionPerStep() {
        return consumptionPerStep;
    }

    public double getFailureProbability() {
        return failureProbability;
    }

    public int getFailures() {
        return failures;
    }

    @Override
    public String toString() {
        return "PowerPlant{" +
                "energy=" + energy +
                ", operational=" + operational +
                ", failures=" + failures +
                '}';
    }
}