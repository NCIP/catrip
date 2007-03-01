package edu.upmc.opi.caBIG.caTIES.database.domain.service.globus.resource;

import javax.xml.namespace.QName;


public interface ResourceConstants {
	public static final String SERVICE_NS = "http://domain.database.caTIES.caBIG.opi.upmc.edu/CaTIES";
	public static final QName RESOURCE_KEY = new QName(SERVICE_NS, "CaTIESKey");
	public static final QName RESOURCE_PROPERY_SET = new QName(SERVICE_NS, "CaTIESResourceProperties");

	//Service level metadata (exposed as resouce properties)
	public static final QName DOMAINMODEL_MD_RP = new QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.dataservice", "DomainModel");
	
}
