package com.example.dinosaurpark;

import com.example.dinosaurpark.config.ParkConfig;
import com.example.dinosaurpark.simulation.SimulationEngine;

public class Main {
    public static void main(String[] args) {
        ParkConfig config = ParkConfig.getInstance();
        SimulationEngine engine = new SimulationEngine(config);
        engine.run();
    }
}