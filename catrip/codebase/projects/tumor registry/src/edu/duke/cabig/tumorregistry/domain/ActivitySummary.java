package edu.duke.cabig.tumorregistry.domain;
import java.lang.Long;
import java.util.Date;





/**
 * @version 1.0
 * @created 01-Sep-2006 1:33:24 PM
 */
public class ActivitySummary {

	private Long id;
	private Date localDate;
	private Date summaryDate;
	private String localCharacterization;
	private String summaryCharacterization;
	private java.util.Set localActivityCollection;
	private java.util.Set summaryActivityCollection;
	private Diagnosis diagnosis;

	// data load
	private String type;
	public ActivitySummary(){

	}

	public void finalize() throws Throwable {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public java.util.Set getLocalActivityCollection() {
		return localActivityCollection;
	}

	public void setLocalActivityCollection(java.util.Set localActivityCollection) {
		this.localActivityCollection = localActivityCollection;
	}

	public String getLocalCharacterization() {
		return localCharacterization;
	}

	public void setLocalCharacterization(String localCharacterization) {
		this.localCharacterization = localCharacterization;
	}

	public Date getLocalDate() {
		return localDate;
	}

	public void setLocalDate(Date localDate) {
		this.localDate = localDate;
	}

	public java.util.Set getSummaryActivityCollection() {
		return summaryActivityCollection;
	}

	public void setSummaryActivityCollection(java.util.Set summaryActivityCollection) {
		this.summaryActivityCollection = summaryActivityCollection;
	}

	public String getSummaryCharacterization() {
		return summaryCharacterization;
	}

	public void setSummaryCharacterization(String summaryCharacterization) {
		this.summaryCharacterization = summaryCharacterization;
	}

	public Date getSummaryDate() {
		return summaryDate;
	}

	public void setSummaryDate(Date summaryDate) {
		this.summaryDate = summaryDate;
	}

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}