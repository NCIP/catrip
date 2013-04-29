/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/

delete from activity_summary;
delete from activity;

delete from collaborative_staging;
delete from distant_site;
delete from disease_extent;

delete from first_course_trmt_summary;
delete from follow_up;
delete from patient_identifier;
delete from recurrence;
delete from diagnosis;
delete from patient;
delete from address;

commit;

