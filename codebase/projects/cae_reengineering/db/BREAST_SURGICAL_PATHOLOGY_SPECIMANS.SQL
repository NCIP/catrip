/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/

CREATE TABLE BREAST_SUR_PATHO_SPECIMANS ( 
	ID NUMERIC(10,2) NOT NULL,
	SURGICAL_PROCEDURE_MVR VARCHAR(255),
	OTHER_SURGICAL_PROCEDURE VARCHAR(255),
	LYMPH_NODE_PROCEDURE VARCHAR(255),
	LATERALITY VARCHAR(255),
	LATERALITY_MVR VARCHAR(255),
	PRIMARY KEY(ID)
) 
;
ALTER TABLE BREAST_SUR_PATHO_SPECIMANS ADD CONSTRAINT FK_BREAST_SUR_PATHO_SPECIMANS 
FOREIGN KEY (ID) REFERENCES ANNOTATION_SET (ID)
;







