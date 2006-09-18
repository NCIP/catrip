package edu.duke.cabig.tumorregistry.domain;
import java.lang.Long;
import java.util.Date;





/**
 * @version 1.0
 * @created 01-Sep-2006 1:33:23 PM
 */
public class FirstCourseTreatmentSummary {

	private Long id;
	private Date firstSurgeryDate;
	private Date mostDefinitiveSurgeryDate;
	private Date firstTreatmentDate;
	private Date FirstSystemicTreatmentDate;

	public FirstCourseTreatmentSummary(){

	}

	public void finalize() throws Throwable {

	}

	public Date getFirstSurgeryDate() {
		return firstSurgeryDate;
	}

	public void setFirstSurgeryDate(Date firstSurgeryDate) {
		this.firstSurgeryDate = firstSurgeryDate;
	}

	public Date getFirstSystemicTreatmentDate() {
		return FirstSystemicTreatmentDate;
	}

	public void setFirstSystemicTreatmentDate(Date firstSystemicTreatmentDate) {
		FirstSystemicTreatmentDate = firstSystemicTreatmentDate;
	}

	public Date getFirstTreatmentDate() {
		return firstTreatmentDate;
	}

	public void setFirstTreatmentDate(Date firstTreatmentDate) {
		this.firstTreatmentDate = firstTreatmentDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getMostDefinitiveSurgeryDate() {
		return mostDefinitiveSurgeryDate;
	}

	public void setMostDefinitiveSurgeryDate(Date mostDefinitiveSurgeryDate) {
		this.mostDefinitiveSurgeryDate = mostDefinitiveSurgeryDate;
	}

}