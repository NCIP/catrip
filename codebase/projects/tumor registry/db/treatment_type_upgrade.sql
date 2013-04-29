/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/

ALTER TABLE ACTIVITY
ADD (TREATMENT_TYPE VARCHAR2(50 BYTE));

UPDATE ACTIVITY
SET treatment_type = discriminator;

COMMIT;