/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/

// EXECUTE THE OUPUT OF THIS SQL ON DB

select 'insert into recurrence (id,type) values (' || id || ',' || '''NO KNOWN RECURRENCE''' || ');'  from follow_up where id  not in ( select id from recurrence);

