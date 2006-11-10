package edu.duke.cabig.tumorregistry.dataload;

import edu.duke.cabig.tumorregistry.domain.*;

public class CollaborativeStagingData extends CollaborativeStaging {
	private Long sequenceNumber;

	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

}
