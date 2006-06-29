package gov.nih.nci.ctom.domain;

import java.util.Set;

/**
 * @version 1.0
 * @created 15-Jun-2006 2:09:10 PM
 */
public class HealthcareSite extends Organization {

	private String nciInstituteCode;
	private Set studySiteCollection;
	private Set healthcareSiteParticipant;

	public HealthcareSite(){

	}

	public Set getHealthcareSiteParticipant() {
		return healthcareSiteParticipant;
	}

	public void setHealthcareSiteParticipant(Set healthcareSiteParticipant) {
		this.healthcareSiteParticipant = healthcareSiteParticipant;
	}

	public String getNciInstituteCode() {
		return nciInstituteCode;
	}

	public void setNciInstituteCode(String nciInstituteCode) {
		this.nciInstituteCode = nciInstituteCode;
	}

	public Set getStudySiteCollection() {
		return studySiteCollection;
	}

	public void setStudySiteCollection(Set studySiteCollection) {
		this.studySiteCollection = studySiteCollection;
	}

}