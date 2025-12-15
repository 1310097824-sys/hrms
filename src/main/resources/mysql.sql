CREATE DATABASE IF NOT EXISTS hrms CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE hrms;

-- 用户表
CREATE TABLE IF NOT EXISTS user_account (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(64) UNIQUE NOT NULL,
    password VARCHAR(128),
    full_name VARCHAR(128),
    role VARCHAR(32),
    position_id BIGINT,
    active BIT,
    CONSTRAINT fk_user_position FOREIGN KEY (position_id) REFERENCES position(id)
) ENGINE=InnoDB;

-- 机构表
CREATE TABLE IF NOT EXISTS organization_unit (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(128),
    level INT,
    parent_id BIGINT,
    CONSTRAINT fk_org_parent FOREIGN KEY (parent_id) REFERENCES organization_unit(id)
) ENGINE=InnoDB;

-- 职位表
CREATE TABLE IF NOT EXISTS position (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(128),
    org_unit_id BIGINT,
    CONSTRAINT fk_position_org FOREIGN KEY (org_unit_id) REFERENCES organization_unit(id)
) ENGINE=InnoDB;

-- 档案表（假删除）
CREATE TABLE IF NOT EXISTS employee_file (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    hire_date DATE,
    notes VARCHAR(255),
    deleted BIT DEFAULT 0,
    CONSTRAINT fk_file_user FOREIGN KEY (user_id) REFERENCES user_account(id)
) ENGINE=InnoDB;

-- 考勤表
CREATE TABLE IF NOT EXISTS attendance_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    check_in_time DATETIME,
    CONSTRAINT fk_att_user FOREIGN KEY (user_id) REFERENCES user_account(id)
) ENGINE=InnoDB;

-- 薪酬表
CREATE TABLE IF NOT EXISTS payroll_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    month VARCHAR(7),
    amount DOUBLE,
    notes VARCHAR(255),
    CONSTRAINT fk_pay_user FOREIGN KEY (user_id) REFERENCES user_account(id)
) ENGINE=InnoDB;
