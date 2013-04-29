/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;


import java.sql.Date;

/**
 * @version 1.0
 * @created 19-Jun-2006 11:34:41 AM
 */
public class AgentOccurrence {

	private Date expirationDate;
	private String formCode;
	private int id;
	private String lotNumber;
	//private SubstanceAdministration substanceAdministration;

	public AgentOccurrence(){

	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

/*	public SubstanceAdministration getSubstanceAdministration() {
		return substanceAdministration;
	}

	public void setSubstanceAdministration(
			SubstanceAdministration substanceAdministration) {
		this.substanceAdministration = substanceAdministration;
	}
*/
}