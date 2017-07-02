DROP DATABASE IF EXISTS gate;

CREATE DATABASE gate;

use gate;

CREATE TABLE IF NOT EXISTS gate.Account (
  id VARCHAR(256) NOT NULL,
  password VARCHAR(256) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS gate.Password_History (
  id VARCHAR(256) NOT NULL,
  account_id VARCHAR(256) NOT NULL,
  password VARCHAR(256) NOT NULL,
  registered_id VARCHAR(256) NOT NULL,
  registration_date DateTime NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES Account(id)
  )ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS gate.Person (
  id VARCHAR(256) NOT NULL,
  last_name VARCHAR(128) NOT NULL,
  middle_name VARCHAR(128),
  first_name VARCHAR(128) NOT NULL,
  date_of_birth DATE NOT NULL,
  postal_code VARCHAR(10),
  state_province VARCHAR(32),
  city VARCHAR(32),
  building VARCHAR(64),
  address1 VARCHAR(64),
  address2 VARCHAR(64),
  address3 VARCHAR(64),
  phone_number1 VARCHAR(15),
  phone_number2 VARCHAR(15),
  mail_address1 VARCHAR(256),
  mail_address2 VARCHAR(256),
  PRIMARY KEY (id)
  )ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS gate.Person_History (
  id VARCHAR(256) NOT NULL,
  person_id VARCHAR(256) NOT NULL,
  last_name VARCHAR(128) NOT NULL,
  middle_name VARCHAR(128),
  first_name VARCHAR(128) NOT NULL,
  postal_code VARCHAR(10),
  state_province VARCHAR(32),
  city VARCHAR(32),
  building VARCHAR(64),
  address1 VARCHAR(64),
  address2 VARCHAR(64),
  address3 VARCHAR(64),
  registered_id VARCHAR(256) NOT NULL,
  registration_date DateTime NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (person_id) REFERENCES Person(id)
  )ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS gate.Person_Account_Link (
  id VARCHAR(256) NOT NULL,
  person_id VARCHAR(256) NOT NULL,
  account_id VARCHAR(256) NOT NULL,
  state TINYINT(1) NOT NULL,
  registration_date DateTime NOT NULL,
  PRIMARY KEY (id)
  )ENGINE = InnoDB;

