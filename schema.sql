DROP DATABASE IF EXISTS maker_space;
CREATE DATABASE maker_space;
USE maker_space;

CREATE TABLE User (
    user_id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name      VARCHAR(50) NOT NULL,
    last_name       VARCHAR(50) NOT NULL,
    email           VARCHAR(100) NOT NULL UNIQUE,
    password_hash   VARCHAR(255) NOT NULL,
    user_type       ENUM('Member', 'Trainer', 'Shop-Tech') NOT NULL,
    credit_balance  DECIMAL(10,2) NOT NULL DEFAULT 0,
    created_at      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Equipment (
    equipment_id          INT AUTO_INCREMENT PRIMARY KEY,
    asset_tag             VARCHAR(50) NOT NULL UNIQUE,
    make                  VARCHAR(50),
    model                 VARCHAR(50),
    category              ENUM('3D Printer', 'Laser Cutter', 'CNC', 'Other') NOT NULL,
    consumable_type       VARCHAR(50),
    status                ENUM('Available', 'In-Use', 'Maintenance', 'Offline') NOT NULL DEFAULT 'Available',
    hourly_rate           DECIMAL(10,2) NOT NULL DEFAULT 0,
    usage_hours           DECIMAL(10,2) NOT NULL DEFAULT 0,
    maintenance_threshold DECIMAL(10,2) NOT NULL DEFAULT 0
);

CREATE TABLE Consumable (
    consumable_id      INT AUTO_INCREMENT PRIMARY KEY,
    name               VARCHAR(100) NOT NULL,
    unit               ENUM('kg', 'g', 'm', 'sheet', 'L', 'pcs') NOT NULL,
    stock_quantity     DECIMAL(10,2) NOT NULL DEFAULT 0,
    reorder_level      DECIMAL(10,2) NOT NULL DEFAULT 0,
    consumption_rate   DECIMAL(10,2),
    last_restock_date  DATE
);

CREATE TABLE Equipment_Session (
    session_id        INT AUTO_INCREMENT PRIMARY KEY,
    user_id           INT NOT NULL,
    equipment_id      INT NOT NULL,
    consumable_id     INT NULL,
    check_in_time     DATETIME NOT NULL,
    check_out_time    DATETIME NULL,
    material_quantity DECIMAL(10,2),
    total_debit       DECIMAL(10,2),
    CONSTRAINT fk_session_user
        FOREIGN KEY (user_id) REFERENCES User(user_id),
    CONSTRAINT fk_session_equipment
        FOREIGN KEY (equipment_id) REFERENCES Equipment(equipment_id),
    CONSTRAINT fk_session_consumable
        FOREIGN KEY (consumable_id) REFERENCES Consumable(consumable_id)
);

CREATE TABLE Ledger_Entry (
    ledger_id     INT AUTO_INCREMENT PRIMARY KEY,
    user_id       INT NOT NULL,
    entry_type    ENUM('Credit', 'Debit') NOT NULL,
    amount        DECIMAL(10,2) NOT NULL,
    source_type   ENUM(
        'Training',
        'Maintenance',
        'Work Order',
        'Equipment Use',
        'Donation',
        'Adjustment'
    ) NOT NULL,
    source_id     INT NULL,
    description   VARCHAR(255),
    entry_date    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ledger_user
        FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Training_Session (
    training_id    INT AUTO_INCREMENT PRIMARY KEY,
    trainer_id     INT NOT NULL,
    equipment_id   INT NOT NULL,
    training_date  DATETIME NOT NULL,
    credits_earned DECIMAL(10,2) NOT NULL DEFAULT 0,
    CONSTRAINT fk_training_trainer
        FOREIGN KEY (trainer_id) REFERENCES User(user_id),
    CONSTRAINT fk_training_equipment
        FOREIGN KEY (equipment_id) REFERENCES Equipment(equipment_id)
);

CREATE TABLE Work_Order (
    work_order_id  INT AUTO_INCREMENT PRIMARY KEY,
    member_id      INT NOT NULL,
    shoptech_id    INT NULL,
    description    VARCHAR(255),
    priority       ENUM('Low', 'Medium', 'High') NOT NULL DEFAULT 'Medium',
    status         ENUM('Submitted', 'In-Progress', 'Completed') NOT NULL DEFAULT 'Submitted',
    credits_earned DECIMAL(10,2),
    created_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    completed_at   DATETIME NULL,
    CONSTRAINT fk_workorder_member
        FOREIGN KEY (member_id) REFERENCES User(user_id),
    CONSTRAINT fk_workorder_shoptech
        FOREIGN KEY (shoptech_id) REFERENCES User(user_id)
);

CREATE TABLE Maintenance_Task (
    maintenance_id INT AUTO_INCREMENT PRIMARY KEY,
    equipment_id   INT NOT NULL,
    shoptech_id    INT NOT NULL,
    description    VARCHAR(255),
    scheduled_date DATE,
    completed_date DATE NULL,
    hours_worked   DECIMAL(10,2),
    status         ENUM('Scheduled', 'Completed') NOT NULL DEFAULT 'Scheduled',
    notes          TEXT,
    CONSTRAINT fk_maintenance_equipment
        FOREIGN KEY (equipment_id) REFERENCES Equipment(equipment_id),
    CONSTRAINT fk_maintenance_shoptech
        FOREIGN KEY (shoptech_id) REFERENCES User(user_id)
);

CREATE TABLE Maintenance_Alert (
    alert_id       INT AUTO_INCREMENT PRIMARY KEY,
    equipment_id   INT NOT NULL,
    alert_type     ENUM('Usage Threshold', 'Diagnostic') NOT NULL,
    alert_message  VARCHAR(255),
    created_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    resolved       BOOLEAN NOT NULL DEFAULT FALSE,
    resolved_date  DATETIME NULL,
    CONSTRAINT fk_alert_equipment
        FOREIGN KEY (equipment_id) REFERENCES Equipment(equipment_id)
);

INSERT INTO Equipment (
    asset_tag,
    make,
    model,
    category,
    status,
    hourly_rate,
    usage_hours,
    maintenance_threshold
)
VALUES (
    'TEST-001',
    'TestMake',
    'TestModel',
    '3D Printer',
    'Available',
    24.02,
    100.00,
    100.00
);