/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * Created on Dec 7, 2006
 */
package edu.duke.cabig.catrip.test.system;

import java.rmi.RemoteException;

import org.apache.axis.message.addressing.EndpointReference;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.types.URI;
import org.apache.axis.types.URI.MalformedURIException;

import edu.duke.cabig.tumorregistry.domain.Patient;
import gov.nih.nci.cagrid.catriptumorregistry.client.CaTRIPTumorRegistryClient;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.Object;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;
import gov.nih.nci.cagrid.discovery.client.DiscoveryClient;
import gov.nih.nci.cagrid.metadata.MetadataUtils;
import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import gov.nih.nci.cagrid.metadata.exceptions.InvalidResourcePropertyException;
import gov.nih.nci.cagrid.metadata.exceptions.QueryInvalidException;
import gov.nih.nci.cagrid.metadata.exceptions.RemoteResourcePropertyRetrievalException;
import gov.nih.nci.cagrid.metadata.exceptions.ResourcePropertyRetrievalException;

public class CaGridCaseStudy
{
	public static void discoveryExample() throws MalformedURIException, RemoteResourcePropertyRetrievalException, QueryInvalidException, ResourcePropertyRetrievalException
	{
		DiscoveryClient dclient = new DiscoveryClient(
			"http://cagrid2.duhs.duke.edu:80/wsrf/services/DefaultIndexService"
		);
		
		EndpointReferenceType[] eprs = dclient.getAllServices(false);
		for (EndpointReferenceType epr : eprs) {
			System.out.println(epr);
		}

		eprs = dclient.discoverDataServicesByDomainModel("Tumor Registry");
		EndpointReferenceType trEpr = eprs[0];
		System.out.println(trEpr);
	}

	public static void metadataExample() throws InvalidResourcePropertyException, RemoteResourcePropertyRetrievalException, ResourcePropertyRetrievalException, MalformedURIException
	{
		String trUrl = "http://cagrid2.duhs.duke.edu:80/wsrf/services/cagrid/CaTRIPTumorRegistry";
		EndpointReferenceType trEpr = new EndpointReferenceType(new URI(trUrl));
		
		ServiceMetadata metadata = MetadataUtils.getServiceMetadata(trEpr);
		System.out.println(metadata.getHostingResearchCenter().getResearchCenter().getDisplayName());
		
		DomainModel model = MetadataUtils.getDomainModel(trEpr);
		System.out.println(model.getProjectShortName());
	}
	
	public static void invokeExample() throws MalformedURIException, RemoteException
	{
		String trUrl = "http://cagrid2.duhs.duke.edu:80/wsrf/services/cagrid/CaTRIPTumorRegistry";

		CaTRIPTumorRegistryClient client = new CaTRIPTumorRegistryClient(trUrl);
		
		CQLQuery query = new CQLQuery();
		Object target = new Object();
		query.setTarget(target);
		target.setName("edu.duke.cabig.tumorregistry.domain.Patient");
		
		CQLQueryResults results = client.query(query);
		CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results);
		while (iter.hasNext()) {
			Patient patient = (Patient) iter.next();
			System.out.println(patient.getRace());
		}
	}
	
	public static void main(String[] args)
		throws Exception
	{
		discoveryExample();
		metadataExample();
		invokeExample();
	}
}
