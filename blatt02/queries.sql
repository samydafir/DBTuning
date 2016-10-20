\timing
SELECT count(ssnum)
FROM Employee e1
WHERE salary > (SELECT AVG(e2.salary)
FROM Employee e2, Techdept
WHERE e2.dept = e1.dept
AND e2.dept = Techdept.dept);

SELECT count(ssnum)
FROM Techdept t, Employee e1, (SELECT dept, AVG(salary) as avgsalary from Employee group by dept) as e2
WHERE e1.dept = t.dept AND e1.salary > e2.avgsalary AND e1.dept = e2.dept;


