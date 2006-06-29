package gov.nih.nci.ctom.domain;



import java.sql.Date;
import java.util.Set;

/**
 * [Additional Documentation] A systematic evaluation of an observation or an
 * intervention (for example, treatment, drug, device, procedure or system) in one
 * or more subjects. Frequently this is a test of a particular hypothesis about
 * the treatment, drug, device, procedure or system. [CDAM]  A study can be either
 * primary or correlative. A study is considered a primary study if it has one or
 * more correlative studies. A correlative study extends the objectives or
 * observations of a primary study, enrolling the same, or a subset of the same,
 * subjects as the primary study. A Clinical Trial is a Study with type=
 * "intervention" with subjects of type="human". [BRIDG] [End Documentation]
 * @version 1.0
 * @updated 15-Jun-2006 1:58:46 PM
 */

/**
 * @hibernate.class table="PROTOCOL"
 * 
 */
public class Protocol {

	private Date amendmentDate;
	private int amendmentIdentifier;
	private boolean blindedIndicator;
	private String descriptionText;
	/**
	 * Values Include: A-AIDS, B-Benign, C-Cancer.
	 */
	private String diseaseCode;
	private int id;
	/**
	 * Values include: D-Diagnostic Protocol, GN-Genetic Non-therapeutic Protocol, etc.
	 */
	private String intentCode;
	private String longTitleText;
	/**
	 * Values include: CTEP, CTEP-CTMS, CTEP-CDUS Complete, etc.
	 */
	private String monitorCode;
	private boolean multiInstitutionIndicator;
	private String navyNCIIdentifier;
	private String nciIdentifier;
	/**
	 * Values include: I, I/II, II, III, NA.
	 */
	private String phaseCode;
	private String precisText;
	private boolean randomizedIndicator;
	/**
	 * [Additional Documentation] A name or abbreviated title by which the document is
	 * known. [BRIDG] [End Documentation]
	 */
	private String shortTitleText;
	/**
	 * Values include: AB-Abbott Labs, AL-Alkermes, Inc., APH- Angiotech, AM- Amgen,
	 * etc.
	 */
	private String sponsorCode;
	private int targetAccrualNumber;
	private Set protocolStatusCollection;
	private Set eligibilityCriteriaCollection;
	private Set studyInvestigatorCollection;
	private Set studyAgentCollection;
	private Set studySiteCollection;

	public Protocol(){

	}

	/**
	 * @hibernate.property
	 * column="AMENDMENT_DATE"
	 * type="DATE"
	 **/
	public Date getAmendmentDate() {
		return amendmentDate;
	}

	public void setAmendmentDate(Date amendmentDate) {
		this.amendmentDate = amendmentDate;
	}

	/**
	 * @hibernate.property
	 * column="AMENDMENT_IDENTIFIER"
	 * type="INT"
	 **/
	public int getAmendmentIdentifier() {
		return amendmentIdentifier;
	}

	public void setAmendmentIdentifier(int amendmentIdentifier) {
		this.amendmentIdentifier = amendmentIdentifier;
	}

	/**
	 * @hibernate.property
	 * column="blinded_Indicator"
	 * length="200"
	 * type="STRING"
	 **/
	public boolean isBlindedIndicator() {
		return blindedIndicator;
	}

	public void setBlindedIndicator(boolean blindedIndicator) {
		this.blindedIndicator = blindedIndicator;
	}

	/**
	 * @hibernate.property
	 * column="description_Text"
	 * length="200"
	 * type="STRING"
	 **/
	public String getDescriptionText() {
		return descriptionText;
	}

	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}

	/**
	 * @hibernate.property
	 * column="disease_Code"
	 * length="200"
	 * type="STRING"
	 **/
	public String getDiseaseCode() {
		return diseaseCode;
	}

	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	/**
	   *
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

	public String getIntentCode() {
		return intentCode;
	}

	public void setIntentCode(String intentCode) {
		this.intentCode = intentCode;
	}

	public String getLongTitleText() {
		return longTitleText;
	}

	public void setLongTitleText(String longTitleText) {
		this.longTitleText = longTitleText;
	}

	public String getMonitorCode() {
		return monitorCode;
	}

	public void setMonitorCode(String monitorCode) {
		this.monitorCode = monitorCode;
	}

	public boolean isMultiInstitutionIndicator() {
		return multiInstitutionIndicator;
	}

	public void setMultiInstitutionIndicator(boolean multiInstitutionIndicator) {
		this.multiInstitutionIndicator = multiInstitutionIndicator;
	}

	public String getNavyNCIIdentifier() {
		return navyNCIIdentifier;
	}

	public void setNavyNCIIdentifier(String navyNCIIdentifier) {
		this.navyNCIIdentifier = navyNCIIdentifier;
	}

	public String getNciIdentifier() {
		return nciIdentifier;
	}

	public void setNciIdentifier(String nciIdentifier) {
		this.nciIdentifier = nciIdentifier;
	}

	public String getPhaseCode() {
		return phaseCode;
	}

	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}

	public String getPrecisText() {
		return precisText;
	}

	public void setPrecisText(String precisText) {
		this.precisText = precisText;
	}

	public Set getProtocolStatusCollection() {
		return protocolStatusCollection;
	}

	public void setProtocolStatusCollection(Set protocolStatusCollection) {
		this.protocolStatusCollection = protocolStatusCollection;
	}

	public boolean isRandomizedIndicator() {
		return randomizedIndicator;
	}

	public void setRandomizedIndicator(boolean randomizedIndicator) {
		this.randomizedIndicator = randomizedIndicator;
	}

	public String getShortTitleText() {
		return shortTitleText;
	}

	public void setShortTitleText(String shortTitleText) {
		this.shortTitleText = shortTitleText;
	}

	public String getSponsorCode() {
		return sponsorCode;
	}

	public void setSponsorCode(String sponsorCode) {
		this.sponsorCode = sponsorCode;
	}

	public int getTargetAccrualNumber() {
		return targetAccrualNumber;
	}

	public void setTargetAccrualNumber(int targetAccrualNumber) {
		this.targetAccrualNumber = targetAccrualNumber;
	}

	public Set getEligibilityCriteriaCollection() {
		return eligibilityCriteriaCollection;
	}

	public void setEligibilityCriteriaCollection(
			Set eligibilityCriteriaCollection) {
		this.eligibilityCriteriaCollection = eligibilityCriteriaCollection;
	}

	public Set getStudyAgentCollection() {
		return studyAgentCollection;
	}

	public void setStudyAgentCollection(Set studyAgentCollection) {
		this.studyAgentCollection = studyAgentCollection;
	}

	public Set getStudyInvestigatorCollection() {
		return studyInvestigatorCollection;
	}

	public void setStudyInvestigatorCollection(
			Set studyInvestigatorCollection) {
		this.studyInvestigatorCollection = studyInvestigatorCollection;
	}

	public Set getStudySiteCollection() {
		return studySiteCollection;
	}

	public void setStudySiteCollection(Set studySiteCollection) {
		this.studySiteCollection = studySiteCollection;
	}

}