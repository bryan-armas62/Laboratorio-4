package com.example.dinosaurpark.model;

public void tick() {
    if (status == VehicleStatus.BROKEN) {
        repairCountdown--;
        if (repairCountdown <= 0) {
            status = VehicleStatus.AVAILABLE;
        }
    }
}