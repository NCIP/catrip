/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

/**
 * @version 1.0
 * @created 19-Jun-2006 11:34:41 AM
 */
public class AssessmentRelationship {

	private String commentText;
	private int id;
	private String typeCode;
	private Assessment assessment_1;
	private Assessment assessment_2;

	public AssessmentRelationship(){

	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public Assessment getAssessment_1() {
		return assessment_1;
	}

	public void setAssessment_1(Assessment assessment_1) {
		this.assessment_1 = assessment_1;
	}

	public Assessment getAssessment_2() {
		return assessment_2;
	}

	public void setAssessment_2(Assessment assessment_2) {
		this.assessment_2 = assessment_2;
	}

}