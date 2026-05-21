package com.example.dinosaurpark.model;

// INTERMEDIO: necesita un vehículo AVAILABLE para reparar
public void repairIfNeeded(PowerPlant plant, List<Vehicle> vehicles) {
    if (!plant.isOperational()) {
        // Busca el primer vehículo con status AVAILABLE
        Optional<Vehicle> available = vehicles.stream()
            .filter(v -> v.getStatus() == VehicleStatus.AVAILABLE)
            .findFirst();
        if (available.isPresent()) {
            available.get().use();     // marca como IN_USE
            plant.repair();
            available.get().free();    // devuelve a AVAILABLE
        }
        // Si no hay vehículo: la planta queda sin reparar este step
    }
}