CREATE TABLE Persons (
                         PersonID int UNIQUE AUTO_INCREMENT NOT NULL,
                         LastName varchar(255) NOT NULL,
                         FirstName varchar(255) NOT NULL,
                         Gender varchar(80) NOT NULL,
                         Title varchar(255) NOT NULL,
                         Nat varchar(255) NOT NULL
);

CREATE TABLE AAA (
                         Person_ID int UNIQUE AUTO_INCREMENT NOT NULL,
                         LastName varchar(255) NOT NULL
);

DROP table AAA;

INSERT INTO Persons (FirstName, LastName, Gender, Title, Nat) VALUES ('Jane', 'Doe', 'female', 'Mrs', 'US');


DELETE from Persons ;