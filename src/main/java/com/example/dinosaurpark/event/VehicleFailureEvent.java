package com.example.dinosaurpark.event;

import com.example.dinosaurpark.model.Vehicle;
import com.example.dinosaurpark.simulation.ParkState;

import java.util.Random;

public class VehicleFailureEvent implements SimulationEvent {

    private final double probability;

    public VehicleFailureEvent(double probability) {
        this.probability = probability;
    }

    @Override
    public String getName() {
        return "VEHICLE_FAILURE";
    }

    @Override
    public String getDescription() {
        return "Vehicle broken";
    }

    @Override
    public double getProbability() {
        return probability;
    }

    @Override
    public void execute(ParkState state, Random rng) {

        Vehicle vehicle = state.getVehicles().get(
                rng.nextInt(state.getVehicles().size())
        );

        vehicle.fail();

        state.getDatabaseService().saveEvent(
                getName(),
                getDescription()
        );
    }
}