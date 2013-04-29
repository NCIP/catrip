/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

import java.sql.Date;

/**
 * @version 1.0
 * @created 19-Jun-2006 11:34:41 AM
 */
public class AdverseEventTherapy {

	private int delayDuration;
	private String delayDurationUnitOfMeasureCode;
	private long id;
	/**
	 * Values include: None, Symptomatic, Supportive, Vigorous, Supportive.
	 */
	private String intensityCode;
	private Date treatmentDate;

	public AdverseEventTherapy(){

	}

	public int getDelayDuration() {
		return delayDuration;
	}

	public void setDelayDuration(int delayDuration) {
		this.delayDuration = delayDuration;
	}

	public String getDelayDurationUnitOfMeasureCode() {
		return delayDurationUnitOfMeasureCode;
	}

	public void setDelayDurationUnitOfMeasureCode(
			String delayDurationUnitOfMeasureCode) {
		this.delayDurationUnitOfMeasureCode = delayDurationUnitOfMeasureCode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIntensityCode() {
		return intensityCode;
	}

	public void setIntensityCode(String intensityCode) {
		this.intensityCode = intensityCode;
	}

	public Date getTreatmentDate() {
		return treatmentDate;
	}

	public void setTreatmentDate(Date treatmentDate) {
		this.treatmentDate = treatmentDate;
	}

}