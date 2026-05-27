package com.example.dinosaurpark.model;

public class Vehicle {

    private final int id;
    private VehicleStatus status;

    public Vehicle(int id) {
        this.id = id;
        this.status = VehicleStatus.AVAILABLE;
    }

    public void use() {
        status = VehicleStatus.IN_USE;
    }

    public void free() {
        status = VehicleStatus.AVAILABLE;
    }

    public void fail() {
        status = VehicleStatus.BROKEN;
    }

    public int getId() {
        return id;
    }

    public VehicleStatus getStatus() {
        return status;
    }
}