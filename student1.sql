
DECLARE 
record_student1 student1%ROWTYPE; 
record_student2 student2%ROWTYPE; 
flag boolean := false; 
CURSOR stud1_c is 
	select * from student1; 
CURSOR stud2_c is 
	select * from student2; 
BEGIN 
open stud1_c; 
loop 
	fetch stud1_c into record_student1; 
	exit when stud1_c%notfound; 

	open stud2_c; 
	loop 
		fetch stud2_c into record_student2; 
		exit when stud2_c%notfound; 
		if record_student1.roll = record_student2.oroll then 
			flag := true; 
			exit; 
		end if; 
	end loop; 
	close stud2_c; 

	if flag = true then 
	dbms_output.put_line('data is already present'); 
	else 
	dbms_output.put_line('data is added'); 
	insert into student2 values (record_student1.name,record_student1.roll,record_student1.class,record_student1.marks); 
	end if; 

	flag := false; 
end loop; 
close stud1_c; 
END; 
/

[root@localhost bin]# /etc/init.d/oracle-xe restart 
Restarting oracle-xe (via systemctl):                      [  OK  ]
[root@localhost bin]# cd /u01/app/oracle/product/11.2.0/xe/bin
[root@localhost bin]#  . ./oracle_env.sh
[root@localhost bin]# sqlplus system

SQL*Plus: Release 11.2.0.2.0 Production on Thu Oct 10 14:27:11 2019

Copyright (c) 1982, 2011, Oracle.  All rights reserved.

Enter password: 

Connected to:
Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production

SQL> create table student1(name varchar(10),roll number,class varchar(5), marks number);

Table created.

SQL> create table student2(oname varchar(10),oroll number,oclass varchar(5),omarks number);

Table created.

SQL> insert into student1 values("Shradha",76,"TE2",90);
insert into student1 values("Shradha",76,"TE2",90)
                                         *
ERROR at line 1:
ORA-00984: column not allowed here


SQL> insert into student values('Shradha',76,'TE2',90);
insert into student values('Shradha',76,'TE2',90)
            *
ERROR at line 1:
ORA-00942: table or view does not exist


SQL> insert into student1 values('Shradha',76,'TE2',90);

1 row created.

SQL> insert into student1 values('Harshada',60,'TE2',95);

1 row created.

SQL> insert into student1 values('Pratiksha',75,'SE2',92);

1 row created.

SQL> insert into student1 values('Shruti',79,'SE3',88);

1 row created.

SQL>  insert into student1 values('Rasika',67,'TE3',91);

1 row created.

SQL> select * from student1;

NAME		 ROLL CLASS	 MARKS
---------- ---------- ----- ----------
Shradha 	   76 TE2	    90
Harshada	   60 TE2	    95
Pratiksha	   75 SE2	    92
Shruti		   79 SE3	    88
Rasika		   67 TE3	    91

SQL> insert into student2 values('Rasika',67,'TE3',91);

1 row created.

SQL> insert into student2 values('Vishal',74,'TE2',89);

1 row created.

SQL> insert into student2 values('Madhu',35,'SE3',85);

1 row created.

SQL> insert into student2 values('Prachi',30,'TE3',94);

1 row created.

SQL> insert into student2 values('Kunjal',44,'TE2',96);

1 row created.

SQL> insert into student2 values('Kasturi',50,'TE1',92);

1 row created.

SQL> select * from student2;

ONAME		OROLL OCLAS	OMARKS
---------- ---------- ----- ----------
Rasika		   67 TE3	    91
Vishal		   74 TE2	    89
Madhu		   35 SE3	    85
Prachi		   30 TE3	    94
Kunjal		   44 TE2	    96
Kasturi 	   50 TE1	    92

6 rows selected.

SQL> update student1 set roll=66 where name='Rasika';

1 row updated.

Commit complete.
SQL> select * from student1;

NAME		 ROLL CLASS	 MARKS
---------- ---------- ----- ----------
Shradha 	   76 TE2	    90
Harshada	   60 TE2	    95
Pratiksha	   75 SE2	    92
Shruti		   79 SE3	    88
Rasika		   66 TE3	    91

SQL>   
DECLARE 
record_student1 student1%ROWTYPE; 
record_student2 student2%ROWTYPE; 
flag boolean := false; 
CURSOR stud1_c is 
	select * from student1; 
CURSOR stud2_c is 
	select * from student2; 
BEGIN 
open stud1_c; 
loop 
	fetch stud1_c into record_student1; 
	exit when stud1_c%notfound; 

	open stud2_c; 
	loop 
		fetch stud2_c into record_student2; 
		exit when stud2_c%notfound; 
		if record_student1.roll = record_student2.oroll then 
			flag := true; 
			exit; 
		end SQL> if; 
	end loop; 
	close stud2_c; 

	if flag = true then 
	dbms_output.put_line('data is already present'); 
	else 
	dbms_output.put_line('data is added'); 
	insert into student2 values (record_student1.name,record_student1.roll,record_student1.class,record_student1.marks); 
	end if; 

	flag := false; 
end loop; 
close stud1_c; 
END; 
/

  2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26   27   28   29   30   31   32   33   34   35   36   37  data is already present
data is already present
data is already present
data is already present
data is added

PL/SQL procedure successfully completed.

Commit complete.
SQL> SQL> select * from student1;

NAME		 ROLL CLASS	 MARKS
---------- ---------- ----- ----------
Shradha 	   76 TE2	    90
Harshada	   60 TE2	    95
Pratiksha	   75 SE2	    92
Shruti		   79 SE3	    88
Rasika		   66 TE3	    91

SQL> select * from student2;

ONAME		OROLL OCLAS	OMARKS
---------- ---------- ----- ----------
Rasika		   67 TE3	    91
Vishal		   74 TE2	    89
Madhu		   35 SE3	    85
Prachi		   30 TE3	    94
Kunjal		   44 TE2	    96
Kasturi 	   50 TE1	    92
Shradha 	   76 TE2	    90
Harshada	   60 TE2	    95
Pratiksha	   75 SE2	    92
Shruti		   79 SE3	    88
Rasika		   66 TE3	    91

11 rows selected.

SQL> 






