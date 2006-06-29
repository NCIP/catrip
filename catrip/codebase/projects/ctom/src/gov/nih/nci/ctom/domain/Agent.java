package gov.nih.nci.ctom.domain;

import java.util.Set;

/**
 * @version 1.0
 * @created 15-Jun-2006 2:09:59 PM
 */

/**
*
* @hibernate.class
*           table="AGENT"
*/

public class Agent {

	private String descriptionText;
	private int id;
	private String name;
	private String statusCode;
	private Set agentOccurrenceCollection;
	private Set studyAgentCollection;
	private Set substanceAdministrationCollection;

	public Agent(){

	}

	public Set getAgentOccurrenceCollection() {
		return agentOccurrenceCollection;
	}

	public void setAgentOccurrenceCollection(Set agentOccurrenceCollection) {
		this.agentOccurrenceCollection = agentOccurrenceCollection;
	}

	public String getDescriptionText() {
		return descriptionText;
	}

	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}

	/**
	   * @hibernate.id
	   *    column="ID"
	   *    generator-class="native"
	*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Set getStudyAgentCollection() {
		return studyAgentCollection;
	}

	public void setStudyAgentCollection(Set studyAgentCollection) {
		this.studyAgentCollection = studyAgentCollection;
	}

	public Set getSubstanceAdministrationCollection() {
		return substanceAdministrationCollection;
	}

	public void setSubstanceAdministrationCollection(
			Set substanceAdministrationCollection) {
		this.substanceAdministrationCollection = substanceAdministrationCollection;
	}

}