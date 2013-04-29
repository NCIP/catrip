/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/

update catissue_part_medical_id set site_id = 4 where identifier<41;
update catissue_part_medical_id set site_id = 5 where identifier>40;

update catissue_clinical_report set PARTICIPENT_MEDI_IDENTIFIER_ID=IDENTIFIER;

update catissue_part_medical_id set medical_record_number = concat("DU",identifier,"00") where identifier<41;

update catissue_part_medical_id set medical_record_number = concat("TT",identifier,"00") where identifier>40;

commit;


