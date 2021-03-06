/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/

CREATE TABLE BREAST_MET_ANATOMIC_SITES ( 
	ID NUMERIC(10,2),
	METASTASIS_ANATOMIC_SITE VARCHAR(255)
) 
;


ALTER TABLE BREAST_MET_ANATOMIC_SITES ADD CONSTRAINT FK_METASTASIS_ANATOMIC_SITES 
FOREIGN KEY (ID) REFERENCES BREAST_CANCER_TNMF_FINDINGS (ID)
;



