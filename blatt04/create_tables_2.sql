CREATE TABLE Publ_S
(
pubID varchar(129),
type varchar(13),
title varchar(700),
booktitle varchar(132),
year varchar(4),
publisher varchar(196)
);
CREATE INDEX pubIDS ON Publ_S (pubID);
CLUSTER Publ_S USING pubIDS;
DROP INDEX pubIDS;

CREATE TABLE Publ_B
(
pubID varchar(129),
type varchar(13),
title varchar(700),
booktitle varchar(132),
year varchar(4),
publisher varchar(196)
);
CREATE INDEX booktitleB ON Publ_B (booktitle);

CREATE TABLE Publ_CB
(
pubID varchar(129),
type varchar(13),
title varchar(700),
booktitle varchar(132),
year varchar(4),
publisher varchar(196)
);
CREATE INDEX booktitleCB ON Publ_CB (booktitle);
CLUSTER Publ_CB USING booktitleCB;

CREATE TABLE Publ_H
(
pubID varchar(129),
type varchar(13),
title varchar(700),
booktitle varchar(132),
year varchar(4),
publisher varchar(196)
);
CREATE INDEX booktitleH ON Publ_H USING HASH (booktitle);
