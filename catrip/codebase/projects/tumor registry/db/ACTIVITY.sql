--  ------------------------------------------- 
--  Generated by Enterprise Architect Ver. 4.01
--  Created On : Wednesday, 04 October, 2006 
--  DBMS       : Oracle 
--  ------------------------------------------- 

DROP TABLE ACTIVITY CASCADE CONSTRAINTS;

CREATE TABLE ACTIVITY ( 
	ID NUMBER(8) NOT NULL,
	COURSE NUMBER(8),
	STOP_DATE DATE,
	AT_LOCAL_FACILITY NUMBER(8),
	NUMBER_OF_TREATMENTS VARCHAR2(50),
	LOCATION VARCHAR2(100),
	START_DATE DATE,
	CHARACTERIZATION VARCHAR2(255),
	VOLUME VARCHAR2(100),
	REGIONAL_DOSE VARCHAR2(100),
	BOOST_DOSE VARCHAR2(100),
	BOOST_MODALITY VARCHAR2(100),
	REGIONAL_MODALITY VARCHAR2(100),
	DISCRIMINATOR VARCHAR(100),
	DIAGNOSIS_ID NUMBER(8),
	PERFORMED_INDICATOR VARCHAR(100),
  SUMMARY_ACTIVITY_ID   NUMBER(8),
  LOCAL_ACTIVITY_ID     NUMBER(8)
) 
;

ALTER TABLE ACTIVITY ADD CONSTRAINT PK_ACTIVITY 
PRIMARY KEY (ID) 
;

ALTER TABLE ACTIVITY ADD CONSTRAINT FK_ACTIVITY_DIAGNOSIS 
FOREIGN KEY (DIAGNOSIS_ID) REFERENCES DIAGNOSIS (ID)
;

















