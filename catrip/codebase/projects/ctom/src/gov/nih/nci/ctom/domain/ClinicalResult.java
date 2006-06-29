package gov.nih.nci.ctom.domain;

import gov.nih.nci.ctom.domain.Observation;

/**
 * @version 1.0
 * @created 19-Jun-2006 11:34:41 AM
 */
public class ClinicalResult extends Observation {

	/**
	 * Values include: ER-Estrogen Receptor Assay, PR-Progesterone Receptor Assay, p53
	 * Assay, etc.
	 */
	private String assayMethodCode;
	private boolean biomarkerIndicator;
	/**
	 * Values include: Unknown, Semiprone, Prone, Sitting, etc.
	 */
	private String bodyPositionCode;
	/**
	 * Values include: Lower than reference range, High- greater than normal in degree
	 * or intensity or amount, Normal, etc
	 */
	private String labReferenceRangeCode;
	/**
	 * Values include: IHC-Immunohistochemistry, PCR- Polymerase Chain Reaction,
	 * Manual Differentiation, etc.
	 */
	private String labTechniqueCode;
	/**
	 * Values include: Conference notes, Hospital Information System (electronic
	 * transfer), Hard copy, etc.
	 */
	private String meansVitalStatusObtainedCode;
	private String normalAbnormalIndicator;
	private String panelName;
	private boolean significanceIndicator;
	private String value;
	/**
	 * [Additional Documentation] In case of  laboratory test results, this element
	 * maps to CDE Lab Unit Of Measure Name; publicID 2003753; version 3.0. [End
	 * Documentation]
	 */
	private String valueUnitOfMeasureCode;
	private ConceptDescriptor conceptDescriptor;

	public ClinicalResult(){

	}

	public String getAssayMethodCode() {
		return assayMethodCode;
	}

	public void setAssayMethodCode(String assayMethodCode) {
		this.assayMethodCode = assayMethodCode;
	}

	public boolean isBiomarkerIndicator() {
		return biomarkerIndicator;
	}

	public void setBiomarkerIndicator(boolean biomarkerIndicator) {
		this.biomarkerIndicator = biomarkerIndicator;
	}

	public String getBodyPositionCode() {
		return bodyPositionCode;
	}

	public void setBodyPositionCode(String bodyPositionCode) {
		this.bodyPositionCode = bodyPositionCode;
	}

	public ConceptDescriptor getConceptDescriptor() {
		return conceptDescriptor;
	}

	public void setConceptDescriptor(ConceptDescriptor conceptDescriptor) {
		this.conceptDescriptor = conceptDescriptor;
	}

	public String getLabReferenceRangeCode() {
		return labReferenceRangeCode;
	}

	public void setLabReferenceRangeCode(String labReferenceRangeCode) {
		this.labReferenceRangeCode = labReferenceRangeCode;
	}

	public String getLabTechniqueCode() {
		return labTechniqueCode;
	}

	public void setLabTechniqueCode(String labTechniqueCode) {
		this.labTechniqueCode = labTechniqueCode;
	}

	public String getMeansVitalStatusObtainedCode() {
		return meansVitalStatusObtainedCode;
	}

	public void setMeansVitalStatusObtainedCode(String meansVitalStatusObtainedCode) {
		this.meansVitalStatusObtainedCode = meansVitalStatusObtainedCode;
	}

	public String getNormalAbnormalIndicator() {
		return normalAbnormalIndicator;
	}

	public void setNormalAbnormalIndicator(String normalAbnormalIndicator) {
		this.normalAbnormalIndicator = normalAbnormalIndicator;
	}

	public String getPanelName() {
		return panelName;
	}

	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}

	public boolean isSignificanceIndicator() {
		return significanceIndicator;
	}

	public void setSignificanceIndicator(boolean significanceIndicator) {
		this.significanceIndicator = significanceIndicator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueUnitOfMeasureCode() {
		return valueUnitOfMeasureCode;
	}

	public void setValueUnitOfMeasureCode(String valueUnitOfMeasureCode) {
		this.valueUnitOfMeasureCode = valueUnitOfMeasureCode;
	}

}