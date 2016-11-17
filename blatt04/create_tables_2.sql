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
CREATE INDEX yearB ON Publ_B (year);

CREATE TABLE Publ_CB
(
pubID varchar(129),
type varchar(13),
title varchar(700),
booktitle varchar(132),
year varchar(4),
publisher varchar(196)
);
CREATE INDEX yearCB ON Publ_CB (year);
CLUSTER Publ_CB USING yearCB;

CREATE TABLE Publ_H
(
pubID varchar(129),
type varchar(13),
title varchar(700),
booktitle varchar(132),
year varchar(4),
publisher varchar(196)
);
CREATE INDEX yearH ON Publ_H USING HASH (year);
