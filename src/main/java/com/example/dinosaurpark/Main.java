package com.example.dinosaurpark;

import java.util.ArrayList;
import java.util.List;

import com.example.dinosaurpark.config.ParkConfig;
import com.example.dinosaurpark.event.BlackoutEvent;
import com.example.dinosaurpark.event.DealsHourEvent;
import com.example.dinosaurpark.event.DinosaurEscapeEvent;
import com.example.dinosaurpark.event.SimulationEvent;
import com.example.dinosaurpark.event.StormEvent;
import com.example.dinosaurpark.event.VehicleFailureEvent;
import com.example.dinosaurpark.model.CarnivoreDinosaur;
import com.example.dinosaurpark.model.Dinosaur;
import com.example.dinosaurpark.model.Guard;
import com.example.dinosaurpark.model.HerbivoreDinosaur;
import com.example.dinosaurpark.model.Technician;
import com.example.dinosaurpark.model.Tourist;
import com.example.dinosaurpark.model.Vehicle;
import com.example.dinosaurpark.persistence.DatabaseService;
import com.example.dinosaurpark.simulation.ParkState;
import com.example.dinosaurpark.simulation.SimulationEngine;
import com.example.dinosaurpark.zone.PowerPlant;

public class Main {

    public static void main(String[] args) {

        ParkConfig config = ParkConfig.getInstance();

        DatabaseService db = new DatabaseService();

        List<Tourist> tourists = new ArrayList<>();

        for (int i = 1; i <= config.getInt("tourists", 50); i++) {
            tourists.add(new Tourist(i, "Tourist-" + i));
        }

        List<Dinosaur> dinosaurs = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {

            dinosaurs.add(
                    new CarnivoreDinosaur(
                            i,
                            "TRex-" + i,
                            "Tyrannosaurus"
                    )
            );
        }

        for (int i = 1; i <= 15; i++) {

            dinosaurs.add(
                    new HerbivoreDinosaur(
                            i + 100,
                            "Brachio-" + i,
                            "Brachiosaurus"
                    )
            );
        }

        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            vehicles.add(new Vehicle(i));
        }

        PowerPlant powerPlant = new PowerPlant(
                100,
                1.5,
                0.05,
                500
        );

        ParkState state = new ParkState(
                tourists,
                dinosaurs,
                vehicles,
                powerPlant,
                db
        );

        List<SimulationEvent> events = List.of(
                new DinosaurEscapeEvent(0.05),
                new BlackoutEvent(0.03),
                new StormEvent(0.04),
                new DealsHourEvent(0.02),
                new VehicleFailureEvent(0.03)
        );

        List<Guard> guards = List.of(
                new Guard(1, "Guard-1", 150),
                new Guard(2, "Guard-2", 150)
        );

        List<Technician> technicians = List.of(
                new Technician(1, "Tech-1", 150)
        );

        SimulationEngine engine = new SimulationEngine(
                state,
                events,
                guards,
                technicians
        );

        engine.run(
                config.getInt("simulation.totalSteps", 100)
        );

        db.close();
    }
}