package gov.nih.nci.cagrid.cgems.service.globus.resource;

import javax.xml.namespace.QName;


public interface ResourceConstants {
	public static final String SERVICE_NS = "http://cgems.cagrid.nci.nih.gov/CGEMS";
	public static final QName RESOURCE_KEY = new QName(SERVICE_NS, "CGEMSKey");
	public static final QName RESOURCE_PROPERY_SET = new QName(SERVICE_NS, "CGEMSResourceProperties");

	//Service level metadata (exposed as resouce properties)
	public static final QName DOMAINMODEL_MD_RP = new QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.dataservice", "DomainModel");
	
}