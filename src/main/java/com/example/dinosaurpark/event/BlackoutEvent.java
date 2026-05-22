package com.example.dinosaurpark.event;

import com.example.dinosaurpark.persistence.EventRecord;
import com.example.dinosaurpark.simulation.ParkState;

import java.time.LocalDateTime;
import java.util.Random;


public class BlackoutEvent implements SimulationEvent {

    private final double probability;

    public BlackoutEvent(double probability) {
        this.probability = probability;
    }

    @Override
    public String getName() { return "APAGON_MASIVO"; }

    @Override
    public String getDescription() { return "APAGON masivo - la planta electrica fallo"; }

    @Override
    public double  getProbability() { return probability; }

    @Override
    public void execute(ParkState state, Random rng) {
        System.out.println(" [EVENTO] !APAGON MASIVO! la planta electrica colapso.");
        state.getPowerPlant().triggerFailure(state.getDb());
        state.addExpense(2000.0, "BLACKOUT", "Danos por apagon masivo");
        state.getDB().appendEvent(toRecord(state.getCurrentStep()));
    }

    @Override
    public EventRecord toRecord(long step) {
        return new EventRecord(step, getName(), getDescription(), "PowerPlant", LocalDateTime.now());
    }
}