// EXECUTE THE OUPUT OF THIS SQL ON DB

select 'insert into recurrence (id,type) values (' || id || ',' || '''NO KNOWN RECURRENCE''' || ');'  from follow_up where id  not in ( select id from recurrence);

