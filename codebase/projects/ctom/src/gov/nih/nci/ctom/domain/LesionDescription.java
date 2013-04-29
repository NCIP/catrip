/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

/**
 * @version 1.0
 * @created 23-Jun-2006 1:59:08 PM
 */
public class LesionDescription extends Observation {

	private String anatomicSiteCode;
	private String anatomicSiteCodeSystem;
	/**
	 * Values include: Flat Lesion, Nodular Lesion. 
	 */
	private String appearanceTypeCode;
	private String contactAnatomicSiteCode;
	private String contactAnatomicSiteCodeSystem;
	private int dimensionProduct;
	private int evaluationNumber;
	private String lesionNumber;
	private String measurableIndicator;
	/**
	 * Values include: PET scan, Gallium scan, etc.
	 */
	private String methodCode;
	private String previouslyIrradiatedSiteIndicator;
	/**
	 * Values include: Target Lesion, Non target Lesion.
	 */
	private String targetNonTargetCode;
	private int xDimension;
	private int yDimension;
	private int zDimension;

	public LesionDescription(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public String getAnatomicSiteCode() {
		return anatomicSiteCode;
	}

	public void setAnatomicSiteCode(String anatomicSiteCode) {
		this.anatomicSiteCode = anatomicSiteCode;
	}

	public String getAnatomicSiteCodeSystem() {
		return anatomicSiteCodeSystem;
	}

	public void setAnatomicSiteCodeSystem(String anatomicSiteCodeSystem) {
		this.anatomicSiteCodeSystem = anatomicSiteCodeSystem;
	}

	public String getAppearanceTypeCode() {
		return appearanceTypeCode;
	}

	public void setAppearanceTypeCode(String appearanceTypeCode) {
		this.appearanceTypeCode = appearanceTypeCode;
	}

	public String getContactAnatomicSiteCode() {
		return contactAnatomicSiteCode;
	}

	public void setContactAnatomicSiteCode(String contactAnatomicSiteCode) {
		this.contactAnatomicSiteCode = contactAnatomicSiteCode;
	}

	public String getContactAnatomicSiteCodeSystem() {
		return contactAnatomicSiteCodeSystem;
	}

	public void setContactAnatomicSiteCodeSystem(
			String contactAnatomicSiteCodeSystem) {
		this.contactAnatomicSiteCodeSystem = contactAnatomicSiteCodeSystem;
	}

	public int getDimensionProduct() {
		return dimensionProduct;
	}

	public void setDimensionProduct(int dimensionProduct) {
		this.dimensionProduct = dimensionProduct;
	}

	public int getEvaluationNumber() {
		return evaluationNumber;
	}

	public void setEvaluationNumber(int evaluationNumber) {
		this.evaluationNumber = evaluationNumber;
	}

	public String getLesionNumber() {
		return lesionNumber;
	}

	public void setLesionNumber(String lesionNumber) {
		this.lesionNumber = lesionNumber;
	}

	public String isMeasurableIndicator() {
		return measurableIndicator;
	}

	public void setMeasurableIndicator(String measurableIndicator) {
		this.measurableIndicator = measurableIndicator;
	}

	public String getMethodCode() {
		return methodCode;
	}

	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}

	public String getPreviouslyIrradiatedSiteIndicator() {
		return previouslyIrradiatedSiteIndicator;
	}

	public void setPreviouslyIrradiatedSiteIndicator(
			String previouslyIrradiatedSiteIndicator) {
		this.previouslyIrradiatedSiteIndicator = previouslyIrradiatedSiteIndicator;
	}

	public String getTargetNonTargetCode() {
		return targetNonTargetCode;
	}

	public void setTargetNonTargetCode(String targetNonTargetCode) {
		this.targetNonTargetCode = targetNonTargetCode;
	}

	public int getXDimension() {
		return xDimension;
	}

	public void setXDimension(int dimension) {
		xDimension = dimension;
	}

	public int getYDimension() {
		return yDimension;
	}

	public void setYDimension(int dimension) {
		yDimension = dimension;
	}

	public int getZDimension() {
		return zDimension;
	}

	public void setZDimension(int dimension) {
		zDimension = dimension;
	}

}