/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/

CREATE TABLE BREAST_MC_LOCATIONS ( 
	ID NUMERIC(10,2),
	MICROCALCIFICATION_LOCATION VARCHAR(255)
) 
;


ALTER TABLE BREAST_MC_LOCATIONS ADD CONSTRAINT FK_MC_LOCATIONS 
FOREIGN KEY (ID) REFERENCES INVASIVE_BREAST_CARCINOMA (ID)
;



