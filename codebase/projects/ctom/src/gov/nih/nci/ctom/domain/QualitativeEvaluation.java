/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

/**
 * @version 1.0
 * @created 23-Jun-2006 9:53:48 AM
 */
public class QualitativeEvaluation extends Assessment {

	private int anamResultAccuracyPercent;
	/**
	 * Values include: Yes, No.
	 */
	private String menstrualIndicator;
	/**
	 * Values include: Always Regular, Never Regular, Usually Regular.
	 */
	private String menstrualPatternTypeCode;
	private String painIndexCode;
	private String painIndexCodeSystem;
	/**
	 * Values include: 100 Normal, 90 Able to carry on normal activity, 80 Normal
	 * activity with effort, 70 Cares for self, etc.
	 */
	private String performanceStatusCode;
	private String performanceStatusCodeSystem;
	/**
	 * Values include: 3-Alive Disease Status Unknown, 1-Alive With Disease, 2-Alive
	 * With No Evidence Of Disease, 5-Died, 4-Unknown.
	 */
	private String survivalStatusCode;
	private String survivalStatusDescriptionText;

	public QualitativeEvaluation(){

	}

	public int getAnamResultAccuracyPercent() {
		return anamResultAccuracyPercent;
	}

	public void setAnamResultAccuracyPercent(int anamResultAccuracyPercent) {
		this.anamResultAccuracyPercent = anamResultAccuracyPercent;
	}

	public String isMenstrualIndicator() {
		return menstrualIndicator;
	}

	public void setMenstrualIndicator(String menstrualIndicator) {
		this.menstrualIndicator = menstrualIndicator;
	}

	public String getMenstrualPatternTypeCode() {
		return menstrualPatternTypeCode;
	}

	public void setMenstrualPatternTypeCode(String menstrualPatternTypeCode) {
		this.menstrualPatternTypeCode = menstrualPatternTypeCode;
	}

	public String getPainIndexCode() {
		return painIndexCode;
	}

	public void setPainIndexCode(String painIndexCode) {
		this.painIndexCode = painIndexCode;
	}

	public String getPainIndexCodeSystem() {
		return painIndexCodeSystem;
	}

	public void setPainIndexCodeSystem(String painIndexCodeSystem) {
		this.painIndexCodeSystem = painIndexCodeSystem;
	}

	public String getPerformanceStatusCode() {
		return performanceStatusCode;
	}

	public void setPerformanceStatusCode(String performanceStatusCode) {
		this.performanceStatusCode = performanceStatusCode;
	}

	public String getPerformanceStatusCodeSystem() {
		return performanceStatusCodeSystem;
	}

	public void setPerformanceStatusCodeSystem(String performanceStatusCodeSystem) {
		this.performanceStatusCodeSystem = performanceStatusCodeSystem;
	}

	public String getSurvivalStatusCode() {
		return survivalStatusCode;
	}

	public void setSurvivalStatusCode(String survivalStatusCode) {
		this.survivalStatusCode = survivalStatusCode;
	}

	public String getSurvivalStatusDescriptionText() {
		return survivalStatusDescriptionText;
	}

	public void setSurvivalStatusDescriptionText(
			String survivalStatusDescriptionText) {
		this.survivalStatusDescriptionText = survivalStatusDescriptionText;
	}

}