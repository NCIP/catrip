package gov.nih.nci.ctom.domain;

import java.util.Set;

/**
 * @version 1.0
 * @created 27-Jun-2006 12:43:53 PM
 */
public class HealthcareSiteParticipant {

	public int id;
	public String participantIdentifier;
	public Set healthcareSiteRoleCollection;
	//public Participant participant;

	public HealthcareSiteParticipant(){

	}

	public Set getHealthcareSiteRoleCollection() {
		return healthcareSiteRoleCollection;
	}

	public void setHealthcareSiteRoleCollection(Set healthcareSiteRoleCollection) {
		this.healthcareSiteRoleCollection = healthcareSiteRoleCollection;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParticipantIdentifier() {
		return participantIdentifier;
	}

	public void setParticipantIdentifier(String participantIdentifier) {
		this.participantIdentifier = participantIdentifier;
	}

}