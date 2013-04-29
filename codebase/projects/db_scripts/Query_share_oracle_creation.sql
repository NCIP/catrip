/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/

create user query_share identified by query_share
default tablespace users
temporary tablespace temp
quota unlimited on users;
grant connect to query_share;

GRANT CREATE TABLE TO query_share;
GRANT CREATE INDEXTYPE TO query_share;
GRANT CREATE PROCEDURE TO query_share;
GRANT CREATE SYNONYM TO query_share;
GRANT CREATE TABLE TO query_share;
GRANT CREATE TRIGGER TO query_share;
GRANT CREATE TYPE TO query_share;
GRANT CREATE VIEW TO query_share;
GRANT CREATE SEQUENCE TO query_share;
GRANT CREATE SESSION TO query_share;

connect query_share/query_share;

CREATE TABLE CATRIP_QUERY
(
  ID            NUMBER(8)                       NOT NULL,
  FIRST_NAME    VARCHAR2(50 BYTE),
  LAST_NAME     VARCHAR2(100 BYTE),
  USER_NAME     VARCHAR2(25 BYTE),
  DESCRIPTION   VARCHAR2(255 BYTE),
  NAME          VARCHAR2(50 BYTE),
  DATE_CREATED  DATE,
  DATE_UPDATED  DATE,
  SOURCE        VARCHAR2(50 BYTE),
  VERSION       VARCHAR2(50 BYTE),
  INSTANCE      VARCHAR2(50 BYTE),
  DCQL          VARCHAR2(100 BYTE)
)
LOGGING 
NOCACHE
NOPARALLEL;


CREATE TABLE DCQL_ATTRIBUTE
(
  ID        NUMBER(8)                           NOT NULL,
  CLASS_ID  INTEGER,
  NAME      VARCHAR2(2000 BYTE)
)
LOGGING 
NOCACHE
NOPARALLEL;


CREATE TABLE DCQL_CLASS
(
  ID               NUMBER(8)                    NOT NULL,
  CATRIP_QUERY_ID  INTEGER,
  NAME             VARCHAR2(2000 BYTE)
)
LOGGING 
NOCACHE
NOPARALLEL;


CREATE TABLE DCQL_QUERY
(
  ID               NUMBER(8),
  CATRIP_QUERY_ID  NUMBER(8),
  SEQUENCE         NUMBER(2),
  DCQL             VARCHAR2(4000 BYTE)
)
LOGGING 
NOCACHE
NOPARALLEL;


CREATE UNIQUE INDEX PK_DCQL_QUERY ON DCQL_QUERY
(ID)
LOGGING
NOPARALLEL;


ALTER TABLE CATRIP_QUERY ADD (
  PRIMARY KEY (ID));


ALTER TABLE DCQL_QUERY ADD (
  CONSTRAINT PK_DCQL_QUERY PRIMARY KEY (ID));


ALTER TABLE DCQL_QUERY ADD (
  FOREIGN KEY (CATRIP_QUERY_ID) 
    REFERENCES CATRIP_QUERY (ID));



