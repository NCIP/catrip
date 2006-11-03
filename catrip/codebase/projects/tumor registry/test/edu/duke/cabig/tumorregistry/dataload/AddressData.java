package edu.duke.cabig.tumorregistry.dataload;

import edu.duke.cabig.tumorregistry.domain.*;

public class AddressData extends Address {
	private Long sequenceNumber;
	private String countyCountryCode;

	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getCountyCountryCode() {
		return countyCountryCode;
	}

	public void setCountyCountryCode(String countyCountryCode) {
		this.countyCountryCode = countyCountryCode;
	}
}
