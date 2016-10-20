CREATE TABLE Employee 
(
ssnum integer,
name character varying,
manager character varying,
dept character varying,
salary numeric(8,2),
numfriends smallint,
CONSTRAINT ssnumNameE PRIMARY KEY (ssnum, name)
);
CREATE UNIQUE INDEX ssnumE ON Employee (ssnum);
CREATE UNIQUE INDEX nameE ON Employee (name);
CREATE INDEX deptE ON Employee (dept);

CREATE TABLE Student 
(
ssnum integer,
name character varying,
course character varying,
grade smallint,
CONSTRAINT ssnumNameS PRIMARY KEY (ssnum, name)
);
CREATE UNIQUE INDEX ssnumS ON Student (ssnum);
CREATE UNIQUE INDEX nameS ON Student (name);

CREATE TABLE Techdept
(
dept character varying,
manager character varying,
location character varying,
CONSTRAINT deptT PRIMARY KEY (dept)
);


