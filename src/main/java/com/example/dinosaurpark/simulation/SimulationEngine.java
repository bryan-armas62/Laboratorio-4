package com.example.dinosaurpark.simulation;

import java.util.List;
import java.util.Random;

import com.example.dinosaurpark.event.SimulationEvent;
import com.example.dinosaurpark.model.Guard;
import com.example.dinosaurpark.model.Technician;
import com.example.dinosaurpark.monitoring.ParkMonitor;

public class SimulationEngine {

    private final ParkState state;

    private final List<SimulationEvent> events;

    private final List<Guard> guards;

    private final List<Technician> technicians;

    private final ParkMonitor monitor;

    private final Random rng;

    public SimulationEngine(ParkState state,
                            List<SimulationEvent> events,
                            List<Guard> guards,
                            List<Technician> technicians) {

        this.state = state;
        this.events = events;
        this.guards = guards;
        this.technicians = technicians;

        this.monitor = new ParkMonitor();

        this.rng = new Random();
    }

    public void run(int totalSteps) {

        for (int step = 1; step <= totalSteps; step++) {

            executeEvents();

            processGuards();

            processTechnicians();

            state.getPowerPlant().tick(
                    rng,
                    state.getDatabaseService()
            );

            monitor.printStatus(state, step);

            state.setDiscountActive(false);

            sleep();
        }
    }

    private void executeEvents() {

        for (SimulationEvent event : events) {

            if (rng.nextDouble() < event.getProbability()) {

                event.execute(state, rng);
            }
        }
    }

    private void processGuards() {

        for (Guard guard : guards) {

            guard.recaptureEscapedDinosaurs(
                    state.getDinosaurs()
            );
        }
    }

    private void processTechnicians() {

        for (Technician technician : technicians) {

            technician.repairIfNeeded(
                    state.getPowerPlant(),
                    state.getVehicles()
            );
        }
    }

    private void sleep() {

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}