package edu.duke.cabig.tumorregistry.domain;
import java.lang.Long;
import java.lang.String;

/**
 * @version 1.0
 * @created 01-Sep-2006 1:33:21 PM
 */
public class CollaborativeStaging {

	private Long id;
	private String extension;
	private String tumorSize;
	private String tumorSizeExtEvaluation;
	private String lymphNodes;
	private String lymphNodesAtEvaluation;
	private String metEvaluation;
	private String metAtDiagnosis;
	private String derivedTumorStage;
	private String derivedTumorDescriptor;
	private String derivedNodeStage;
	private String derivedNodeDescriptor;
	private String derivedMetStage;
	private String derivedMetDescriptor;
	private String derivedAJCCStage;
	private String derivedSEERSummary1977;
	private String derivedSEERSummary2000;
	private String siteSpecificFactor1;
	private String siteSpecificFactor2;
	private String siteSpecificFactor3;
	private String siteSpecificFactor4;
	private String siteSpecificFactor5;
	private String siteSpecificFactor6;
	private Long diagnosisId;
	private Diagnosis diagnosis;

	public CollaborativeStaging(){

	}

	public void finalize() throws Throwable {

	}

	public String getDerivedAJCCStage() {
		return derivedAJCCStage;
	}

	public void setDerivedAJCCStage(String derivedAJCCStage) {
		this.derivedAJCCStage = derivedAJCCStage;
	}

	public String getDerivedMetDescriptor() {
		return derivedMetDescriptor;
	}

	public void setDerivedMetDescriptor(String derivedMetDescriptor) {
		this.derivedMetDescriptor = derivedMetDescriptor;
	}

	public String getDerivedMetStage() {
		return derivedMetStage;
	}

	public void setDerivedMetStage(String derivedMetStage) {
		this.derivedMetStage = derivedMetStage;
	}

	public String getDerivedNodeDescriptor() {
		return derivedNodeDescriptor;
	}

	public void setDerivedNodeDescriptor(String derivedNodeDescriptor) {
		this.derivedNodeDescriptor = derivedNodeDescriptor;
	}

	public String getDerivedNodeStage() {
		return derivedNodeStage;
	}

	public void setDerivedNodeStage(String derivedNodeStage) {
		this.derivedNodeStage = derivedNodeStage;
	}

	public String getDerivedSEERSummary1977() {
		return derivedSEERSummary1977;
	}

	public void setDerivedSEERSummary1977(String derivedSEERSummary1977) {
		this.derivedSEERSummary1977 = derivedSEERSummary1977;
	}

	public String getDerivedSEERSummary2000() {
		return derivedSEERSummary2000;
	}

	public void setDerivedSEERSummary2000(String derivedSEERSummary2000) {
		this.derivedSEERSummary2000 = derivedSEERSummary2000;
	}

	public String getDerivedTumorDescriptor() {
		return derivedTumorDescriptor;
	}

	public void setDerivedTumorDescriptor(String derivedTumorDescriptor) {
		this.derivedTumorDescriptor = derivedTumorDescriptor;
	}

	public String getDerivedTumorStage() {
		return derivedTumorStage;
	}

	public void setDerivedTumorStage(String derivedTumorStage) {
		this.derivedTumorStage = derivedTumorStage;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLymphNodes() {
		return lymphNodes;
	}

	public void setLymphNodes(String lymphNodes) {
		this.lymphNodes = lymphNodes;
	}

	public String getLymphNodesAtEvaluation() {
		return lymphNodesAtEvaluation;
	}

	public void setLymphNodesAtEvaluation(String lymphNodesAtEvaluation) {
		this.lymphNodesAtEvaluation = lymphNodesAtEvaluation;
	}

	public String getMetAtDiagnosis() {
		return metAtDiagnosis;
	}

	public void setMetAtDiagnosis(String metAtDiagnosis) {
		this.metAtDiagnosis = metAtDiagnosis;
	}

	public String getMetEvaluation() {
		return metEvaluation;
	}

	public void setMetEvaluation(String metEvaluation) {
		this.metEvaluation = metEvaluation;
	}

	public String getSiteSpecificFactor1() {
		return siteSpecificFactor1;
	}

	public void setSiteSpecificFactor1(String siteSpecificFactor1) {
		this.siteSpecificFactor1 = siteSpecificFactor1;
	}

	public String getSiteSpecificFactor2() {
		return siteSpecificFactor2;
	}

	public void setSiteSpecificFactor2(String siteSpecificFactor2) {
		this.siteSpecificFactor2 = siteSpecificFactor2;
	}

	public String getSiteSpecificFactor3() {
		return siteSpecificFactor3;
	}

	public void setSiteSpecificFactor3(String siteSpecificFactor3) {
		this.siteSpecificFactor3 = siteSpecificFactor3;
	}

	public String getSiteSpecificFactor4() {
		return siteSpecificFactor4;
	}

	public void setSiteSpecificFactor4(String siteSpecificFactor4) {
		this.siteSpecificFactor4 = siteSpecificFactor4;
	}

	public String getSiteSpecificFactor5() {
		return siteSpecificFactor5;
	}

	public void setSiteSpecificFactor5(String siteSpecificFactor5) {
		this.siteSpecificFactor5 = siteSpecificFactor5;
	}

	public String getSiteSpecificFactor6() {
		return siteSpecificFactor6;
	}

	public void setSiteSpecificFactor6(String siteSpecificFactor6) {
		this.siteSpecificFactor6 = siteSpecificFactor6;
	}

	public String getTumorSize() {
		return tumorSize;
	}

	public void setTumorSize(String tumorSize) {
		this.tumorSize = tumorSize;
	}

	public String getTumorSizeExtEvaluation() {
		return tumorSizeExtEvaluation;
	}

	public void setTumorSizeExtEvaluation(String tumorSizeExtEvaluation) {
		this.tumorSizeExtEvaluation = tumorSizeExtEvaluation;
	}

	public Long getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(Long diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

}