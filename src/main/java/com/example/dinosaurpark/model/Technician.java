package com.example.dinosaurpark.model;

import com.example.dinosaurpark.zone.PowerPlant;

import java.util.List;
import java.util.Optional;

public class Technician extends Worker {

    public Technician(int id, String name, double dailySalary) {
        super(id, name, dailySalary);
    }

    @Override
    public String getRole() {
        return "TECHNICIAN";
    }

    public void repairIfNeeded(PowerPlant plant, List<Vehicle> vehicles) {

        if (!plant.isOperational()) {

            Optional<Vehicle> availableVehicle = vehicles.stream()
                    .filter(v -> v.getStatus() == VehicleStatus.AVAILABLE)
                    .findFirst();

            if (availableVehicle.isPresent()) {

                availableVehicle.get().use();

                plant.repair();

                availableVehicle.get().free();
            }
        }
    }
}