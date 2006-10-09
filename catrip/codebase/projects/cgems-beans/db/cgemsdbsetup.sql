
spool cgems_setup.log
set scan off;


create user cgems identified by cgems
default tablespace users
temporary tablespace temp
quota unlimited on users;
grant connect to cgems;

GRANT CREATE TABLE TO cgems;
GRANT CREATE INDEXTYPE TO cgems;
GRANT CREATE PROCEDURE TO cgems;
GRANT CREATE SYNONYM TO cgems;
GRANT CREATE TABLE TO cgems;
GRANT CREATE TRIGGER TO cgems;
GRANT CREATE TYPE TO cgems;
GRANT CREATE VIEW TO cgems;
GRANT CREATE SEQUENCE TO cgems;
GRANT CREATE SESSION TO cgems;

spool off
exit