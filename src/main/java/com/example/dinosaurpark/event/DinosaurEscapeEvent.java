package com.example.dinosaurpark.event;

import com.example.dinosaurpark.model.Dinosaur;
import com.example.dinosaurpark.model.Tourist;
import com.example.dinosaurpark.model.TouristStatus;
import com.example.dinosaurpark.simulation.ParkState;

import java.util.List;
import java.util.Random;

public class DinosaurEscapeEvent implements SimulationEvent {

    private final double probability;

    public DinosaurEscapeEvent(double probability) {
        this.probability = probability;
    }

    @Override
    public String getName() {
        return "ESCAPE_DINOSAURIO";
    }

    @Override
    public String getDescription() {
        return "A dinosaur escaped";
    }

    @Override
    public double getProbability() {
        return probability;
    }

    @Override
    public void execute(ParkState state, Random rng) {

        List<Dinosaur> dinosaurs = state.getDinosaurs();

        Dinosaur selected = dinosaurs.get(
                rng.nextInt(dinosaurs.size())
        );

        selected.escape();

        if (rng.nextDouble() < selected.getDangerLevel()) {

            List<Tourist> tourists = state.getTourists();

            Tourist tourist = tourists.get(
                    rng.nextInt(tourists.size())
            );

            tourist.setStatus(TouristStatus.ATTACKED);
        }

        state.getDatabaseService().saveEvent(
                getName(),
                getDescription()
        );
    }
}