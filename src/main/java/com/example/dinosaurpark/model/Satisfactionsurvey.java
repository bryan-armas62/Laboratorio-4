package com.example.dinosaurpark.model;

public record SatisfactionSurvey(
        int touristId,
        String enclosureName,
        int score
) {
}