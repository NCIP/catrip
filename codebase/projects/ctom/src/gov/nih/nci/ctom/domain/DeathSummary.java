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
public class DeathSummary extends Assessment {

	private String autopsiedIndicator;
	/**
	 * Values include: I-Infection, M-Malignant Disease, O-Other, T-Toxicity From
	 * Protocol Treatment.
	 */
	private String deathCauseCode;
	private String deathCauseText;
	private Date deathDate;

	public DeathSummary(){

	}

	public String isAutopsiedIndicator() {
		return autopsiedIndicator;
	}

	public void setAutopsiedIndicator(String autopsiedIndicator) {
		this.autopsiedIndicator = autopsiedIndicator;
	}

	public String getDeathCauseCode() {
		return deathCauseCode;
	}

	public void setDeathCauseCode(String deathCauseCode) {
		this.deathCauseCode = deathCauseCode;
	}

	public String getDeathCauseText() {
		return deathCauseText;
	}

	public void setDeathCauseText(String deathCauseText) {
		this.deathCauseText = deathCauseText;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

}