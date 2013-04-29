/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

/**
 * @version 1.0
 * @created 29-Jun-2006 11:02:19 AM
 */
public class ObservationRelationship {

	private String commentText;
	private int id;
	private String typeCode;
	private Observation observation_1;
	private Observation observation_2;


	public ObservationRelationship(){

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


	public Observation getObservation_1() {
		return observation_1;
	}


	public void setObservation_1(Observation observation_1) {
		this.observation_1 = observation_1;
	}


	public Observation getObservation_2() {
		return observation_2;
	}


	public void setObservation_2(Observation observation_2) {
		this.observation_2 = observation_2;
	}


	public String getTypeCode() {
		return typeCode;
	}


	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

}