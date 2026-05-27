package com.example.dinosaurpark.simulation;

import java.util.List;

import com.example.dinosaurpark.model.Dinosaur;
import com.example.dinosaurpark.model.Tourist;
import com.example.dinosaurpark.model.Vehicle;
import com.example.dinosaurpark.persistence.DatabaseService;
import com.example.dinosaurpark.zone.PowerPlant;

public class ParkState {

    private final List<Tourist> tourists;
    private final List<Dinosaur> dinosaurs;
    private final List<Vehicle> vehicles;

    private final PowerPlant powerPlant;

    private final DatabaseService databaseService;

    private boolean discountActive;

    public ParkState(List<Tourist> tourists,
                     List<Dinosaur> dinosaurs,
                     List<Vehicle> vehicles,
                     PowerPlant powerPlant,
                     DatabaseService databaseService) {

        this.tourists = tourists;
        this.dinosaurs = dinosaurs;
        this.vehicles = vehicles;
        this.powerPlant = powerPlant;
        this.databaseService = databaseService;

        this.discountActive = false;
    }

    public List<Tourist> getTourists() {
        return tourists;
    }

    public List<Dinosaur> getDinosaurs() {
        return dinosaurs;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public PowerPlant getPowerPlant() {
        return powerPlant;
    }

    public DatabaseService getDatabaseService() {
        return databaseService;
    }

    public boolean isDiscountActive() {
        return discountActive;
    }

    public void setDiscountActive(boolean discountActive) {
        this.discountActive = discountActive;
    }
}