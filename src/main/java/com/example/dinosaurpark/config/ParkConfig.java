package com.example.dinosaurpark.config;

public final class ParkConfig {

    private static ParkConfig instance;
    private final Properties props;

    // Constructor PRIVADO — nadie puede hacer "new ParkConfig()"
    private ParkConfig() {
        // Carga park.properties con getClass().getClassLoader().getResourceAsStream()
    }

    // Punto de acceso global — crea la instancia solo si no existe
    public static ParkConfig getInstance() { ... }

    // Métodos de lectura
    public int    getInt   (String key, int defaultValue)    { ... }
    public double getDouble(String key, double defaultValue) { ... }
    public String getString(String key, String defaultValue) { ... }
    public long   getSeed  () { ... }  // lee simulation.seed
    public int    getTotalSteps() { ... }

    // Solo para tests — permite resetear la instancia entre tests
    static void resetForTesting() { instance = null; }
}