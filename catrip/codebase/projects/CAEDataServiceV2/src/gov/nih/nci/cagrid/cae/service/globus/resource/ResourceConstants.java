package gov.nih.nci.cagrid.cae.service.globus.resource;

import javax.xml.namespace.QName;


public interface ResourceConstants {
	public static final String SERVICE_NS = "http://cae.cagrid.nci.nih.gov/CAE";
	public static final QName RESOURCE_KEY = new QName(SERVICE_NS, "CAEKey");
	public static final QName RESOURCE_PROPERY_SET = new QName(SERVICE_NS, "CAEResourceProperties");

	//Service level metadata (exposed as resouce properties)
	public static final QName DOMAINMODEL_MD_RP = new QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.dataservice", "DomainModel");
	public static final QName SERVICEMETADATA_MD_RP = new QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata", "ServiceMetadata");
	
}
