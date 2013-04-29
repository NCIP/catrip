/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.catrip.cagrid.catripquery.service.globus.resource;

import javax.xml.namespace.QName;


public interface ResourceConstants {
	public static final String SERVICE_NS = "http://catripquery.cagrid.catrip.nci.nih.gov/QueryService";
	public static final QName RESOURCE_KEY = new QName(SERVICE_NS, "QueryServiceKey");
	public static final QName RESOURCE_PROPERY_SET = new QName(SERVICE_NS, "QueryServiceResourceProperties");

	//Service level metadata (exposed as resouce properties)
	public static final QName DOMAINMODEL_MD_RP = new QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.dataservice", "DomainModel");
	
}
