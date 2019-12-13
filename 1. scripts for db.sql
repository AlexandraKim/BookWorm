create database BookWorm;
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '12345';

CREATE TABLE Book (
Id INT(6) AUTO_INCREMENT PRIMARY KEY,
Title VARCHAR(128) NOT NULL,
Genre VARCHAR(128) NOT NULL,
Author VARCHAR(128) NOT NULL,
Isbn VARCHAR(13) NOT NULL,
PublicationDate DATE
);

create table User (
Id varchar(8) not null PRIMARY KEY,
Type int(6) not null,
FirstName varchar(128) not null,
LastName varchar(128) not null,
Password varchar(32) not null,
IsBlocked bit
);

CREATE TABLE UserToBook (
Id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
UserId VARCHAR(8) NOT NULL,
BookId int(6) not null,
IssueDate Date,
ReturnDate Date
);

alter table UserToBook 
add constraint FK_UserToBook_User
FOREIGN KEY (UserId) REFERENCES User(Id);

alter table UserToBook 
add constraint FK_UserToBook_Book
FOREIGN KEY (BookId) REFERENCES Book(Id);

insert into user (Id, Type, FirstName, LastName, Password) values ('U150001', 0, 'Arnold', 'Schwarzenegger', '1234567');
insert into user (Id, Type, FirstName, LastName, Password) values ('U150002', 1, 'Ryan', 'Reynolds', '12345678');
insert into user (Id, Type, FirstName, LastName, Password) values ('U1510001', 2, 'Elon', 'Musk', '12345679');

insert into book (Title, Genre, Author, Isbn, PublicationDate) values ('Harry Potter and the Philosopher''s Stone', 'Fantasy Fiction', 'J. K. Rowling', '0747532699', '1997-06-26');
insert into book (Title, Genre, Author, Isbn, PublicationDate) values ('The Great Gatsby', 'Fantasy Romantic', 'F. Scott Fitzgerald', '2023578952', '1925-10-25');
insert into book (Title, Genre, Author, Isbn, PublicationDate) values ('Fahrenheit 451', 'Scientific Novel', ' Ray Bradbury', '3698452036', '1953-10-19');
insert into book (Title, Genre, Author, Isbn, PublicationDate) values ('Pride and Prejudice', 'Historical Romantic', 'Jane Austen', '9826510325', '1813-01-28');
insert into book (Title, Genre, Author, Isbn, PublicationDate) values ('Twilight', ' Fantasy Fiction', 'Stephenie Meyer', '5489632015', '2005-10-05');
insert into book (Title, Genre, Author, Isbn, PublicationDate) values ('The Fault in Our Stars', 'Science  Fiction', 'John Green', '1239620852', '2012-01-10');
insert into book (Title, Genre, Author, Isbn, PublicationDate) values ('The Da Vinci Code', 'Detective', 'Dan Brown', '5632033881', '2003-03-18');
insert into book (Title, Genre, Author, Isbn, PublicationDate) values ('Alice''s Adventures in Wonderland & Through the Looking-Glass', 'Fantasy', 'Lewis Carrol', '9875220015', '2000-12-01');
insert into book (Title, Genre, Author, Isbn, PublicationDate) values ('Jane Eyre', 'Novel Romantic', 'Chalotte Bronte', '8755236697', '1847-10-16');
insert into book (Title, Genre, Author, Isbn, PublicationDate) values ('The Little Prince', 'Fabel Novel', 'Antoine de Saint-Exupery', '5223367890', '1943-04-06');
insert into book (Title, Genre, Author, Isbn, PublicationDate) values ('Life Pi', 'Adventure Fantasy', 'Yann Martel', '1029654789', '2001-10-11');

insert into UserToBook (UserId, BookId) values ('U1510001', 1);
insert into UserToBook (UserId, BookId) values ('U1510001', 2);