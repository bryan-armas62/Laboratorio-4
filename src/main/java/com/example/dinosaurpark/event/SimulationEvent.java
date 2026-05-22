package com.example.dinosaurpark.event;

public interface SimulationEvent {
    String      getName();
    String      getDescription();
    void        execute(ParkState state, Random rng);
    EventRecord toRecord(long step);
    double getProbability();
}