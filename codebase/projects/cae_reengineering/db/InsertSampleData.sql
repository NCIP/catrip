/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/

spool cae_data.log
set scan off;

@DeleteChilds.sql
@InsertAnnotatableEntity.sql
@InsertParticipants.sql
@InsertParticipantsIdentifiers.sql
@InsertAnnotationEventParameters.sql
@InsertAnnotationSet.sql

@InsertInvasiveBrCar.sql
@InsertNottigHG.sql
@InsertOtherHG.sql
@InsertThreeDim.sql
@InsertBrSurgPathSpec.sql
@InsertBreatSurgProced.sql
@UPDATE_CAE_MRN.sql
commit;

spool off;
exit
