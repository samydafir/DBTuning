CREATE TABLE Publ_S
(
pubID varchar(129),
type varchar(13),
title varchar(700),
booktitle varchar(132),
year varchar(4),
publisher varchar(196)
);

CREATE TABLE Publ_B
(
pubID varchar(129),
type varchar(13),
title varchar(700),
booktitle varchar(132),
year varchar(4),
publisher varchar(196)
);
CREATE INDEX pubIDB ON Publ_B (pubID);

CREATE TABLE Publ_CB
(
pubID varchar(129),
type varchar(13),
title varchar(700),
booktitle varchar(132),
year varchar(4),
publisher varchar(196)
);
CREATE INDEX pubIDCB ON Publ_CB (pubID);
CLUSTER Publ_CB USING pubIDCB;

CREATE TABLE Publ_H
(
pubID varchar(129),
type varchar(13),
title varchar(700),
booktitle varchar(132),
year varchar(4),
publisher varchar(196)
);
CREATE INDEX pubIDH ON Publ_H USING HASH (pubID);
