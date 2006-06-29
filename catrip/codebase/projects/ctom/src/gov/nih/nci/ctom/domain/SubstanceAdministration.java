package gov.nih.nci.ctom.domain;

import java.util.Set;

/**
 * @version 1.0
 * @created 20-Jun-2006 8:20:55 AM 
 */
public class SubstanceAdministration extends Activity {

	/**
	 * Values include: Agent Added, Agent Dose Decreased, Agent Dose Increased, etc.
	 */
	private String doseChangeCode;
	/**
	 * Values include: 9-Unknown, 3-No, 1-Yes Planned, 2-Yes Unplanned.
	 */
	private int doseChangeIndicatorCode;
	/**
	 * Values include: Daily, Weekly, Monthly, Yearly, etc.
	 */
	private String doseFrequencyCode;
	private String doseFrequencyText;
	/**
	 * Values include:  Gastrostomy Tube, CIV- Continuous Intravenous Infusion, IA-
	 * Intra-Arterial, etc.
	 */
	private String routeCode;
	private int singleDose;
	private String singleDoseUnitOfMeasureCode;
	private int totalDose;
	private String totalDoseUnitOfMeasureCode;
	//private Agent agent;
	//private StudyAgent studyAgent;
	private Set agentOccurranceCollection;

	public SubstanceAdministration(){

	}

/*	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
*/
	public String getDoseChangeCode() {
		return doseChangeCode;
	}

	public void setDoseChangeCode(String doseChangeCode) {
		this.doseChangeCode = doseChangeCode;
	}

	public int getDoseChangeIndicatorCode() {
		return doseChangeIndicatorCode;
	}

	public void setDoseChangeIndicatorCode(int doseChangeIndicatorCode) {
		this.doseChangeIndicatorCode = doseChangeIndicatorCode;
	}

	public String getDoseFrequencyCode() {
		return doseFrequencyCode;
	}

	public void setDoseFrequencyCode(String doseFrequencyCode) {
		this.doseFrequencyCode = doseFrequencyCode;
	}

	public String getDoseFrequencyText() {
		return doseFrequencyText;
	}

	public void setDoseFrequencyText(String doseFrequencyText) {
		this.doseFrequencyText = doseFrequencyText;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public int getSingleDose() {
		return singleDose;
	}

	public void setSingleDose(int singleDose) {
		this.singleDose = singleDose;
	}

	public String getSingleDoseUnitOfMeasureCode() {
		return singleDoseUnitOfMeasureCode;
	}

	public void setSingleDoseUnitOfMeasureCode(String singleDoseUnitOfMeasureCode) {
		this.singleDoseUnitOfMeasureCode = singleDoseUnitOfMeasureCode;
	}

/*	public StudyAgent getStudyAgent() {
		return studyAgent;
	}

	public void setStudyAgent(StudyAgent studyAgent) {
		this.studyAgent = studyAgent;
	}
*/
	public int getTotalDose() {
		return totalDose;
	}

	public void setTotalDose(int totalDose) {
		this.totalDose = totalDose;
	}

	public String getTotalDoseUnitOfMeasureCode() {
		return totalDoseUnitOfMeasureCode;
	}

	public void setTotalDoseUnitOfMeasureCode(String totalDoseUnitOfMeasureCode) {
		this.totalDoseUnitOfMeasureCode = totalDoseUnitOfMeasureCode;
	}

	public Set getAgentOccurranceCollection() {
		return agentOccurranceCollection;
	}

	public void setAgentOccurranceCollection(Set agentOccurranceCollection) {
		this.agentOccurranceCollection = agentOccurranceCollection;
	}

}