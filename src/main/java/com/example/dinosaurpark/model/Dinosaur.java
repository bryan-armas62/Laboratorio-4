package com.example.dinosaurpark.model;

public abstract class Dinosaur {
    // Campos comunes a todos los dinosaurios
    private final int id;
    private final String name, species;
    private DinosaurStatus status;  // inicia en IN_ENCLOSURE
    private final double feedingCostPerDay;

    // Métodos abstractos — cada subclase define su propio comportamiento
    public abstract String getDiet();        // "CARNIVORE" o "HERBIVORE"
    public abstract double getDangerLevel(); // 0.0 a 1.0

    // Métodos concretos — iguales para todos
    public void escape()           { status = ESCAPED;     }
    public void recapture()        { status = RECAPTURED;  }
    public void returnToEnclosure(){ status = IN_ENCLOSURE;}
}