SET enable_mergejoin TO false;
SET enable_nestloop TO false;
SET enable_hashjoin TO true;
SHOW enable_hashjoin;

EXPLAIN ANALYZE SELECT name,title
FROM Auth, Publ
WHERE Auth.pubID=Publ.pubID;
EXPLAIN ANALYZE SELECT title
FROM Auth, Publ
WHERE Auth.pubID=Publ.pubID AND Auth.name='Divesh Srivastava';
