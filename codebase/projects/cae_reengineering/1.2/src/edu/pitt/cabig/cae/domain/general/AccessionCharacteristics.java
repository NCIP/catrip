/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.pitt.cabig.cae.domain.general;

import java.util.Set;


/**
 * @version 1.1
 * @created 15-Sep-2006 3:40:42 PM
 */
public class AccessionCharacteristics extends AnnotationSet {

	private static final long serialVersionUID = 1234567890L;
	private Set surgicalProcedure;
	private String otherSurgicalProcedure;

	public AccessionCharacteristics(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public String getOtherSurgicalProcedure() {
		return otherSurgicalProcedure;
	}

	public void setOtherSurgicalProcedure(String otherSurgicalProcedure) {
		this.otherSurgicalProcedure = otherSurgicalProcedure;
	}

	public Set getSurgicalProcedure() {
		return surgicalProcedure;
	}

	public void setSurgicalProcedure(Set surgicalProcedure) {
		this.surgicalProcedure = surgicalProcedure;
	}

}