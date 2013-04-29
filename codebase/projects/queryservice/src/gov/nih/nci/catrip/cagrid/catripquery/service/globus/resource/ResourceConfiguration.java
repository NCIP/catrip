/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.catrip.cagrid.catripquery.service.globus.resource;

public class ResourceConfiguration {
	private String registrationTemplateFile;
	private boolean performRegistration;



	public boolean shouldPerformRegistration() {
		return performRegistration;
	}


	public void setPerformRegistration(boolean performRegistration) {
		this.performRegistration = performRegistration;
	}


	public String getRegistrationTemplateFile() {
		return registrationTemplateFile;
	}


	public void setRegistrationTemplateFile(String registrationTemplateFile) {
		this.registrationTemplateFile = registrationTemplateFile;
	}
		
}
