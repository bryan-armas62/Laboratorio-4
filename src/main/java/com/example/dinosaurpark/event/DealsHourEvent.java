package com.example.dinosaurpark.event;

import com.example.dinosaurpark.simulation.ParkState;

import java.util.Random;

public class DealsHourEvent implements SimulationEvent {

    private final double probability;

    public DealsHourEvent(double probability) {
        this.probability = probability;
    }

    @Override
    public String getName() {
        return "DEALS_HOUR";
    }

    @Override
    public String getDescription() {
        return "Discounts activated";
    }

    @Override
    public double getProbability() {
        return probability;
    }

    @Override
    public void execute(ParkState state, Random rng) {

        state.setDiscountActive(true);

        state.getDatabaseService().saveEvent(
                getName(),
                getDescription()
        );
    }
}