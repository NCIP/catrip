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
 * 
 * 
 * @version 1.0
 * @created 19-Jun-2006 11:34:41 AM
 */

/**
*
* @hibernate.class
*           table="ASSESSMENT"
*
*
*/

public abstract class Assessment {

	private Date evaluationDate;
	private long id;
	private Set observationCollection;
	private Set assessmentRelationshipCollection;

	public Assessment(){

	}

	public Set getAssessmentRelationshipCollection() {
		return assessmentRelationshipCollection;
	}

	public void setAssessmentRelationshipCollection(
			Set assessmentRelationshipCollection) {
		this.assessmentRelationshipCollection = assessmentRelationshipCollection;
	}

    /**
     * @hibernate.property
     *   column="EVALUATION_DATE"
     *   type="date"
     */
	public Date getEvaluationDate() {
		return evaluationDate;
	}

	public void setEvaluationDate(Date evaluationDate) {
		this.evaluationDate = evaluationDate;
	}

	/**
	   * @hibernate.id
	   *    column="ID"
	   *    generator-class="native"
	*/
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set getObservationCollection() {
		return observationCollection;
	}

	public void setObservationCollection(Set observationCollection) {
		this.observationCollection = observationCollection;
	}

}