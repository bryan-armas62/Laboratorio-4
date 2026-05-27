package com.example.dinosaurpark.event;

import com.example.dinosaurpark.model.Tourist;
import com.example.dinosaurpark.simulation.ParkState;

import java.util.Random;

public class StormEvent implements SimulationEvent {

    private final double probability;

    public StormEvent(double probability) {
        this.probability = probability;
    }

    @Override
    public String getName() {
        return "TORMENTA";
    }

    @Override
    public String getDescription() {
        return "Heavy storm";
    }

    @Override
    public double getProbability() {
        return probability;
    }

    @Override
    public void execute(ParkState state, Random rng) {

        for (Tourist tourist : state.getTourists()) {
            tourist.recordVisit("EVACUATION");
        }

        state.getDatabaseService().saveExpense(
                "STORM",
                500,
                "Storm damages"
        );
    }
}