package com.example.dinosaurpark.persistence;

public class CsvWriter {
    // Constructor: crea la carpeta output/ y los 3 archivos con headers
    // IMPORTANTE: initFile() debe SOBREESCRIBIR (no append) para evitar duplicados
    public CsvWriter(String outputDir) { ... }

    // Métodos para agregar filas
    public void appendRevenue(RevenueRecord r) { ... }
    public void appendExpense(ExpenseRecord e) { ... }
    public void appendEvent  (EventRecord   ev){ ... }
}