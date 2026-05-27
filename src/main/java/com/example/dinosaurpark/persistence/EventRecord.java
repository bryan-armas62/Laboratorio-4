package com.example.dinosaurpark.persistence;

public record EventRecord(
        long id,
        String name,
        String description
) {
}