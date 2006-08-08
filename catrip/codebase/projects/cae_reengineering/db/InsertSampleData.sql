spool cae_tables.log
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
