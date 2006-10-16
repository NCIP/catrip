package gov.nih.nci.cagrid.catissuecore.service.globus.resource;

import javax.xml.namespace.QName;


public interface ResourceConstants {
	public static final String SERVICE_NS = "http://catissuecore.cagrid.nci.nih.gov/CaTissueCore";
	public static final QName RESOURCE_KEY = new QName(SERVICE_NS, "CaTissueCoreKey");
	public static final QName RESOURCE_PROPERY_SET = new QName(SERVICE_NS, "CaTissueCoreResourceProperties");

	//Service level metadata (exposed as resouce properties)
	public static final QName DOMAINMODEL_MD_RP = new QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.dataservice", "DomainModel");
	public static final QName SERVICEMETADATA_MD_RP = new QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata", "ServiceMetadata");
	
}
