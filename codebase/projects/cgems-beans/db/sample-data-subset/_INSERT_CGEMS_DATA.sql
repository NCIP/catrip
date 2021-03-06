/*L
  Copyright Duke Comprehensive Cancer Center

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/catrip/LICENSE.txt for details.
L*/

spool cgems_data.log
set scan off;

@SNP_DIM.sql
@SNP_ASSOCIATION_ANALYSIS.sql
@SNP_ANALYSIS_FINDING_FACT.sql
@SNP_ANALYSIS_GROUP.sql
@SNP_PANEL.sql
@SNP_ASSAY.sql
@STUDY_POPULATION.sql
@SNP_FREQ_FACT.sql
@STUDY_DIM.sql
@STUDY_PARTICIPANT.sql
@SNP_ANALYSIS_GRP_AS.sql
@STUDY_PANEL_ASSOC.sql
@STUDY_STDPOPULATION_ASSOC.sql

@CHR_START_END.sql
@GENE_ALIAS.sql
@GENE_DIM.sql
@GENE_SNP_ASSOC.sql
@GENOTYPE_STATUS_LU.sql

spool off;