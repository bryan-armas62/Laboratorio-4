CREATE TABLE IF NOT EXISTS revenues (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(100),
    amount DOUBLE,
    tourist_id INT,
    zone_name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS expenses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(100),
    amount DOUBLE,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    description VARCHAR(255)
);