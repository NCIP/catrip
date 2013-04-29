/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.tumorregistry.domain;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

/**
 * @version 1.0
 * @created 01-Sep-2006 1:33:18 PM
 */
public class Followup {

	private Long id;
	private String cancerStatus;
	private String contactMethod;
	private Date date;
	private Recurrence recurrence;
	private Diagnosis diagnosis;
	// data loading
	private Long accnNo;
	private Long seqNo;
	
	public Followup(){

	}

	public void finalize() throws Throwable {

	}

	public String getCancerStatus() {
		return cancerStatus;
	}

	public void setCancerStatus(String cancerStatus) {
		this.cancerStatus = cancerStatus;
	}

	public String getContactMethod() {
		return contactMethod;
	}

	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Recurrence getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(Recurrence recurrence) {
		this.recurrence = recurrence;
	}

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Long getAccnNo() {
		return accnNo;
	}

	public void setAccnNo(Long accnNo) {
		this.accnNo = accnNo;
	}

	public Long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

}