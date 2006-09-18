--  ------------------------------------------- 
--  Generated by Enterprise Architect Ver. 4.01
--  Created On : Friday, 01 September, 2006 
--  DBMS       : Oracle 
--  ------------------------------------------- 

DROP TABLE RECURRENCE CASCADE CONSTRAINTS
;

CREATE TABLE RECURRENCE ( 
	ID NUMBER(8) NOT NULL,
	RECURRENCE_DATE DATE,
	TYPE VARCHAR2(50),
	FOLLOW_UP_ID NUMBER(8)
) 
;

ALTER TABLE RECURRENCE ADD CONSTRAINT PK_Recurrence 
PRIMARY KEY (ID) 
;

ALTER TABLE RECURRENCE ADD CONSTRAINT FK_RECURRENCE_FOLLOW_UP 
FOREIGN KEY (FOLLOW_UP_ID) REFERENCES FOLLOW_UP (ID)
;





