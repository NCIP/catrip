/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/

update PARTICIPANT_IDENTIFIERS set medical_record_number = 'DU'||id ||'00' where id<41;

update PARTICIPANT_IDENTIFIERS set medical_record_number = 'TT'||id || '00'where id>40;

commit;
