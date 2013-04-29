/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

/**
 * @version 1.0
 * @created 28-Jun-2006 4:10:33 PM
 */
public class ParticipantEligibilityAnswer {

	private String answerText;
	private String checklistNumber;
	private int id;

	public ParticipantEligibilityAnswer(){

	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public String getChecklistNumber() {
		return checklistNumber;
	}

	public void setChecklistNumber(String checklistNumber) {
		this.checklistNumber = checklistNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}