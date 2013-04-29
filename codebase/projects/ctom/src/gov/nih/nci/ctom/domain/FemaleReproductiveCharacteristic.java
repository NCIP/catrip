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
 * @created 19-Jun-2006 11:34:42 AM
 */
public class FemaleReproductiveCharacteristic {

	private boolean abortionIndicator;
	private int firstLiveBirthAge;
	private int id;
	private int liveBirthCount;
	private int menopauseAge;
	/**
	 * Values include:  Natural Menopause, Surgery Stopped Period, etc.
	 */
	private String menopauseReasonCode;
	private String menopauseReasonOtherText;
	private Date menopauseStartDate;
	private int stillBirthCount;

	public FemaleReproductiveCharacteristic(){

	}

	public boolean isAbortionIndicator() {
		return abortionIndicator;
	}

	public void setAbortionIndicator(boolean abortionIndicator) {
		this.abortionIndicator = abortionIndicator;
	}

	public int getFirstLiveBirthAge() {
		return firstLiveBirthAge;
	}

	public void setFirstLiveBirthAge(int firstLiveBirthAge) {
		this.firstLiveBirthAge = firstLiveBirthAge;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLiveBirthCount() {
		return liveBirthCount;
	}

	public void setLiveBirthCount(int liveBirthCount) {
		this.liveBirthCount = liveBirthCount;
	}

	public int getMenopauseAge() {
		return menopauseAge;
	}

	public void setMenopauseAge(int menopauseAge) {
		this.menopauseAge = menopauseAge;
	}

	public String getMenopauseReasonCode() {
		return menopauseReasonCode;
	}

	public void setMenopauseReasonCode(String menopauseReasonCode) {
		this.menopauseReasonCode = menopauseReasonCode;
	}

	public String getMenopauseReasonOtherText() {
		return menopauseReasonOtherText;
	}

	public void setMenopauseReasonOtherText(String menopauseReasonOtherText) {
		this.menopauseReasonOtherText = menopauseReasonOtherText;
	}

	public Date getMenopauseStartDate() {
		return menopauseStartDate;
	}

	public void setMenopauseStartDate(Date menopauseStartDate) {
		this.menopauseStartDate = menopauseStartDate;
	}

	public int getStillBirthCount() {
		return stillBirthCount;
	}

	public void setStillBirthCount(int stillBirthCount) {
		this.stillBirthCount = stillBirthCount;
	}

}