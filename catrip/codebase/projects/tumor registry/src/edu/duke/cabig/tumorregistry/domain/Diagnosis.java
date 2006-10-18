package edu.duke.cabig.tumorregistry.domain;
import java.lang.Long;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;

/**
 * Diagnosis
 * @version 1.0
 * @created 01-Sep-2006 3:23:14 PM
 */
public class Diagnosis {

	private Long id;
	private Date firstContactDate;
	private Date initialDiagnosisDate;
	private Integer ageAtDiagnosis;
	private String causeOfDeath;
	private String behavior;
	private String histology;
	private Integer histologyCode;
	private String histologicGrade;
	private String laterality;
	private String primarySite;
	private String classOfCase;
	private Integer classOfCaseCode;
	private String primarySiteCode;
	
	private java.util.Set activityCollection;
	private java.util.Set diseaseExtentCollection;
	private java.util.Set followUpCollection;
	private CollaborativeStaging collaborativeStaging;
	private Address address;
	private FirstCourseTreatmentSummary firstTreatment;
	private ActivitySummary activitySummary;
	private Patient patient;
	private DiseaseExtent diseaseExtent;
	
	

	public DiseaseExtent getDiseaseExtent() {
		return diseaseExtent;
	}

	public void setDiseaseExtent(DiseaseExtent diseaseExtent) {
		this.diseaseExtent = diseaseExtent;
	}

	public Diagnosis(){

	}

	public java.util.Set getActivityCollection() {
		return activityCollection;
	}

	public void setActivityCollection(java.util.Set activityCollection) {
		this.activityCollection = activityCollection;
	}

	public ActivitySummary getActivitySummary() {
		return activitySummary;
	}

	public void setActivitySummary(ActivitySummary activitySummary) {
		this.activitySummary = activitySummary;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getAgeAtDiagnosis() {
		return ageAtDiagnosis;
	}

	public void setAgeAtDiagnosis(Integer ageAtDiagnosis) {
		this.ageAtDiagnosis = ageAtDiagnosis;
	}

	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}

	public String getCauseOfDeath() {
		return causeOfDeath;
	}

	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}

	public String getClassOfCase() {
		return classOfCase;
	}

	public void setClassOfCase(String classOfCase) {
		this.classOfCase = classOfCase;
	}

	public Integer getClassOfCaseCode() {
		return classOfCaseCode;
	}

	public void setClassOfCaseCode(Integer classOfCaseCode) {
		this.classOfCaseCode = classOfCaseCode;
	}

	public CollaborativeStaging getCollaborativeStaging() {
		return collaborativeStaging;
	}

	public void setCollaborativeStaging(CollaborativeStaging collaborativeStaging) {
		this.collaborativeStaging = collaborativeStaging;
	}

	public java.util.Set getDiseaseExtentCollection() {
		return diseaseExtentCollection;
	}

	public void setDiseaseExtentCollection(java.util.Set diseaseExtentCollection) {
		this.diseaseExtentCollection = diseaseExtentCollection;
	}

	public Date getFirstContactDate() {
		return firstContactDate;
	}

	public void setFirstContactDate(Date firstContactDate) {
		this.firstContactDate = firstContactDate;
	}

	public FirstCourseTreatmentSummary getFirstTreatment() {
		return firstTreatment;
	}

	public void setFirstTreatment(FirstCourseTreatmentSummary firstTreatment) {
		this.firstTreatment = firstTreatment;
	}

	public java.util.Set getFollowUpCollection() {
		return followUpCollection;
	}

	public void setFollowUpCollection(java.util.Set followUpCollection) {
		this.followUpCollection = followUpCollection;
	}

	public String getHistologicGrade() {
		return histologicGrade;
	}

	public void setHistologicGrade(String histologicGrade) {
		this.histologicGrade = histologicGrade;
	}

	public String getHistology() {
		return histology;
	}

	public void setHistology(String histology) {
		this.histology = histology;
	}

	public Integer getHistologyCode() {
		return histologyCode;
	}

	public void setHistologyCode(Integer histologyCode) {
		this.histologyCode = histologyCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInitialDiagnosisDate() {
		return initialDiagnosisDate;
	}

	public void setInitialDiagnosisDate(Date initialDiagnosisDate) {
		this.initialDiagnosisDate = initialDiagnosisDate;
	}

	public String getLaterality() {
		return laterality;
	}

	public void setLaterality(String laterality) {
		this.laterality = laterality;
	}

	public String getPrimarySite() {
		return primarySite;
	}

	public void setPrimarySite(String primarySite) {
		this.primarySite = primarySite;
	}

	public String getPrimarySiteCode() {
		return primarySiteCode;
	}

	public void setPrimarySiteCode(String primarySiteCode) {
		this.primarySiteCode = primarySiteCode;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}


}