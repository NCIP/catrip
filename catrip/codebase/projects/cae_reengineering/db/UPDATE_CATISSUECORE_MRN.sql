update catissue_part_medical_id set site_id = 4 where identifier<41;
update catissue_part_medical_id set site_id = 5 where identifier>40;

update catissue_part_medical_id set medical_record_number = concat("DU",identifier,"00") where identifier<41;

update catissue_part_medical_id set medical_record_number = concat("TT",identifier,"00") where identifier>40;

commit;


update PARTICIPANT_IDENTIFIERS set medical_record_number = 'DU'||id ||'00' where id<41;

update PARTICIPANT_IDENTIFIERS set medical_record_number = 'TT'||id || '00'where id>40;
