// D. Limpiar eventos del step anterior
state.clearActiveEvents();

// Luego disparar eventos probabilísticos (reemplaza el scheduler)
private void checkAndFireEvents(ParkState state) {
    for (SimulationEvent event : allEvents) {
        if (state.getRng().nextDouble() < event.getProbability()) {
            event.execute(state, state.getRng());
            state.addActiveEvent(event.getName());
        }
    }
}

// C. También hacer tick a los vehículos
for (Vehicle v : state.getVehicles()) v.tick();

// E. Technician ahora también necesita vehicles
tech.repairIfNeeded(powerPlant, vehicles);

// F. Monitoreo CONDICIONAL — no cada step
if (state.getCurrentStep() % monitoringInterval == 0)
    ParkMonitor.displaySnapshot(state);