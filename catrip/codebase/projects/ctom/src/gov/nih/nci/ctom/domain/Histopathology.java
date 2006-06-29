package gov.nih.nci.ctom.domain;

import java.util.Set;

/**
 * @version 1.0
 * @created 25-Jun-2006 2:43:55 PM
 */
public class Histopathology extends Observation {

	/**
	 * Values include: Positive, Negative, Indeterminate, Not Done.
	 */
	private String grossExamResultCode;
	private boolean involvedSurgicalMarginIndicator;
	private String reportDescriptiveText;
	private Set neoplasmCollection;
	private Set histopathologyGradeCollection;

	public Histopathology(){

	}

	public String getGrossExamResultCode() {
		return grossExamResultCode;
	}

	public void setGrossExamResultCode(String grossExamResultCode) {
		this.grossExamResultCode = grossExamResultCode;
	}

	public Set getHistopathologyGradeCollection() {
		return histopathologyGradeCollection;
	}

	public void setHistopathologyGradeCollection(Set histopathologyGradeCollection) {
		this.histopathologyGradeCollection = histopathologyGradeCollection;
	}

	public boolean isInvolvedSurgicalMarginIndicator() {
		return involvedSurgicalMarginIndicator;
	}

	public void setInvolvedSurgicalMarginIndicator(
			boolean involvedSurgicalMarginIndicator) {
		this.involvedSurgicalMarginIndicator = involvedSurgicalMarginIndicator;
	}

	public Set getNeoplasmCollection() {
		return neoplasmCollection;
	}

	public void setNeoplasmCollection(Set neoplasmCollection) {
		this.neoplasmCollection = neoplasmCollection;
	}

	public String getReportDescriptiveText() {
		return reportDescriptiveText;
	}

	public void setReportDescriptiveText(String reportDescriptiveText) {
		this.reportDescriptiveText = reportDescriptiveText;
	}

}