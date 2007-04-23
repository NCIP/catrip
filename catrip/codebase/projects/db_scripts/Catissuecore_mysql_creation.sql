CREATE DATABASE catissuecore_test;

GRANT ALL PRIVILEGES ON *.* TO ‘catissuecore_test'@'localhost’ IDENTIFIED BY 'catissuecore_test' WITH GRANT OPTION;

GRANT ALL PRIVILEGES ON *.* TO 'catissuecore_test'@'%' IDENTIFIED BY 'catissuecore_test' WITH GRANT OPTION;

use catissuecore_test;

/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_USER` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `EMAIL_ADDRESS` varchar(100) default NULL,
  `PASSWORD` varchar(50) default NULL,
  `FIRST_NAME` varchar(50) default NULL,
  `LAST_NAME` varchar(50) default NULL,
  `LOGIN_NAME` varchar(50) NOT NULL default '',
  `START_DATE` date default NULL,
  `ACTIVITY_STATUS` varchar(50) default NULL,
  `DEPARTMENT_ID` bigint(20) default NULL,
  `CANCER_RESEARCH_GROUP_ID` bigint(20) default NULL,
  `INSTITUTION_ID` bigint(20) default NULL,
  `ADDRESS_ID` bigint(20) default NULL,
  `CSM_USER_ID` bigint(20) default NULL,
  `STATUS_COMMENT` text,
  PRIMARY KEY  (`IDENTIFIER`),
  UNIQUE KEY `LOGIN_NAME` (`LOGIN_NAME`),
  KEY `FKB025CFC71792AD22` (`INSTITUTION_ID`),
  KEY `FKB025CFC7FFA96920` (`CANCER_RESEARCH_GROUP_ID`),
  KEY `FKB025CFC76CD94566` (`ADDRESS_ID`),
  KEY `FKB025CFC7F30C2528` (`DEPARTMENT_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=538 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_TRANSFER_EVENT_PARAM` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  `FROM_POSITION_DIMENSION_ONE` int(11) default NULL,
  `FROM_POSITION_DIMENSION_TWO` int(11) default NULL,
  `TO_POSITION_DIMENSION_ONE` int(11) default NULL,
  `TO_POSITION_DIMENSION_TWO` int(11) default NULL,
  `TO_STORAGE_CONTAINER_ID` bigint(20) default NULL,
  `FROM_STORAGE_CONTAINER_ID` bigint(20) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK71F9AC103C2DAC61` (`TO_STORAGE_CONTAINER_ID`),
  KEY `FK71F9AC1099DF0A92` (`FROM_STORAGE_CONTAINER_ID`),
  KEY `FK71F9AC10BC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_TIS_SPE_EVENT_PARAM` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  `NEOPLASTIC_CELLULARITY_PER` double default NULL,
  `NECROSIS_PERCENTAGE` double default NULL,
  `LYMPHOCYTIC_PERCENTAGE` double default NULL,
  `TOTAL_CELLULARITY_PERCENTAGE` double default NULL,
  `HISTOLOGICAL_QUALITY` varchar(50) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FKBB9648F4BC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_THAW_EVENT_PARAMETERS` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FKD8890A48BC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_TABLE_RELATION` (
  `RELATIONSHIP_ID` bigint(20) NOT NULL auto_increment,
  `PARENT_TABLE_ID` bigint(20) default NULL,
  `CHILD_TABLE_ID` bigint(20) default NULL,
  `TABLES_IN_PATH` varchar(50) default NULL,
  PRIMARY KEY  (`RELATIONSHIP_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=108 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_STORAGE_TYPE` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `TYPE` varchar(50) NOT NULL default '',
  `DEFAULT_TEMP_IN_CENTIGRADE` double default NULL,
  `ONE_DIMENSION_LABEL` varchar(50) default NULL,
  `TWO_DIMENSION_LABEL` varchar(50) default NULL,
  `STORAGE_CONTAINER_CAPACITY_ID` bigint(20) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  UNIQUE KEY `TYPE` (`TYPE`),
  KEY `FKE9A0629A5F7CB0FE` (`STORAGE_CONTAINER_CAPACITY_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1101 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_STORAGE_CONTAINER` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `CONTAINER_NUMBER` int(11) default NULL,
  `TEMPERATURE` double default NULL,
  `IS_CONTAINER_FULL` tinyint(1) default NULL,
  `BARCODE` varchar(50) default NULL,
  `ACTIVITY_STATUS` varchar(30) default NULL,
  `STORAGE_TYPE_ID` bigint(20) default NULL,
  `SITE_ID` bigint(20) default NULL,
  `PARENT_CONTAINER_ID` bigint(20) default NULL,
  `STORAGE_CONTAINER_CAPACITY_ID` bigint(20) default NULL,
  `POSITION_DIMENSION_ONE` int(11) default NULL,
  `POSITION_DIMENSION_TWO` int(11) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  UNIQUE KEY `BARCODE` (`BARCODE`),
  KEY `FK28429D015F7CB0FE` (`STORAGE_CONTAINER_CAPACITY_ID`),
  KEY `FK28429D01A7F77D13` (`SITE_ID`),
  KEY `FK28429D0159A3CE5C` (`STORAGE_TYPE_ID`),
  KEY `FK28429D01DB097B2E` (`PARENT_CONTAINER_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1329 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_STORAGE_CONT_DETAILS` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `PARAMETER_NAME` varchar(50) default NULL,
  `VALUE` varchar(50) default NULL,
  `STORAGE_CONTAINER_ID` bigint(20) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK3531F575B3DFB11D` (`STORAGE_CONTAINER_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=273 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_STORAGE_CONT_CAPACITY` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `ONE_DIMENSION_CAPACITY` int(11) default NULL,
  `TWO_DIMENSION_CAPACITY` int(11) default NULL,
  PRIMARY KEY  (`IDENTIFIER`)
) ENGINE=MyISAM AUTO_INCREMENT=1379 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_SPUN_EVENT_PARAMETERS` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  `GFORCE` double default NULL,
  `DURATION_IN_MINUTES` int(11) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK312D77BCBC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_SPECIMEN_REQUIREMENT` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `SPECIMEN_CLASS` varchar(255) NOT NULL default '',
  `SPECIMEN_TYPE` varchar(50) default NULL,
  `TISSUE_SITE` varchar(150) default NULL,
  `PATHOLOGY_STATUS` varchar(50) default NULL,
  `QUANTITY` double default NULL,
  PRIMARY KEY  (`IDENTIFIER`)
) ENGINE=MyISAM AUTO_INCREMENT=1118 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_SPECIMEN_PROTOCOL` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `PRINCIPAL_INVESTIGATOR_ID` bigint(20) default NULL,
  `TITLE` varchar(50) NOT NULL default '',
  `SHORT_TITLE` varchar(50) default NULL,
  `IRB_IDENTIFIER` varchar(50) default NULL,
  `START_DATE` date default NULL,
  `END_DATE` date default NULL,
  `ENROLLMENT` int(11) default NULL,
  `DESCRIPTION_URL` varchar(200) default NULL,
  `ACTIVITY_STATUS` varchar(50) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  UNIQUE KEY `TITLE` (`TITLE`),
  KEY `FKB8481373870EB740` (`PRINCIPAL_INVESTIGATOR_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_SPECIMEN_EVENT_PARAM` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `SPECIMEN_ID` bigint(20) default NULL,
  `EVENT_TIMESTAMP` datetime default NULL,
  `USER_ID` bigint(20) default NULL,
  `COMMENTS` text,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK753F33AD60773DB2` (`SPECIMEN_ID`),
  KEY `FK753F33AD2206F20F` (`USER_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_SPECIMEN_COLL_GROUP` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `CLINICAL_DIAGNOSIS` varchar(150) default NULL,
  `CLINICAL_STATUS` varchar(50) default NULL,
  `ACTIVITY_STATUS` varchar(50) default NULL,
  `SITE_ID` bigint(20) default NULL,
  `COLLECTION_PROTOCOL_EVENT_ID` bigint(20) default NULL,
  `CLINICAL_REPORT_ID` bigint(20) default NULL,
  `COLLECTION_PROTOCOL_REG_ID` bigint(20) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FKDEBAF167A7F77D13` (`SITE_ID`),
  KEY `FKDEBAF1674CE21DDA` (`CLINICAL_REPORT_ID`),
  KEY `FKDEBAF16753B01F66` (`COLLECTION_PROTOCOL_EVENT_ID`),
  KEY `FKDEBAF1677E07C4AC` (`COLLECTION_PROTOCOL_REG_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1126 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_SPECIMEN_CHAR` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `TISSUE_SITE` varchar(150) default NULL,
  `TISSUE_SIDE` varchar(50) default NULL,
  `PATHOLOGICAL_STATUS` varchar(50) default NULL,
  PRIMARY KEY  (`IDENTIFIER`)
) ENGINE=MyISAM AUTO_INCREMENT=1173 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_SPECIMEN_BIOHZ_REL` (
  `BIOHAZARD_ID` bigint(20) NOT NULL default '0',
  `SPECIMEN_ID` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`SPECIMEN_ID`,`BIOHAZARD_ID`),
  KEY `FK7A3F5539F398D480` (`BIOHAZARD_ID`),
  KEY `FK7A3F553960773DB2` (`SPECIMEN_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_SPECIMEN` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `SPECIMEN_CLASS` varchar(255) NOT NULL default '',
  `TYPE` varchar(50) default NULL,
  `AVAILABLE` tinyint(1) default NULL,
  `POSITION_DIMENSION_ONE` int(11) default NULL,
  `POSITION_DIMENSION_TWO` int(11) default NULL,
  `BARCODE` varchar(50) default NULL,
  `COMMENTS` varchar(200) default NULL,
  `ACTIVITY_STATUS` varchar(50) default NULL,
  `PARENT_SPECIMEN_ID` bigint(20) default NULL,
  `STORAGE_CONTAINER_IDENTIFIER` bigint(20) default NULL,
  `SPECIMEN_COLLECTION_GROUP_ID` bigint(20) default NULL,
  `SPECIMEN_CHARACTERISTICS_ID` bigint(20) default NULL,
  `QUANTITY` double default NULL,
  `AVAILABLE_QUANTITY` double default NULL,
  `CONCENTRATION` double default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  UNIQUE KEY `BARCODE` (`BARCODE`),
  KEY `FK1674810456906F39` (`SPECIMEN_CHARACTERISTICS_ID`),
  KEY `FK1674810433BF33C5` (`SPECIMEN_COLLECTION_GROUP_ID`),
  KEY `FK16748104B189E99D` (`PARENT_SPECIMEN_ID`),
  KEY `FK1674810432B31EAB` (`STORAGE_CONTAINER_IDENTIFIER`)
) ENGINE=MyISAM AUTO_INCREMENT=1203 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_SITE` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(50) NOT NULL default '',
  `TYPE` varchar(50) default NULL,
  `EMAIL_ADDRESS` varchar(150) default NULL,
  `USER_ID` bigint(20) default NULL,
  `ACTIVITY_STATUS` varchar(50) default NULL,
  `ADDRESS_ID` bigint(20) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  UNIQUE KEY `NAME` (`NAME`),
  KEY `FKB024C3436CD94566` (`ADDRESS_ID`),
  KEY `FKB024C3432206F20F` (`USER_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_SEARCH_DISPLAY_DATA` (
  `RELATIONSHIP_ID` bigint(20) NOT NULL default '0',
  `COL_ID` bigint(20) NOT NULL default '0',
  `DISPLAY_NAME` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_REPORTED_PROBLEM` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `AFFILIATION` varchar(200) NOT NULL default '',
  `NAME_OF_REPORTER` varchar(200) NOT NULL default '',
  `REPORTERS_EMAIL_ID` varchar(50) NOT NULL default '',
  `MESSAGE_BODY` varchar(200) NOT NULL default '',
  `SUBJECT` varchar(100) default NULL,
  `REPORTED_DATE` date default NULL,
  `ACTIVITY_STATUS` varchar(100) default NULL,
  `COMMENTS` text,
  PRIMARY KEY  (`IDENTIFIER`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_RELATED_TABLES_MAP` (
  `FIRST_TABLE_ID` bigint(20) default NULL,
  `SECOND_TABLE_ID` bigint(20) default NULL,
  `FIRST_TABLE_JOIN_COLUMN` varchar(50) default NULL,
  `SECOND_TABLE_JOIN_COLUMN` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_RECEIVED_EVENT_PARAM` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  `RECEIVED_QUALITY` varchar(255) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FKA7139D06BC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_QUERY_TABLE_DATA` (
  `TABLE_ID` bigint(20) NOT NULL auto_increment,
  `TABLE_NAME` varchar(50) default NULL,
  `DISPLAY_NAME` varchar(50) default NULL,
  `ALIAS_NAME` varchar(50) default NULL,
  `PRIVILEGE_ID` int(1) default NULL,
  `FOR_SQI` tinyint(1) default NULL,
  PRIMARY KEY  (`TABLE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_QUERY_RESULTS_51` (
  `Column0` bigint(20) NOT NULL default '0',
  `Column1` varchar(50) default NULL,
  `Column2` varchar(50) default NULL,
  `Column3` varchar(50) default NULL,
  `Column4` date default NULL,
  `Column5` varchar(20) default NULL,
  `Column6` varchar(50) default NULL,
  `Column7` varchar(50) default NULL,
  `Column8` varchar(50) default NULL,
  `Column9` varchar(50) default NULL,
  `Column10` varchar(50) default NULL,
  `Column11` bigint(20) NOT NULL default '0',
  `Column12` varchar(50) default NULL,
  `Column13` varchar(50) default NULL,
  `Column14` varchar(50) NOT NULL default '',
  `Column15` varchar(50) default NULL,
  `Column16` varchar(50) default NULL,
  `Column17` date default NULL,
  `Column18` date default NULL,
  `Column19` int(11) default NULL,
  `Column20` varchar(200) default NULL,
  `Column21` varchar(50) default NULL,
  `Column22` double default NULL,
  `Column23` varchar(50) default NULL,
  `Column24` varchar(150) default NULL,
  `Column25` varchar(50) default NULL,
  `Column26` double default NULL,
  `Column27` bigint(20) NOT NULL default '0',
  `Column28` bigint(20) NOT NULL default '0',
  `Column29` varchar(150) default NULL,
  `Column30` varchar(50) default NULL,
  `Column31` varchar(50) NOT NULL default '',
  `Column32` double default NULL,
  `Column33` varchar(50) NOT NULL default '',
  `Column34` varchar(50) default NULL,
  `Column35` varchar(50) default NULL,
  `Column36` bigint(20) default NULL,
  `Column37` varchar(50) default NULL,
  `Column38` varchar(50) default NULL,
  `Column39` bigint(20) NOT NULL default '0',
  `Column40` bigint(20) default NULL,
  `Column41` bigint(20) default NULL,
  `Column42` varchar(50) default NULL,
  `Column43` int(11) default NULL,
  `Column44` int(11) default NULL,
  `Column45` varchar(50) default NULL,
  `Column46` double default NULL,
  `Column47` double default NULL,
  `Column48` double default NULL,
  `Column49` varchar(150) default NULL,
  `Column50` varchar(50) default NULL,
  `Column51` varchar(50) default NULL,
  `Column52` varchar(50) default NULL,
  `Column53` varchar(50) default NULL,
  `Column54` bigint(20) NOT NULL default '0',
  `Column55` varchar(50) NOT NULL default '',
  `Column56` varchar(50) default NULL,
  `Column57` date default NULL,
  `Column58` varchar(50) default NULL,
  `Column59` varchar(50) default NULL,
  `Column60` varchar(50) default NULL,
  `Column61` date default NULL,
  `Column62` bigint(20) NOT NULL default '0',
  `Column63` bigint(20) NOT NULL default '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_QUERY_RESULTS_16` (
  `Column0` bigint(20) NOT NULL default '0',
  `Column1` varchar(50) default NULL,
  `Column2` varchar(50) default NULL,
  `Column3` varchar(50) default NULL,
  `Column4` date default NULL,
  `Column5` varchar(20) default NULL,
  `Column6` varchar(50) default NULL,
  `Column7` varchar(50) default NULL,
  `Column8` varchar(50) default NULL,
  `Column9` varchar(50) default NULL,
  `Column10` varchar(50) default NULL,
  `Column11` bigint(20) NOT NULL default '0',
  `Column12` varchar(50) NOT NULL default '',
  `Column13` varchar(50) default NULL,
  `Column14` date default NULL,
  `Column15` varchar(50) default NULL,
  `Column16` varchar(50) default NULL,
  `Column17` varchar(50) default NULL,
  `Column18` date default NULL,
  `Column19` bigint(20) NOT NULL default '0',
  `Column20` varchar(150) default NULL,
  `Column21` varchar(50) default NULL,
  `Column22` varchar(50) NOT NULL default '',
  `Column23` double default NULL,
  `Column24` varchar(50) NOT NULL default '',
  `Column25` varchar(50) default NULL,
  `Column26` varchar(50) default NULL,
  `Column27` bigint(20) default NULL,
  `Column28` varchar(50) default NULL,
  `Column29` varchar(50) default NULL,
  `Column30` bigint(20) NOT NULL default '0',
  `Column31` bigint(20) default NULL,
  `Column32` bigint(20) default NULL,
  `Column33` varchar(50) default NULL,
  `Column34` int(11) default NULL,
  `Column35` int(11) default NULL,
  `Column36` varchar(50) default NULL,
  `Column37` double default NULL,
  `Column38` double default NULL,
  `Column39` double default NULL,
  `Column40` varchar(150) default NULL,
  `Column41` varchar(50) default NULL,
  `Column42` varchar(50) default NULL,
  `Column43` varchar(50) default NULL,
  `Column44` varchar(50) default NULL,
  `Column45` bigint(20) NOT NULL default '0',
  `Column46` varchar(50) default NULL,
  `Column47` varchar(50) default NULL,
  `Column48` varchar(50) NOT NULL default '',
  `Column49` varchar(50) default NULL,
  `Column50` varchar(50) default NULL,
  `Column51` date default NULL,
  `Column52` date default NULL,
  `Column53` int(11) default NULL,
  `Column54` varchar(200) default NULL,
  `Column55` varchar(50) default NULL,
  `Column56` double default NULL,
  `Column57` varchar(50) default NULL,
  `Column58` varchar(150) default NULL,
  `Column59` varchar(50) default NULL,
  `Column60` double default NULL,
  `Column61` bigint(20) NOT NULL default '0',
  `Column62` bigint(20) NOT NULL default '0',
  `Column63` bigint(20) NOT NULL default '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_PROCEDURE_EVENT_PARAM` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  `URL` varchar(200) NOT NULL default '',
  `NAME` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FKEC6B4260BC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_PERMISSIBLE_VALUE` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `CONCEPT_CODE` varchar(40) default NULL,
  `DEFINITION` text,
  `PARENT_IDENTIFIER` bigint(20) default NULL,
  `VALUE` varchar(100) default NULL,
  `PUBLIC_ID` varchar(30) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK57DDCE153B5435E` (`PARENT_IDENTIFIER`),
  KEY `FK57DDCE1FC56C2B1` (`PUBLIC_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2637 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_PARTICIPANT` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `LAST_NAME` varchar(50) default NULL,
  `FIRST_NAME` varchar(50) default NULL,
  `MIDDLE_NAME` varchar(50) default NULL,
  `BIRTH_DATE` date default NULL,
  `GENDER` varchar(20) default NULL,
  `GENOTYPE` varchar(50) default NULL,
  `RACE` varchar(50) default NULL,
  `ETHNICITY` varchar(50) default NULL,
  `SOCIAL_SECURITY_NUMBER` varchar(50) default NULL,
  `ACTIVITY_STATUS` varchar(20) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  UNIQUE KEY `SOCIAL_SECURITY_NUMBER` (`SOCIAL_SECURITY_NUMBER`)
) ENGINE=MyISAM AUTO_INCREMENT=1078 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_PART_MEDICAL_ID` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `MEDICAL_RECORD_NUMBER` varchar(50) default NULL,
  `SITE_ID` bigint(20) default NULL,
  `PARTICIPANT_ID` bigint(20) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  UNIQUE KEY `mrn_site_unique` (`MEDICAL_RECORD_NUMBER`,`SITE_ID`),
  KEY `FK349E77F9A7F77D13` (`SITE_ID`),
  KEY `FK349E77F987E5ADC7` (`PARTICIPANT_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1075 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_MOL_SPE_REVIEW_PARAM` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  `GEL_IMAGE_URL` varchar(200) default NULL,
  `QUALITY_INDEX` varchar(50) default NULL,
  `LANE_NUMBER` varchar(50) default NULL,
  `GEL_NUMBER` int(11) default NULL,
  `ABSORBANCE_AT_260` double default NULL,
  `ABSORBANCE_AT_280` double default NULL,
  `RATIO_28S_TO_18S` double default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK5280ECEBC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_INTERFACE_COLUMN_DATA` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `TABLE_ID` bigint(20) NOT NULL default '0',
  `COLUMN_NAME` varchar(50) default NULL,
  `ATTRIBUTE_TYPE` varchar(30) default NULL,
  PRIMARY KEY  (`IDENTIFIER`)
) ENGINE=MyISAM AUTO_INCREMENT=303 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_INSTITUTION` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`IDENTIFIER`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=MyISAM AUTO_INCREMENT=89 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_IN_OUT_EVENT_PARAM` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  `STORAGE_STATUS` varchar(100) NOT NULL default '',
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK4F0FAEB9BC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_FROZEN_EVENT_PARAM` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  `METHOD` varchar(50) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK52627245BC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_FLUID_SPE_EVENT_PARAM` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  `CELL_COUNT` double default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK70565D20BC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_FIXED_EVENT_PARAM` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  `FIXATION_TYPE` varchar(50) NOT NULL default '',
  `DURATION_IN_MINUTES` int(11) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FKE0F1781BC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_EXTERNAL_IDENTIFIER` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(50) default NULL,
  `VALUE` varchar(50) default NULL,
  `SPECIMEN_ID` bigint(20) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK5CF2FA2160773DB2` (`SPECIMEN_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=141 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_EVENT_PARAM` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK90C79AECBC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_EMBEDDED_EVENT_PARAM` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  `EMBEDDING_MEDIUM` varchar(50) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FKD356182FBC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_DISTRIBUTION_SPE_REQ` (
  `DISTRIBUTION_PROTOCOL_ID` bigint(20) NOT NULL default '0',
  `SPECIMEN_REQUIREMENT_ID` bigint(20) NOT NULL default '0',
  `IDENTIFIER` bigint(20) default NULL,
  PRIMARY KEY  (`DISTRIBUTION_PROTOCOL_ID`,`SPECIMEN_REQUIREMENT_ID`),
  KEY `FKE34A3688BE10F0CE` (`SPECIMEN_REQUIREMENT_ID`),
  KEY `FKE34A36886B1F36E7` (`DISTRIBUTION_PROTOCOL_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_DISTRIBUTION_PROTOCOL` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FKC8999977BC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_DISTRIBUTION` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `TO_SITE_ID` bigint(20) default NULL,
  `DISTRIBUTION_PROTOCOL_ID` bigint(20) default NULL,
  `ACTIVITY_STATUS` varchar(50) default NULL,
  `SPECIMEN_ID` bigint(20) default NULL,
  `EVENT_TIMESTAMP` datetime default NULL,
  `USER_ID` bigint(20) default NULL,
  `COMMENTS` text,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK542766802206F20F` (`USER_ID`),
  KEY `FK542766806B1F36E7` (`DISTRIBUTION_PROTOCOL_ID`),
  KEY `FK542766801DBE834F` (`TO_SITE_ID`),
  KEY `FK5427668060773DB2` (`SPECIMEN_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_DISTRIBUTED_ITEM` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `QUANTITY` double default NULL,
  `SPECIMEN_ID` bigint(20) default NULL,
  `DISTRIBUTION_ID` bigint(20) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FKA7C3ED4B60773DB2` (`SPECIMEN_ID`),
  KEY `FKA7C3ED4BF8278B6` (`DISTRIBUTION_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_DISPOSAL_EVENT_PARAM` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  `REASON` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK1BC818D6BC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_DEPARTMENT` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`IDENTIFIER`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=MyISAM AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_COLLECTION_PROTOCOL` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK32DC439DBC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_COLL_SPECIMEN_REQ` (
  `COLLECTION_PROTOCOL_EVENT_ID` bigint(20) NOT NULL default '0',
  `SPECIMEN_REQUIREMENT_ID` bigint(20) NOT NULL default '0',
  `IDENTIFIER` bigint(20) default NULL,
  PRIMARY KEY  (`COLLECTION_PROTOCOL_EVENT_ID`,`SPECIMEN_REQUIREMENT_ID`),
  KEY `FK860E6ABEBE10F0CE` (`SPECIMEN_REQUIREMENT_ID`),
  KEY `FK860E6ABE53B01F66` (`COLLECTION_PROTOCOL_EVENT_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_COLL_PROT_REG` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `PROTOCOL_PARTICIPANT_ID` varchar(50) default NULL,
  `REGISTRATION_DATE` date default NULL,
  `PARTICIPANT_ID` bigint(20) default NULL,
  `COLLECTION_PROTOCOL_ID` bigint(20) default NULL,
  `ACTIVITY_STATUS` varchar(20) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  UNIQUE KEY `PART_ID_TO_COLL_PROT_ID` (`PARTICIPANT_ID`,`COLLECTION_PROTOCOL_ID`),
  UNIQUE KEY `PROT_PART_ID_TO_COLL_PROT_ID` (`PROTOCOL_PARTICIPANT_ID`,`COLLECTION_PROTOCOL_ID`),
  KEY `FK5EB25F1387E5ADC7` (`PARTICIPANT_ID`),
  KEY `FK5EB25F1348304401` (`COLLECTION_PROTOCOL_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1130 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_COLL_PROT_EVENT` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `CLINICAL_STATUS` varchar(50) default NULL,
  `STUDY_CALENDAR_EVENT_POINT` double default NULL,
  `COLLECTION_PROTOCOL_ID` bigint(20) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK7AE7715948304401` (`COLLECTION_PROTOCOL_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1094 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_COLL_EVENT_PARAM` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  `COLLECTION_PROCEDURE` varchar(50) default NULL,
  `CONTAINER` varchar(50) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FKF9888F91BC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_COLL_DISTRIBUTION_REL` (
  `COLLECTION_PROTOCOL_ID` bigint(20) NOT NULL default '0',
  `DISTRIBUTION_PROTOCOL_ID` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`COLLECTION_PROTOCOL_ID`,`DISTRIBUTION_PROTOCOL_ID`),
  KEY `FK1483BCB56B1F36E7` (`DISTRIBUTION_PROTOCOL_ID`),
  KEY `FK1483BCB548304401` (`COLLECTION_PROTOCOL_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_COLL_COORDINATORS` (
  `COLLECTION_PROTOCOL_ID` bigint(20) NOT NULL default '0',
  `USER_ID` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`COLLECTION_PROTOCOL_ID`,`USER_ID`),
  KEY `FKE490E33A48304401` (`COLLECTION_PROTOCOL_ID`),
  KEY `FKE490E33A2206F20F` (`USER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_CLINICAL_REPORT` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `SURGICAL_PATHOLOGICAL_NUMBER` varchar(50) default NULL,
  `PARTICIPENT_MEDI_IDENTIFIER_ID` bigint(20) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK54A4264515246F7` (`PARTICIPENT_MEDI_IDENTIFIER_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1067 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_CELL_SPE_REVIEW_PARAM` (
  `IDENTIFIER` bigint(20) NOT NULL default '0',
  `NEOPLASTIC_CELLULARITY_PER` double default NULL,
  `VIABLE_CELL_PERCENTAGE` double default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK52F40EDEBC7298A9` (`IDENTIFIER`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_CDE` (
  `PUBLIC_ID` varchar(30) NOT NULL default '',
  `LONG_NAME` varchar(200) default NULL,
  `DEFINITION` text,
  `VERSION` varchar(50) default NULL,
  `LAST_UPDATED` date default NULL,
  PRIMARY KEY  (`PUBLIC_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_CANCER_RESEARCH_GROUP` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`IDENTIFIER`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=MyISAM AUTO_INCREMENT=147 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_BIOHAZARD` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(50) NOT NULL default '',
  `COMMENTS` text,
  `TYPE` varchar(50) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_AUDIT_EVENT_LOG` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `OBJECT_IDENTIFIER` bigint(20) default NULL,
  `OBJECT_NAME` varchar(50) default NULL,
  `EVENT_TYPE` varchar(50) default NULL,
  `AUDIT_EVENT_ID` bigint(20) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK8BB672DF77F0B904` (`AUDIT_EVENT_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2020 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_AUDIT_EVENT_DETAILS` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `ELEMENT_NAME` varchar(150) default NULL,
  `PREVIOUS_VALUE` varchar(150) default NULL,
  `CURRENT_VALUE` varchar(150) default NULL,
  `AUDIT_EVENT_LOG_ID` bigint(20) default NULL,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FK5C07745D34FFD77F` (`AUDIT_EVENT_LOG_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_AUDIT_EVENT` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `IP_ADDRESS` varchar(20) default NULL,
  `EVENT_TIMESTAMP` datetime default NULL,
  `USER_ID` bigint(20) default NULL,
  `COMMENTS` text,
  PRIMARY KEY  (`IDENTIFIER`),
  KEY `FKACAF697A2206F20F` (`USER_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=672 DEFAULT CHARSET=latin1;


/* Formatted on 2007/04/23 09:39 (QP5 v5.50) */
CREATE TABLE `CATISSUE_ADDRESS` (
  `IDENTIFIER` bigint(20) NOT NULL auto_increment,
  `STREET` varchar(50) default NULL,
  `CITY` varchar(50) default NULL,
  `STATE` varchar(50) default NULL,
  `COUNTRY` varchar(50) default NULL,
  `ZIPCODE` varchar(30) default NULL,
  `PHONE_NUMBER` varchar(50) default NULL,
  `FAX_NUMBER` varchar(50) default NULL,
  PRIMARY KEY  (`IDENTIFIER`)
) ENGINE=MyISAM AUTO_INCREMENT=558 DEFAULT CHARSET=latin1;


