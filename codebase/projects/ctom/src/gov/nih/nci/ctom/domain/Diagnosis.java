/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

import java.sql.Date;
import java.util.Set;

/**
 * @version 1.0
 * @created 19-Jun-2006 11:34:41 AM
 */
public class Diagnosis extends Assessment {
	private long id;
	private int ageAtDiagnosis;
	private Date confirmationDate;
	private String diseaseDiagnosisCode;
	private String diseaseDiagnosisCodeSystem;
	/**
	 * [Additional Documentation] Definition of the extent of the disease in the body
	 * (e.g., in-situ, localized, invasion, regional, etc.) [End Documentation]
	 */
	private String diseaseExtentText;
	/**
	 * Values include: metastatic, disease free.
	 */
	private String diseaseStatusCode;
	private String name;
	/**
	 * Values include: Appendix, Bladder, Cervix, etc.
	 */
	private String primaryAnatomicSiteCode;
	private String primaryAnatomicSiteCodeSystem;
	/**
	 * Values include: Bilateral, Both, Left, Right, etc. 
	 */
	private String primaryAnatomicSiteLateralityCode;
	private boolean recurrenceIndicator;
	/**
	 * Values Include: Pathology Report, Self-Report, etc.
	 */
	private String sourceCode;
	private String sourceOther;
	private Set cancerStage;

	public Diagnosis(){

	}

	public int getAgeAtDiagnosis() {
		return ageAtDiagnosis;
	}

	public void setAgeAtDiagnosis(int ageAtDiagnosis) {
		this.ageAtDiagnosis = ageAtDiagnosis;
	}

	public Set getCancerStage() {
		return cancerStage;
	}

	public void setCancerStage(Set cancerStage) {
		this.cancerStage = cancerStage;
	}

	public Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public String getDiseaseDiagnosisCode() {
		return diseaseDiagnosisCode;
	}

	public void setDiseaseDiagnosisCode(String diseaseDiagnosisCode) {
		this.diseaseDiagnosisCode = diseaseDiagnosisCode;
	}

	public String getDiseaseDiagnosisCodeSystem() {
		return diseaseDiagnosisCodeSystem;
	}

	public void setDiseaseDiagnosisCodeSystem(String diseaseDiagnosisCodeSystem) {
		this.diseaseDiagnosisCodeSystem = diseaseDiagnosisCodeSystem;
	}

	public String getDiseaseExtentText() {
		return diseaseExtentText;
	}

	public void setDiseaseExtentText(String diseaseExtentText) {
		this.diseaseExtentText = diseaseExtentText;
	}

	public String getDiseaseStatusCode() {
		return diseaseStatusCode;
	}

	public void setDiseaseStatusCode(String diseaseStatusCode) {
		this.diseaseStatusCode = diseaseStatusCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrimaryAnatomicSiteCode() {
		return primaryAnatomicSiteCode;
	}

	public void setPrimaryAnatomicSiteCode(String primaryAnatomicSiteCode) {
		this.primaryAnatomicSiteCode = primaryAnatomicSiteCode;
	}

	public String getPrimaryAnatomicSiteCodeSystem() {
		return primaryAnatomicSiteCodeSystem;
	}

	public void setPrimaryAnatomicSiteCodeSystem(
			String primaryAnatomicSiteCodeSystem) {
		this.primaryAnatomicSiteCodeSystem = primaryAnatomicSiteCodeSystem;
	}

	public String getPrimaryAnatomicSiteLateralityCode() {
		return primaryAnatomicSiteLateralityCode;
	}

	public void setPrimaryAnatomicSiteLateralityCode(
			String primaryAnatomicSiteLateralityCode) {
		this.primaryAnatomicSiteLateralityCode = primaryAnatomicSiteLateralityCode;
	}

	public boolean isRecurrenceIndicator() {
		return recurrenceIndicator;
	}

	public void setRecurrenceIndicator(boolean recurrenceIndicator) {
		this.recurrenceIndicator = recurrenceIndicator;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceOther() {
		return sourceOther;
	}

	public void setSourceOther(String sourceOther) {
		this.sourceOther = sourceOther;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}