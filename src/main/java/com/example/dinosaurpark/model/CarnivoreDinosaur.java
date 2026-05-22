package com.example.dinosaurpark.model;

public class CarnivoreDinosaur extends Dinosaur {

    public CarnivoreDinosaur(int id, String name, String species) {
        super(id, name, species);
    }

    @Override
    public double getDangerLevel() {
        return 0.9;
    }
}