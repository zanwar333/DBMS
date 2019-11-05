SQL> create table Customer60(cid number,Name varchar2(10),total_purchase number);

Table created.

SQL> create table Category60(cid number,Name varchar2(10), class varchar2(10));

Table created.

SQL> insert into Customer60 values(1,'Shradha', 15000);

1 row created.

Commit complete.
SQL> insert into Customer60 values(2,'Harshada',9000);

1 row created.

Commit complete.
SQL> insert into Customer60 values(3,'Pratiksha',4000);

1 row created.

Commit complete.

SQL> set autocommit on;
SQL> set serveroutput on;
SQL> CREATE or replace PROCEDURE Grade 
IS 
CURSOR cur IS 
	select * from Customer60; 
Cust_data Customer60%ROWTYPE; 
BEGIN 
OPEN cur; 
delete from Category60; 
loop 
	fetch cur INTO Cust_data; 
	EXIT WHEN cur%NOTFOUND; 
		if Cust_data.total_purchase <= 20000 AND Cust_data.total_purchase >= 10000 THEN 
			dbms_output.put_line('Platinum Category Customer'); 
			insert into Category60 values(Cust_data.cid,Cust_data.Name,'platinum'); 
		elsif Cust_data.total_purchase <= 9999 AND Cust_data.total_  2  purchase >= 5000 THEN 
			dbms_output.put_line('Gold Category Customer'); 
			insert into Category60 values(Cust_data.cid,Cust_data.Name,'gold'); 
		elsif Cust_data.total_purchase <= 4999 AND Cust_data.total_purchase >= 2000 THEN 
			dbms_output.put_line('Si  3  lver Category Customer'); 
			insert into Category60 values(Cust_data.cid,Cust_data.Name,'silver'); 
		ELSE 
			dbms_output.put_line('Regular Category Customer'); 
			insert into Category60 values(Cust_data.cid,Cust_data.Name,'regular'); 
	END if; 
     End loop; 
END; 

Procedure created.

SQL> select * from Customer60;

       CID NAME       TOTAL_PURCHASE
---------- ---------- --------------
	 1 Shradha	       15000
	 2 Harshada		9000
	 3 Pratiksha		4000

SQL> select * from Category60;

no rows selected

SQL> execute Grade;
Platinum Category Customer
Gold Category Customer
Silver Category Customer

PL/SQL procedure successfully completed.

Commit complete.
SQL> select * from Category60;

       CID NAME       CLASS
---------- ---------- ----------
	 1 Shradha    platinum
	 2 Harshada   gold
	 3 Pratiksha  silver

SQL> 



