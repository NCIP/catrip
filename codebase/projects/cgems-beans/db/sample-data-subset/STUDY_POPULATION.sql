/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/

INSERT INTO STUDY_POPULATION ( POPULATION_ID, POPULATION_NAME, POPULATION_DESC, SOURCE,
MEMBER_COUNT ) VALUES ( 
1, 'CONTROL', 'Group of individuals in PLCO serving as control', 'PLCO', NULL); 
INSERT INTO STUDY_POPULATION ( POPULATION_ID, POPULATION_NAME, POPULATION_DESC, SOURCE,
MEMBER_COUNT ) VALUES ( 
2, 'CASE_NONAGGRESSIVE', 'Group of individulas in PLCO with early case', 'PLCO', NULL); 
INSERT INTO STUDY_POPULATION ( POPULATION_ID, POPULATION_NAME, POPULATION_DESC, SOURCE,
MEMBER_COUNT ) VALUES ( 
3, 'CASE_AGGRESSIVE', 'Group of individuals in PLCO with advanced case', 'PLCO', NULL); 
INSERT INTO STUDY_POPULATION ( POPULATION_ID, POPULATION_NAME, POPULATION_DESC, SOURCE,
MEMBER_COUNT ) VALUES ( 
4, 'CEPH', 'Group of individuals form CEPH families', 'CEPH', NULL); 
INSERT INTO STUDY_POPULATION ( POPULATION_ID, POPULATION_NAME, POPULATION_DESC, SOURCE,
MEMBER_COUNT ) VALUES ( 
5, 'CASE', 'Group of individulas in PLCO with either early or advanced case', 'PLCO'
, NULL); 
commit;
 
