package gov.nih.nci.cagrid.catissuecorefull.service.globus.resource;

import javax.xml.namespace.QName;


public interface ResourceConstants {
	public static final String SERVICE_NS = "http://helloworld.cagrid.nci.nih.gov/CaTissueCore_Full";
	public static final QName RESOURCE_KEY = new QName(SERVICE_NS, "CaTissueCore_FullKey");
	public static final QName RESOURCE_PROPERY_SET = new QName(SERVICE_NS, "CaTissueCore_FullResourceProperties");

	//Service level metadata (exposed as resouce properties)
	public static final QName DOMAINMODEL_MD_RP = new QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.dataservice", "DomainModel");
	
}
