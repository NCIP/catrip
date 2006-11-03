package edu.duke.cabig.tumorregistry.dataload;

import edu.duke.cabig.tumorregistry.domain.*;

public class DiseaseExtentData extends DiseaseExtent {
	private Long sequenceNumber;
	private Long accountNumber;
	private String siteCode1 = null;
	private String siteCode2 = null;
	private String siteCode3 = null;

	public DistantSite createDistantSite(int siteCodeNumber){
		DistantSite distantSite = null;
		if (buildDistantSite(siteCodeNumber)){
			distantSite = new DistantSite();
			distantSite.setId((getId()*102) + siteCodeNumber);
			if (siteCodeNumber == 1)
				distantSite.setName(getSiteCode1());
			if (siteCodeNumber == 2)
				distantSite.setName(getSiteCode2());
			if (siteCodeNumber == 3)
				distantSite.setName(getSiteCode3());
		}
		return distantSite;
	}
	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getSiteCode1() {
		return siteCode1;
	}
	public void setSiteCode1(String siteCode1) {
		this.siteCode1 = siteCode1;
	}
	public String getSiteCode2() {
		return siteCode2;
	}
	public void setSiteCode2(String siteCode2) {
		this.siteCode2 = siteCode2;
	}
	public String getSiteCode3() {
		return siteCode3;
	}
	public void setSiteCode3(String siteCode3) {
		this.siteCode3 = siteCode3;
	}
	private boolean buildDistantSite(int siteCodeNumber){
		if (siteCodeNumber == 1)
			return !siteCode1.equalsIgnoreCase("None");
		if (siteCodeNumber == 2)
			return !siteCode2.equalsIgnoreCase("None");
		if (siteCodeNumber == 3)
			return !siteCode3.equalsIgnoreCase("None");
		
		return false;
	}

}
