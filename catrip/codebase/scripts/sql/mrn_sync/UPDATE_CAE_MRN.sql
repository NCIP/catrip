update PARTICIPANT_IDENTIFIERS set medical_record_number = 'DU'||id ||'00' where id<41;

update PARTICIPANT_IDENTIFIERS set medical_record_number = 'TT'||id || '00'where id>40;

commit;
