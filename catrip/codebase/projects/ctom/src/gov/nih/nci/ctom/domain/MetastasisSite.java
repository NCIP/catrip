package gov.nih.nci.ctom.domain;

/**
 * @version 1.0
 * @created 23-Jun-2006 8:43:19 AM
 */
public class MetastasisSite {

	public String anatomicSiteCode;
	public String anatomicSiteCodeSystem;
	public long id;

	public MetastasisSite(){

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}