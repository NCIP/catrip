/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.cagrid.catriptumorregistry.service.globus.resource;

public class ResourceConfiguration {
	private String registrationTemplateFile;
	private boolean performRegistration;
	private String serviceMetadataFile;
	private String domainModelFile;



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
	
	
	
	public String getServiceMetadataFile() {
		return serviceMetadataFile;
	}
	
	
	public void setServiceMetadataFile(String serviceMetadataFile) {
		this.serviceMetadataFile = serviceMetadataFile;
	}
	
	
	
	public String getDomainModelFile() {
		return domainModelFile;
	}
	
	
	public void setDomainModelFile(String domainModelFile) {
		this.domainModelFile = domainModelFile;
	}
		
}
