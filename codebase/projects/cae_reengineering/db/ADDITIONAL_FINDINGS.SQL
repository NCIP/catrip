/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/


CREATE TABLE ADDITIONAL_FINDINGS ( 
	ID NUMERIC(10,2) NOT NULL,
	OTHER_FINDINGS VARCHAR(255),
	PRIMARY KEY(ID)
) 
;

ALTER TABLE ADDITIONAL_FINDINGS ADD CONSTRAINT FK_ADDITIONAL_FINDINGS 
FOREIGN KEY (ID) REFERENCES ANNOTATION_SET (ID)
;


