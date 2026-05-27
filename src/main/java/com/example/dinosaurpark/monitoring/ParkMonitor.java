// ParkMonitor.java
package com.example.dinosaurpark.monitoring;

import com.example.dinosaurpark.model.DinosaurStatus;
import com.example.dinosaurpark.model.TouristStatus;
import com.example.dinosaurpark.model.VehicleStatus;
import com.example.dinosaurpark.simulation.ParkState;

public class ParkMonitor {

    public void printStatus(ParkState state, int step) {

        long escapedDinosaurs = state.getDinosaurs()
                .stream()
                .filter(d -> d.getStatus() == DinosaurStatus.ESCAPED)
                .count();

        long attackedTourists = state.getTourists()
                .stream()
                .filter(t -> t.getStatus() == TouristStatus.ATTACKED)
                .count();

        long brokenVehicles = state.getVehicles()
                .stream()
                .filter(v -> v.getStatus() == VehicleStatus.BROKEN)
                .count();

        System.out.println("====================================");
        System.out.println("SIMULATION STEP: " + step);
        System.out.println("====================================");

        System.out.println("Tourists: " + state.getTourists().size());
        System.out.println("Dinosaurs: " + state.getDinosaurs().size());
        System.out.println("Vehicles: " + state.getVehicles().size());

        System.out.println("Escaped Dinosaurs: " + escapedDinosaurs);
        System.out.println("Attacked Tourists: " + attackedTourists);
        System.out.println("Broken Vehicles: " + brokenVehicles);

        System.out.println("Power Plant Operational: "
                + state.getPowerPlant().isOperational());

        System.out.println("Discount Active: "
                + state.isDiscountActive());

        System.out.println("====================================");
    }
}