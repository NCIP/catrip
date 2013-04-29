/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

/**
 * @version 1.0
 * @created 26-Jun-2006 8:35:12 AM
 */
public class Radiation extends Procedure {

	private String dose;
	/**
	 * Values include: Gray, Centigray, Radiation Absorbed Dose.
	 */
	private String doseUnitOfMeasureCode;
	private String scheduleText;
	private String therapyExtentCode;

	public Radiation(){

	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getDoseUnitOfMeasureCode() {
		return doseUnitOfMeasureCode;
	}

	public void setDoseUnitOfMeasureCode(String doseUnitOfMeasureCode) {
		this.doseUnitOfMeasureCode = doseUnitOfMeasureCode;
	}

	public String getScheduleText() {
		return scheduleText;
	}

	public void setScheduleText(String scheduleText) {
		this.scheduleText = scheduleText;
	}

	public String getTherapyExtentCode() {
		return therapyExtentCode;
	}

	public void setTherapyExtentCode(String therapyExtentCode) {
		this.therapyExtentCode = therapyExtentCode;
	}

}