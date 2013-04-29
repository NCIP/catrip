/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

import java.util.Set;

/**
 * 
 * 
 * @version 1.0
 * @created 26-Jun-2006 9:47:23 AM
 */
public class SpecimenCollection extends Procedure {

	/**
	 * Values include: Abdominal/ ascites effusion, Biopsy, Bronchial alveolar lavage
	 * (BAL), etc.
	 */
	public String methodCode;
	/**
	 * Values include: Normal or Abnormal.
	 */
	public String siteConditionCode;
	public Set specimenCollection;

	public SpecimenCollection(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public String getMethodCode() {
		return methodCode;
	}

	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}

	public String getSiteConditionCode() {
		return siteConditionCode;
	}

	public void setSiteConditionCode(String siteConditionCode) {
		this.siteConditionCode = siteConditionCode;
	}

	public Set getSpecimenCollection() {
		return specimenCollection;
	}

	public void setSpecimenCollection(Set specimenCollection) {
		this.specimenCollection = specimenCollection;
	}

}