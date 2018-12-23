-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
-- create-and-update-database-schema-bank.sql
-- Host: localhost    Database: bank

CREATE DATABASE bank;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `client`;
CREATE TABLE bank.client
(
  id         INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
  login      VARCHAR(30) UNIQUE NOT NULL,
  password   VARCHAR(30)        NOT NULL,
  first_name VARCHAR(30)        NOT NULL,
  last_name  VARCHAR(30)        NOT NULL,
  passport   VARCHAR(30)        NOT NULL,
  phone      VARCHAR(15)        NOT NULL
);

INSERT INTO bank.client (id, login, password, first_name, last_name, passport, phone)
VALUES
  ('1', 'admin', 'admin', 'first_name', 'last_name', 'passport', 'phone'),
  ('2', 'user', 'user', 'first_name', 'last_name', 'passport', 'phone');


DROP TABLE IF EXISTS `card`;
CREATE TABLE bank.card
(
  id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
  account     INT             NOT NULL UNIQUE,
  amount      INT,
  blocked     TINYINT         NOT NULL,
  id_client   INT             NOT NULL,
  unlock_card TINYINT         NOT NULL,
  FOREIGN KEY (id_client) REFERENCES client (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

INSERT INTO bank.card (id, account, amount, blocked, id_client, unlock_card)
VALUES
  ('1', '111', '1000', '0', '2', '0'),
  ('2', '222', '1000', '0', '2', '0'),
  ('3', '333', '1000', '0', '2', '0'),
  ('4', '444', '1000', '1', '2', '1'),
  ('5', '555', '1000', '1', '2', '1');


DROP TABLE IF EXISTS `history`;
CREATE TABLE bank.history
(
  id                INT AUTO_INCREMENT PRIMARY KEY UNIQUE,
  account_sender    INT,
  amount_retelling  INT,
  account_retelling INT,
  operations        VARCHAR(30) NOT NULL,
  date_operation    DATE        NOT NULL,

  FOREIGN KEY (account_sender) REFERENCES card (account)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


INSERT INTO bank.history (id, account_sender, amount_retelling, account_retelling, operations, date_operation)
VALUES
  ('1', '111', '100', '222', 'DEPOSIT', '2012-06-18'),
  ('2', '222', '100', '333', 'DEPOSIT', '2014-8-10'),
  ('3', '333', '100', '111', 'DEPOSIT', '2015-8-10');
