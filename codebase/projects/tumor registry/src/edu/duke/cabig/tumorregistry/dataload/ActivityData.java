/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.tumorregistry.dataload;

import edu.duke.cabig.tumorregistry.domain.*;

public class ActivityData extends Activity {
	private Long sequenceNumber;
	//private String characterizationCode;
	//private static Lookup lookup = Lookup.getInstance();


	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getCharacterizationCode() {
		return characterizationCode;
	}

	public void setCharacterizationCode(String characterizationCode) {
		this.characterizationCode = characterizationCode;
	}
}
