package gov.nih.nci.ctom.domain;

import java.util.Set;

/**
 * @version 1.0
 * @created 15-Jun-2006 2:08:49 PM
 */
public class Investigator extends Person {

	private String nciIdentifier;
	private Set studyInvestigatorCollection;

	public Investigator(){

	}

	public String getNciIdentifier() {
		return nciIdentifier;
	}

	public void setNciIdentifier(String nciIdentifier) {
		this.nciIdentifier = nciIdentifier;
	}

	public Set getStudyInvestigatorCollection() {
		return studyInvestigatorCollection;
	}

	public void setStudyInvestigatorCollection(Set studyInvestigatorCollection) {
		this.studyInvestigatorCollection = studyInvestigatorCollection;
	}

}