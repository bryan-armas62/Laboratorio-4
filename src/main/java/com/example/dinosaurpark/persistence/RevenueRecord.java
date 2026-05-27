package com.example.dinosaurpark.persistence;

public record RevenueRecord(
        long id,
        String type,
        double amount,
        int touristId,
        String zoneName
) {
}