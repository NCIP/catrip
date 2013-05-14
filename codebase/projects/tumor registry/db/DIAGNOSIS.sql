/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/

--  ------------------------------------------- 
--  Generated by Enterprise Architect Ver. 4.01
--  Created On : Thursday, 07 September, 2006 
--  DBMS       : Oracle 
--  ------------------------------------------- 

DROP TABLE DIAGNOSIS CASCADE CONSTRAINTS;

CREATE TABLE DIAGNOSIS ( 
	ID NUMBER(8) NOT NULL,
	FIRST_CONTACT_DATE DATE,
	INITIAL_DIAGNOSIS_DATE DATE,
	AGE_AT_DIAGNOSIS NUMBER(8),
	CAUSE_OF_DEATH VARCHAR2(100),
	BEHAVIOR VARCHAR2(100),
	HISTOLOGY VARCHAR2(100),
	HISTOLOGY_CODE NUMBER(8),
	HISTOLOGIC_GRADE VARCHAR2(100),
	LATERALITY VARCHAR2(100),
	PRIMARY_SITE VARCHAR2(100),
	PRIMARY_SITE_CODE VARCHAR2(100),
	CLASS_OF_CASE VARCHAR2(100),
	CLASS_OF_CASE_CODE NUMBER(8,2),
	PATIENT_ID NUMBER(8),
	ADDRESS_ID NUMBER(8)
) 
;

ALTER TABLE DIAGNOSIS ADD CONSTRAINT PK_DIAGNOSIS 
PRIMARY KEY (ID) 
;

ALTER TABLE DIAGNOSIS ADD CONSTRAINT FK_DIAGNOSIS_PATIENT 
FOREIGN KEY (PATIENT_ID) REFERENCES PATIENT (ID)
;

ALTER TABLE DIAGNOSIS ADD CONSTRAINT FK_DIAGNOSIS_ADDRESS 
FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS (ID)
;





















