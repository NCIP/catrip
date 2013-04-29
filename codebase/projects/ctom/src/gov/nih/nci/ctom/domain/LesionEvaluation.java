/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

/**
 * @version 1.0
 * @created 21-Jun-2006 9:45:18 AM
 */
public class LesionEvaluation extends Assessment {

	/**
	 * Values include: N-New, R-Resolved, etc.
	 */
	public String evaluationCode;

	public LesionEvaluation(){

	}

	public String getEvaluationCode() {
		return evaluationCode;
	}

	public void setEvaluationCode(String evaluationCode) {
		this.evaluationCode = evaluationCode;
	}


}