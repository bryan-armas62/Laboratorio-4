package com.example.dinosaurpark.monitoring;

import com.example.dinosaurpark.simulation.ParkState;

import java.util.List;

public class ParkMonitor {
    
    private ParkMonitor() {}

    public static void displaySnapshot(ParkState state) {
        long activeTourists = state.countActiveTourists();
        long dinosaursInEnclosure = state.countDinosaursInEnclosure();
        double energy = state.getPowerPlant().getEnergyLevel();
        List<String> activeEvents = state.getActiveEventNames();
        long vehiclesUnavailable = state.countVehiclesInUse();

        System.out.println("-----------------------------------------------------");
        System.out.printf("|  MONITOR - Step %-31d  |%n", state.getCurrentStep());
        System.out.println("-----------------------------------------------------");
        System.out.printf("|  1. Turistas activos:        %-19d|%n", activeTourists);
        System.out.printf("|  2. Dinosuarios en encierro: %-19d|%n", dinosaursInEnclosure);
        System.out.printf("|  3. Energia disponible:      %-18s  ||%n",
            String.format("%.1f%%", energy));
        System.out.printf("|  4. Eventos activos:         %-19s|%n",
            activeEvents.isEmpty() ? "Ninguno" : String.join(", ", activeEvents));
        System.out.printf("|  5. Vehiculos no disponibles:%-19d|%n", vehiclesUnavailable);
        System.out.println("-----------------------------------------------------");
    }
}