CREATE TABLE Publ
(
pubID varchar(129),
type varchar(13),
title varchar(700),
booktitle varchar(132),
year varchar(4),
publisher varchar(196)
);

CREATE TABLE Publ_I  
(
pubID varchar(129),
type varchar(13),
title varchar(700),
booktitle varchar(132),
year varchar(4),
publisher varchar(196)
);

CREATE TABLE Auth
(
name varchar(49),
pubID varchar(129)
);

CREATE TABLE Auth_I
(
name varchar(49),
pubID varchar(129)
);

CREATE INDEX pubIDP ON Publ_I (pubID);
CREATE INDEX pubIDA ON Auth_I (pubID);
