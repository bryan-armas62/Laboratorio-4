package com.example.dinosaurpark.zone;

public interface ParkZone {
    String getName();
    boolean hasCapacity();
    int getCurrentOccupancy();
    int getMaxCapacity();
    void enter(Tourist tourist);
    void exit(Tourist tourist);
}