package com.example.dinosaurpark.persistence;

public record ExpenseRecord(
        long id,
        String type,
        double amount,
        String description
) {
}