package gov.nih.nci.ctom.domain;

/**
 * @version 1.0
 * @created 26-Jun-2006 8:36:10 AM
 */
public class Procedure extends Activity {

	private String anatomicSiteCode;
	private String anatomicSiteCodeSystem;

	public Procedure(){

	}

	public String getAnatomicSiteCode() {
		return anatomicSiteCode;
	}

	public void setAnatomicSiteCode(String anatomicSiteCode) {
		this.anatomicSiteCode = anatomicSiteCode;
	}

	public String getAnatomicSiteCodeSystem() {
		return anatomicSiteCodeSystem;
	}

	public void setAnatomicSiteCodeSystem(String anatomicSiteCodeSystem) {
		this.anatomicSiteCodeSystem = anatomicSiteCodeSystem;
	}

}