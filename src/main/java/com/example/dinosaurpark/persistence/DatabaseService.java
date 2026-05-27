package com.example.dinosaurpark.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseService {

    private final Connection connection;

    public DatabaseService() {

        try {

            connection = DriverManager.getConnection(
                    "jdbc:h2:./dinosaurparkdb"
            );

            createTables();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createTables() throws Exception {

        connection.createStatement().execute("""
                CREATE TABLE IF NOT EXISTS revenues(
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    type VARCHAR(100),
                    amount DOUBLE,
                    tourist_id INT,
                    zone_name VARCHAR(100)
                )
                """);

        connection.createStatement().execute("""
                CREATE TABLE IF NOT EXISTS expenses(
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    type VARCHAR(100),
                    amount DOUBLE,
                    description VARCHAR(255)
                )
                """);

        connection.createStatement().execute("""
                CREATE TABLE IF NOT EXISTS events(
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100),
                    description VARCHAR(255)
                )
                """);
    }

    public void saveRevenue(String type,
                            double amount,
                            int touristId,
                            String zoneName) {

        try {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO revenues(type, amount, tourist_id, zone_name) VALUES (?, ?, ?, ?)"
            );

            ps.setString(1, type);
            ps.setDouble(2, amount);
            ps.setInt(3, touristId);
            ps.setString(4, zoneName);

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveExpense(String type,
                            double amount,
                            String description) {

        try {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO expenses(type, amount, description) VALUES (?, ?, ?)"
            );

            ps.setString(1, type);
            ps.setDouble(2, amount);
            ps.setString(3, description);

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveEvent(String name,
                          String description) {

        try {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO events(name, description) VALUES (?, ?)"
            );

            ps.setString(1, name);
            ps.setString(2, description);

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {

    try {

        if (connection != null) {
            connection.close();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}