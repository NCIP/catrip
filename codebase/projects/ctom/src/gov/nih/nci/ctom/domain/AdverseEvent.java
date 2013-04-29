/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

import java.sql.Date;
import java.util.Set;

/**
 * @version 1.0
 * @created 19-Jun-2006 11:34:40 AM
 */

/**
*
* @hibernate.class
*           table="ASSESSMENT_EVENT"
*/

public class AdverseEvent extends Assessment {

	/**
	 * Values include: 2-Dose Reduced, 1-None, 3-Regimen Interrupted, 4-Therapy
	 * Discontinued/Interrupted/ Reduced, etc.
	 */
	private String actionTakenCode;
	/**
	 * Values include: 1 single episode, 2 intermittent, 3 continuous.
	 */
	private String conditionPatternCode;
	/**
	 * Values include: Definite, Possible, Probable, Unlikely, Unrelated	.
	 */
	private String ctcAttributionCode;
	private String ctcAttributionCodeSystem;
	/**
	 * Values include: Pain, Infection, Allergy/Immunology, etc.
	 */
	private String ctcCategoryCode;
	private String ctcCategoryCodeSystem;
	/**
	 * Values include: 0-No Adverse Event Or Within Normal Limits, 1- Mild Adverse
	 * Event, 2- Moderate Adverse Event, 3- Severe and Undesirable Adverse Event, etc.
	 */
	private String ctcGradeCode;
	private String ctcGradeCodeSystem;
	private String ctcTermTypeCode;
	private String ctcTermTypeCodeSystem;
	private String descriptionText;
	private String doseLimitingToxicityDescriptionText;
	private boolean doseLimitingToxicityIndicator;
	private Date onsetDate;
	/**
	 * Values include: 1-Recovered, 2-Still Under Treatment/Observation, 3-Alive With
	 * Sequelae, 4-Died, etc.
	 */
	private String outcomeCode;
	private Date resolvedDate;
	/**
	 * Values include: 1-No, 2-Life-Threatening, 3-Death, 4-Disability, etc.
	 */
	private String seriousReasonCode;
	private Set adverseEventTherapyCollection;

	public AdverseEvent(){

	}

	
	/**
	 * @hibernate.property
	 * column="ACTION_TAKEN_CODE"
	 * length="200"
	 * type="string"
	 **/
	public String getActionTakenCode() {
		return actionTakenCode;
	}

	public void setActionTakenCode(String actionTakenCode) {
		this.actionTakenCode = actionTakenCode;
	}

	public Set getAdverseEventTherapyCollection() {
		return adverseEventTherapyCollection;
	}

	public void setAdverseEventTherapyCollection(
			Set adverseEventTherapyCollection) {
		this.adverseEventTherapyCollection = adverseEventTherapyCollection;
	}

	/**
	 * @hibernate.property
	 * column="CONDITION_PATTERN_CODE"
	 * length="200"
	 * type="string"
	 **/
	public String getConditionPatternCode() {
		return conditionPatternCode;
	}

	public void setConditionPatternCode(String conditionPatternCode) {
		this.conditionPatternCode = conditionPatternCode;
	}

	/**
	 * @hibernate.property
	 * column="CTC_ATTRIBUTE_CODE"
	 * length="200"
	 * type="string"
	 **/
	public String getCtcAttributionCode() {
		return ctcAttributionCode;
	}

	public void setCtcAttributionCode(String ctcAttributionCode) {
		this.ctcAttributionCode = ctcAttributionCode;
	}

	/**
	 * @hibernate.property
	 * column="CTC_ATTRIBUTE_CODE_SYSTEM"
	 * length="200"
	 * type="string"
	 **/
	public String getCtcAttributionCodeSystem() {
		return ctcAttributionCodeSystem;
	}

	public void setCtcAttributionCodeSystem(String ctcAttributionCodeSystem) {
		this.ctcAttributionCodeSystem = ctcAttributionCodeSystem;
	}

	/**
	 * @hibernate.property
	 * column="CTC_CATEGORY_CODE"
	 * length="200"
	 * type="string"
	 **/
	public String getCtcCategoryCode() {
		return ctcCategoryCode;
	}

	public void setCtcCategoryCode(String ctcCategoryCode) {
		this.ctcCategoryCode = ctcCategoryCode;
	}

	/**
	 * @hibernate.property
	 * column="CTC_CATEGORY_CODE_SYSTEM"
	 * length="200"
	 * type="string"
	 **/
	public String getCtcCategoryCodeSystem() {
		return ctcCategoryCodeSystem;
	}

	public void setCtcCategoryCodeSystem(String ctcCategoryCodeSystem) {
		this.ctcCategoryCodeSystem = ctcCategoryCodeSystem;
	}

	/**
	 * @hibernate.property
	 * column="CTC_GRADE_CODE"
	 * length="200"
	 * type="string"
	 **/
	public String getCtcGradeCode() {
		return ctcGradeCode;
	}

	public void setCtcGradeCode(String ctcGradeCode) {
		this.ctcGradeCode = ctcGradeCode;
	}

	/**
	 * @hibernate.property
	 * column="CTC_GRADE_CODE_SYSTEM"
	 * length="200"
	 * type="string"
	 **/
	public String getCtcGradeCodeSystem() {
		return ctcGradeCodeSystem;
	}

	public void setCtcGradeCodeSystem(String ctcGradeCodeSystem) {
		this.ctcGradeCodeSystem = ctcGradeCodeSystem;
	}

	/**
	 * @hibernate.property
	 * column="CTC_TERM_TYPE_CODE"
	 * length="200"
	 * type="string"
	 **/
	public String getCtcTermTypeCode() {
		return ctcTermTypeCode;
	}

	public void setCtcTermTypeCode(String ctcTermTypeCode) {
		this.ctcTermTypeCode = ctcTermTypeCode;
	}

	/**
	 * @hibernate.property
	 * column="CTC_TERM_TYPE_CODE_SYSTEM"
	 * length="200"
	 * type="string"
	 **/
	public String getCtcTermTypeCodeSystem() {
		return ctcTermTypeCodeSystem;
	}

	public void setCtcTermTypeCodeSystem(String ctcTermTypeCodeSystem) {
		this.ctcTermTypeCodeSystem = ctcTermTypeCodeSystem;
	}

	/**
	 * @hibernate.property
	 * column="DESCRIPTION_TEXT"
	 * length="200"
	 * type="string"
	 **/
	public String getDescriptionText() {
		return descriptionText;
	}

	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}

	/**
	 * @hibernate.property
	 * column="CTC_TERM_TYPE_CODE"
	 * length="200"
	 * type="string"
	 **/
	public String getDoseLimitingToxicityDescriptionText() {
		return doseLimitingToxicityDescriptionText;
	}

	public void setDoseLimitingToxicityDescriptionText(
			String doseLimitingToxicityDescriptionText) {
		this.doseLimitingToxicityDescriptionText = doseLimitingToxicityDescriptionText;
	}

	public boolean isDoseLimitingToxicityIndicator() {
		return doseLimitingToxicityIndicator;
	}

	public void setDoseLimitingToxicityIndicator(
			boolean doseLimitingToxicityIndicator) {
		this.doseLimitingToxicityIndicator = doseLimitingToxicityIndicator;
	}

    /**
     * @hibernate.property
     *   column="ONSET_DATE"
     *   type="date"
     */
	public Date getOnsetDate() {
		return onsetDate;
	}

	public void setOnsetDate(Date onsetDate) {
		this.onsetDate = onsetDate;
	}

	public String getOutcomeCode() {
		return outcomeCode;
	}

	public void setOutcomeCode(String outcomeCode) {
		this.outcomeCode = outcomeCode;
	}

    /**
     * @hibernate.property
     *   column="RESOLVED_DATE"
     *   type="date"
     */
	public Date getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(Date resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

	public String getSeriousReasonCode() {
		return seriousReasonCode;
	}

	public void setSeriousReasonCode(String seriousReasonCode) {
		this.seriousReasonCode = seriousReasonCode;
	}

}