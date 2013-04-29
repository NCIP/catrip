/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

/**
 * @version 1.0
 * @created 25-Jun-2006 2:47:50 PM
 */
public class HistopathologyGrade {

	public String commentText;
	public String gradeCode;
	/**
	 * Example: Nottingham.
	 */
	public String gradeCodeSystem;
	public int id;

	public HistopathologyGrade(){

	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getGradeCodeSystem() {
		return gradeCodeSystem;
	}

	public void setGradeCodeSystem(String gradeCodeSystem) {
		this.gradeCodeSystem = gradeCodeSystem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}