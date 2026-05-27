package com.example.dinosaurpark.event;

import com.example.dinosaurpark.simulation.ParkState;

import java.util.Random;

public interface SimulationEvent {

    String getName();

    String getDescription();

    double getProbability();

    void execute(ParkState state, Random rng);
}