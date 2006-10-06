package gov.nih.nci.cagrid.data.cql.cacore.experimental;

import gov.nih.nci.cabio.domain.Gene;
import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLObjectResult;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.MalformedQueryException;
import gov.nih.nci.cagrid.data.QueryProcessingException;
import gov.nih.nci.cagrid.data.cql.CQLQueryProcessor;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;

import java.util.List;
import java.util.Properties;

import javax.xml.namespace.QName;

import org.apache.axis.message.MessageElement;

/** 
 *  IgnorantQueryProcessor
 *  IQP Takes a cql query, ignores it, and returns some results from a
 *  caBIO / caCORE SDK data source
 * 
 * @author <A HREF="MAILTO:ervin@bmi.osu.edu">David W. Ervin</A>
 * 
 * @created Apr 3, 2006 
 * @version $Id: IgnorantQueryProcessor.java,v 1.1 2006-10-06 14:19:12 srakkala Exp $ 
 */
public class IgnorantQueryProcessor extends CQLQueryProcessor {
	public static final String APPLICATION_SERVICE_URL = "appserviceUrl";
	
	private ApplicationService coreService = null;
	
	public IgnorantQueryProcessor() {
		super();
	}
		
	
	public CQLQueryResults processQuery(CQLQuery query) throws MalformedQueryException, QueryProcessingException {
		String serviceUrl = (String) getConfiguredParameters().get(APPLICATION_SERVICE_URL);
		if (serviceUrl == null || serviceUrl.length() == 0) {
			serviceUrl = "http://kramer.bmi.ohio-state.edu:8080/cacore31/server/HTTPServer";
		}
		System.out.println("Data Service connecting out to core service:");
		System.out.println("\t" + serviceUrl);
		coreService = ApplicationService.getRemoteInstance(serviceUrl);
		
		Gene gene = new Gene();
		gene.setSymbol("brca*"); // searching for all genes whose symbol start with brca
		List resultList = null;
		try {
			resultList = coreService.search(Gene.class, gene);
		} catch (ApplicationException ex) {
			throw new QueryProcessingException("Error in SDK application service: " + ex.getMessage(), ex);
		}
		CQLQueryResults results = new CQLQueryResults();
		results.setTargetClassname(Gene.class.getName());
		CQLObjectResult[] objectResults = new CQLObjectResult[resultList.size()];
		for (int i = 0; i < resultList.size(); i++) {
			Gene returnedGene = (Gene) resultList.get(i);
			System.out.println("Symbol: " + returnedGene.getSymbol() +
				"\tTaxon:" +
				returnedGene.getTaxon().getScientificName() +
				"\tName " + returnedGene.getFullName());
			CQLObjectResult result = new CQLObjectResult();
			QName geneQname = Utils.getRegisteredQName(Gene.class);
			MessageElement anyElement = new MessageElement(geneQname, gene);
			result.set_any(new MessageElement[] {anyElement});
		}
		results.setObjectResult(objectResults);
		return results;
	}
	
	
	public Properties getRequiredParameters() {
		Properties params = new Properties();
		params.setProperty(APPLICATION_SERVICE_URL, "http://kramer.bmi.ohio-state.edu:8080/cacore31/server/HTTPServer");
		return params;
	}
}
