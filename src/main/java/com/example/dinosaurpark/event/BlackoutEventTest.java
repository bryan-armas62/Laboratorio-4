package com.example.dinosaurpark.event;

import com.example.dinosaurpark.model.*;
import com.example.dinosaurpark.persistence.DatabaseService;
import com.example.dinosaurpark.simulation.ParkState;
import com.example.dinosaurpark.zone.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Assertions.*;

import com.example.dinosaurpark.zone.ArrivalZone;
import com.example.dinosaurpark.zone.PowerPlant;


class BlackoutEventTest {

    private DatabaseService db;

    @BeforeEach
    void setUp() {
        db = new DatabaseService("./data/test-blackout-" + System.currentTimeMillis());
    }

    @AfterEach
    void tearDown() {
        db.close();
    }

    private ParkState buildState() {
        return new ParkState(
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
            new ArrivalZone(30, 25.0), new CentralHub(15.0, 0.4),
            new BathroomZone(10, 3, 20, 0.2), new PowerPlant(100, 1.5, 0.0, 200, 500),
            new ArrayList<>(), db, new Random(0)
        );
    }

    @Test
    void testGetName() {
        assertEquals("APAGON MASIVO", new BlackoutEvent(0.03).getName());
    }
}