/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

/**
 * [Additional Documentation] In the particular case where the activities are
 * analysis tasks, this is a verb phrase that specifies the semantic link
 * between two AnalysisTasks. [BRIDG] Examples: (1) specification that a
 * particular value in the response to one AnalysisTask dictates navigation to
 * another AnalysisTask (e.g., if analysis of the distribution of the data shows
 * that it is not normally distributed, you would navigate to the non-parametric
 * version of the statistical test) (2) the value of a response may be
 * determined from the value of a set of other fields (e.g., the standard error
 * of the mean is derived from the mean, the standard deviation and the sample
 * size). (3) the behavior of a field for another AnalysisTask is determined by
 * the value of a response (e.g., the choice of prior distribution changes your
 * Bayesian model). [End Documentation]
 * 
 * @version 1.0
 * @created 19-Jun-2006 11:34:40 AM
 */
public class ActivityRelationship {

	private Activity activity_1;
	private Activity activity_2;
	private String commentText;

	private int id;

	private int sequenceNumber;

	/**
	 * values such as, component, is sequel, etc.
	 */
	private String typeCode;

	// public Activity activity;

	public ActivityRelationship() {

	}

	public void finalize() throws Throwable {

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

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public Activity getActivity_1() {
		return activity_1;
	}

	public void setActivity_1(Activity activityId_1) {
		this.activity_1 = activityId_1;
	}

	public Activity getActivity_2() {
		return activity_2;
	}

	public void setActivity_2(Activity activityId_2) {
		this.activity_2 = activityId_2;
	}

}