package com.example.dinosaurpark.model;

public abstract class Worker {
    private final int id;
    private final String name;
    private final double dailySalary;

    public abstract String getRole();  // "GUARD" o "TECHNICIAN"
}