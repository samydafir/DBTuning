EXPLAIN ANALYZE SELECT name,title
FROM Auth, Publ_I
WHERE Auth.pubID=Publ_I.pubID;
EXPLAIN ANALYZE SELECT title
FROM Auth, Publ_I
WHERE Auth.pubID=Publ_I.pubID AND Auth.name='Divesh Srivastava';

EXPLAIN ANALYZE SELECT name,title
FROM Auth_I, Publ
WHERE Auth_I.pubID=Publ.pubID;
EXPLAIN ANALYZE SELECT title
FROM Auth_I, Publ
WHERE Auth_I.pubID=Publ.pubID AND Auth_I.name='Divesh Srivastava';

EXPLAIN ANALYZE SELECT name,title
FROM Auth_I, Publ_I
WHERE Auth_I.pubID=Publ_I.pubID;
EXPLAIN ANALYZE SELECT title
FROM Auth_I, Publ_I
WHERE Auth_I.pubID=Publ_I.pubID AND Auth_I.name='Divesh Srivastava';
