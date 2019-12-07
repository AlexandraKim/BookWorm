create database BookWorm;
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '12345';

CREATE TABLE Book (
Id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
Title VARCHAR(128) NOT NULL,
Subject VARCHAR(128) NOT NULL,
Author VARCHAR(128) NOT NULL,
Isbn VARCHAR(128) NOT NULL,
PublicationDate DATE
)

create table User (
Id varchar(7) not null PRIMARY KEY,
Type int(6) not null,
FirstName varchar(128) not null,
LastName varchar(128) not null,
Password varchar(32) not null
)

insert into user (Id, Type, FirstName, LastName, Password) values ('U150001', 0, 'Arnold', 'Schwarzenegger', '1234567');
