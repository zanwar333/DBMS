SQL> select * from LibraryAudit;

      ISBN BOOKNAME		       QTY
---------- -------------------- ----------
	11 JJJ				30
	11 ABC				30

SQL> select * from Customer60;

       CID NAME       TOTAL_PURCHASE
---------- ---------- --------------
	 1 Shradha	       15000
	 2 Harshada		9000
	 3 Pratiksha		4000

SQL> select * from Category60;

no rows selected

SQL> CREATE or replace Function Fgrade(Icid In number) 
return varchar2 
is 
purchase number; 
name varchar2(20); 
BEGIN 
select total_purchase into purchase from Customer60 where cid=Icid; 
select Name into name from Customer60 where cid=Icid; 
		if purchase <= 20000 AND purchase >= 10000 THEN 
			insert into Category60 values(Icid,name,'platinum'); 
			return('Platinum Category customer'); 
		elsif purchase <= 9999 AND  2   purchase >= 5000 THEN 
			insert into Category60 values(Icid,name,'gold'); 
			return('Gold Category customer'); 
		elsif purchase <= 4999 AND purchase >= 2000 THEN 
			insert into Category60 values(Icid,name,'silver'); 
			return('Silver Category customer'); 
		ELSE 
			insert into Category60 values(Icid,name,'regular'); 
			return('Regular Category customer'); 
	END if; 
     END;  
     /

Function created.

SQL> variable vname varchar2(40);
SQL> execute:vname:=Fgrade(1);

PL/SQL procedure successfully completed.

Commit complete.
SQL> print vname;

VNAME
--------------------------------------------------------------------------------
Platinum Category customer

SQL> execute:vname:=Fgrade(2);

PL/SQL procedure successfully completed.

Commit complete.
SQL> print vname;

VNAME
--------------------------------------------------------------------------------
Gold Category customer

SQL> execute:vname:=Fgrade(3);

PL/SQL procedure successfully completed.

Commit complete.
SQL> print vname;

VNAME
--------------------------------------------------------------------------------
Silver Category customer

SQL> 

