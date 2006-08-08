update catissue_part_medical_id set site_id = 4 where identifier<41;
update catissue_part_medical_id set site_id = 5 where identifier>40;

update catissue_part_medical_id set medical_record_number = concat("DU",identifier,"00") where identifier<41;

update catissue_part_medical_id set medical_record_number = concat("TT",identifier,"00") where identifier>40;

commit;


