package gov.nih.nci.ctom.domain;

import java.util.Set;

/**
 * @version 1.0
 * @created 15-Jun-2006 2:02:17 PM
 */
public class EligibilityCriteria {

	/**
	 * Values include: Yes, No.
	 */
	private boolean expectedAnswerIndicator;
	private int id;
	private int questionNumber;
	private String questionText;
	private Set protocolCollection;
	private Set participantEligibilityAnswerCollection;

	public EligibilityCriteria(){

	}

	public boolean isExpectedAnswerIndicator() {
		return expectedAnswerIndicator;
	}

	public void setExpectedAnswerIndicator(boolean expectedAnswerIndicator) {
		this.expectedAnswerIndicator = expectedAnswerIndicator;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set getParticipantEligibilityAnswerCollection() {
		return participantEligibilityAnswerCollection;
	}

	public void setParticipantEligibilityAnswerCollection(
			Set participantEligibilityAnswerCollection) {
		this.participantEligibilityAnswerCollection = participantEligibilityAnswerCollection;
	}

	public Set getProtocolCollection() {
		return protocolCollection;
	}

	public void setProtocolCollection(Set protocolCollection) {
		this.protocolCollection = protocolCollection;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

}