/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/

CREATE DATABASE caties;

GRANT ALL PRIVILEGES ON *.* TO ‘caties'@'localhost’ IDENTIFIED BY 'caties' WITH GRANT OPTION;

GRANT ALL PRIVILEGES ON *.* TO 'caties'@'%' IDENTIFIED BY 'caties' WITH GRANT OPTION;

use caties;

/* Formatted on 2007/04/23 09:50 (QP5 v5.50) */
CREATE TABLE `IDENTIFIED_PATHOLOGY_REPORT` (
  `ID` bigint(20) NOT NULL auto_increment,
  `VERSION` bigint(20) default '0',
  `UUID` varchar(45) default NULL,
  `DEIDENTIFIED_ID` varchar(45) default NULL,
  `DOCUMENT_TEXT` mediumtext,
  `ACCESSION_NUMBER` varchar(255) default NULL,
  `COLLECTION_DATE_TIME` datetime default NULL,
  `ORDERING_PHYSICIAN_ID` varchar(255) default NULL,
  `HONEST_BROKER_COMMENT` mediumtext,
  `PATIENT_ID` bigint(20) default NULL,
  `ORGANIZATION_ID` bigint(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FKEB4CAB186D01BA9E` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:50 (QP5 v5.50) */
CREATE TABLE `IDENTIFIED_PATIENT` (
  `ID` bigint(20) NOT NULL auto_increment,
  `VERSION` bigint(20) default '1',
  `UUID` varchar(45) default NULL,
  `DEIDENTIFIED_ID` varchar(45) default NULL,
  `MEDICAL_RECORD_NUMBER` varchar(255) default NULL,
  `FIRST_NAME` varchar(255) default NULL,
  `LAST_NAME` varchar(255) default NULL,
  `MIDDLE_NAME` varchar(255) default NULL,
  `BIRTH_DATE` datetime default NULL,
  `SOCIAL_SECURITY_NUMBER` varchar(255) default NULL,
  `GENDER` varchar(255) default NULL,
  `RACE` varchar(255) default NULL,
  `ETHNICITY` varchar(255) default NULL,
  `MARITAL_STATUS` varchar(255) default NULL,
  `ORGANIZATION_ID` bigint(20) default '0',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


