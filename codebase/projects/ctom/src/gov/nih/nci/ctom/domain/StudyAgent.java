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
 * @version 1.0
 * @created 15-Jun-2006 2:04:31 PM
 */
public class StudyAgent {

	private int id;
	private boolean investigationalIndicator;
	private String investigationalNewDrugIdentifier;
	private String statusCode;
	private Date statusDate;
	//private Agent agent;
	//private Protocol study;
	private Set substanceAdministrationCollection;

	public StudyAgent(){

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isInvestigationalIndicator() {
		return investigationalIndicator;
	}

	public void setInvestigationalIndicator(boolean investigationalIndicator) {
		this.investigationalIndicator = investigationalIndicator;
	}

	public String getInvestigationalNewDrugIdentifier() {
		return investigationalNewDrugIdentifier;
	}

	public void setInvestigationalNewDrugIdentifier(
			String investigationalNewDrugIdentifier) {
		this.investigationalNewDrugIdentifier = investigationalNewDrugIdentifier;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public Set getSubstanceAdministrationCollection() {
		return substanceAdministrationCollection;
	}

	public void setSubstanceAdministrationCollection(
			Set substanceAdministrationCollection) {
		this.substanceAdministrationCollection = substanceAdministrationCollection;
	}

}