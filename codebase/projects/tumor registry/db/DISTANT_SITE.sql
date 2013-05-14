/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/

--  ------------------------------------------- 
--  Generated by Enterprise Architect Ver. 4.01
--  Created On : Friday, 01 September, 2006 
--  DBMS       : Oracle 
--  ------------------------------------------- 

DROP TABLE DISTANT_SITE CASCADE CONSTRAINTS
;

CREATE TABLE DISTANT_SITE ( 
	ID NUMBER(8) NOT NULL,
	NAME VARCHAR2(50),
	RECURRENCE_ID NUMBER(8),
	DISEASE_EXTENT_ID NUMBER(8)
) 
;

ALTER TABLE DISTANT_SITE ADD CONSTRAINT PK_DISTANT_SITE 
PRIMARY KEY (ID) 
;

ALTER TABLE DISTANT_SITE ADD CONSTRAINT FK_DISTANT_SITE_Recurrence 
FOREIGN KEY (RECURRENCE_ID) REFERENCES RECURRENCE (ID)
;

ALTER TABLE DISTANT_SITE ADD CONSTRAINT FK_DISTANT_SITE_DISEASE_EXTENT 
FOREIGN KEY (DISEASE_EXTENT_ID) REFERENCES DISEASE_EXTENT (ID)
;



