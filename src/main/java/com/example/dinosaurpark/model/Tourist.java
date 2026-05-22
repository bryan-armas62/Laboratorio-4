package com.example.dinosaurpark.model;

import java.util.ArrayList;
import java.util.List;

public class Tourist {

    private final int id;
    private final String name;
    private TouristStatus status;
    private double moneySpent;
    private final List<String> visitedZones;

    public Tourist(int id, String name) {
        this.id = id;
        this.name = name;
        this.status = TouristStatus.WAITING;
        this.visitedZones = new ArrayList<>();
    }

    public void spend(double amount) {
        moneySpent += amount;
    }

    public void visitZone(String zone) {
        visitedZones.add(zone);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TouristStatus getStatus() {
        return status;
    }

    public void setStatus(TouristStatus status) {
        this.status = status;
    }
}