package com.example.dinosaurpark.zone;

import com.example.dinosaurpark.model.SatisfactionSurvey;
import com.example.dinosaurpark.model.Tourist;
import com.example.dinosaurpark.persistence.DatabaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObservationEnclosure implements ParkZone {

    private final String name;
    private final ExperienceType type;

    private final int maxVisitors;
    private final double entryFee;

    private final List<Tourist> tourists;

    public ObservationEnclosure(String name,
                                ExperienceType type,
                                int maxVisitors,
                                double entryFee) {

        this.name = name;
        this.type = type;
        this.maxVisitors = maxVisitors;
        this.entryFee = entryFee;

        this.tourists = new ArrayList<>();
    }

    public void visit(Tourist tourist,
                      Random rng,
                      DatabaseService db,
                      double discount) {

        if (!hasCapacity()) {
            return;
        }

        enter(tourist);

        double finalPrice = entryFee * (1 - discount);

        tourist.spend(finalPrice);

        tourist.recordVisit(name);

        db.saveRevenue(
                "ENCLOSURE",
                finalPrice,
                tourist.getId(),
                name
        );

        conductSurvey(tourist, rng);
    }

    public SatisfactionSurvey conductSurvey(Tourist tourist,
                                            Random rng) {

        int score;

        switch (type) {

            case BASIC -> score = rng.nextInt(3) + 1;

            case PREMIUM -> score = rng.nextInt(3) + 2;

            default -> score = rng.nextInt(3) + 3;
        }

        return new SatisfactionSurvey(
                tourist.getId(),
                name,
                score
        );
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasCapacity() {
        return tourists.size() < maxVisitors;
    }

    @Override
    public int getCurrentOccupancy() {
        return tourists.size();
    }

    @Override
    public int getMaxCapacity() {
        return maxVisitors;
    }

    @Override
    public void enter(Tourist tourist) {
        tourists.add(tourist);
    }

    @Override
    public void exit(Tourist tourist) {
        tourists.remove(tourist);
    }
}