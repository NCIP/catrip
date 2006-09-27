package gov.nih.nci.cagrid.catissuecorefull.client;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.io.File;
import java.io.FileInputStream;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.AxisClient;
import org.apache.axis.client.Stub;
import org.apache.axis.configuration.FileProvider;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.types.URI.MalformedURIException;
import org.apache.axis.utils.ClassUtils;

import org.globus.gsi.GlobusCredential;

import gov.nih.nci.cagrid.catissuecorefull.stubs.CaTissueCore_FullPortType;
import gov.nih.nci.cagrid.catissuecorefull.stubs.service.CaTissueCore_FullServiceAddressingLocator;
import gov.nih.nci.cagrid.catissuecorefull.common.CaTissueCore_FullI;
import gov.nih.nci.cagrid.introduce.security.client.ServiceSecurityClient;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.Object;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;

/**
 * This class is autogenerated, DO NOT EDIT.
 *
 * On construction the class instance will contact the remote service and retrieve it's security
 * metadata description which it will use to configure the Stub specifically for each method call.
 *
 * @created by Introduce Toolkit version 1.0
 */
public class CaTissueCore_FullClient extends ServiceSecurityClient implements CaTissueCore_FullI {
	protected CaTissueCore_FullPortType portType;
	private Object portTypeMutex;

	public CaTissueCore_FullClient(String url) throws MalformedURIException, RemoteException {
		this(url,null);
	}

	public CaTissueCore_FullClient(String url, GlobusCredential proxy) throws MalformedURIException, RemoteException {
	   	super(url,proxy);
	   	initialize();
	}

	public CaTissueCore_FullClient(EndpointReferenceType epr) throws MalformedURIException, RemoteException {
	   	this(epr,null);
	}

	public CaTissueCore_FullClient(EndpointReferenceType epr, GlobusCredential proxy) throws MalformedURIException, RemoteException {
	   	super(epr,proxy);
		initialize();
	}

	private void initialize() throws RemoteException {
	    this.portTypeMutex = new Object();
		this.portType = createPortType();
	}

	private CaTissueCore_FullPortType createPortType() throws RemoteException {

		CaTissueCore_FullServiceAddressingLocator locator = new CaTissueCore_FullServiceAddressingLocator();
		// attempt to load our context sensitive wsdd file
		InputStream resourceAsStream = ClassUtils.getResourceAsStream(getClass(), "client-config.wsdd");
		if (resourceAsStream != null) {
			// we found it, so tell axis to configure an engine to use it
			EngineConfiguration engineConfig = new FileProvider(resourceAsStream);
			// set the engine of the locator
			locator.setEngine(new AxisClient(engineConfig));
		}
		CaTissueCore_FullPortType port = null;
		try {
			port = locator.getCaTissueCore_FullPortTypePort(getEndpointReference());
		} catch (Exception e) {
			throw new RemoteException("Unable to locate portType:" + e.getMessage(), e);
		}

		return port;
	}

	public static void usage(){
		System.out.println(CaTissueCore_FullClient.class.getName() + " -url <service url>");
	}

    public static void main(String [] args){
        System.out.println("Running the Grid Service Client");
        try{


            CaTissueCore_FullClient client = new CaTissueCore_FullClient("http://localhost:8080/wsrf/services/cagrid/CaTissueCore_Full");


    		CQLQuery cqlQuery = new CQLQuery();

             Object target = new Object();
			 target.setName("edu.wustl.catissuecore.domainobject.impl.ParticipantImpl");

		 cqlQuery.setTarget(target);
		 CQLQueryResults results = client.query(cqlQuery);

		 CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("src/gov/nih/nci/cagrid/catissuecorefull/client/client-config.wsdd")));


		 while (iter.hasNext()) {
			  edu.wustl.catissuecore.domainobject.impl.ParticipantImpl de = (edu.wustl.catissuecore.domainobject.impl.ParticipantImpl) iter.next();
			  System.out.println (de.getId() + " " + de.getFirstName() + "   " + de.getGender());
         }
	} catch (Exception e ) {
		e.printStackTrace();
    }
}

    public gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata getServiceSecurityMetadata() throws RemoteException {
      synchronized(portTypeMutex){
        configureStubSecurity((Stub)portType,"getServiceSecurityMetadata");
        gov.nih.nci.cagrid.introduce.security.GetServiceSecurityMetadataRequest params = new gov.nih.nci.cagrid.introduce.security.GetServiceSecurityMetadataRequest();
        gov.nih.nci.cagrid.introduce.security.GetServiceSecurityMetadataResponse boxedResult = portType.getServiceSecurityMetadata(params);
        return boxedResult.getServiceSecurityMetadata();
      }
    }
    public gov.nih.nci.cagrid.cqlresultset.CQLQueryResults query(gov.nih.nci.cagrid.cqlquery.CQLQuery cqlQuery) throws RemoteException, gov.nih.nci.cagrid.data.faults.QueryProcessingExceptionType, gov.nih.nci.cagrid.data.faults.MalformedQueryExceptionType {
      synchronized(portTypeMutex){
        configureStubSecurity((Stub)portType,"query");
        gov.nih.nci.cagrid.data.QueryRequest params = new gov.nih.nci.cagrid.data.QueryRequest();
        gov.nih.nci.cagrid.data.QueryRequestCqlQuery cqlQueryContainer = new gov.nih.nci.cagrid.data.QueryRequestCqlQuery();
        cqlQueryContainer.setCQLQuery(cqlQuery);
        params.setCqlQuery(cqlQueryContainer);
        gov.nih.nci.cagrid.data.QueryResponse boxedResult = portType.query(params);
        return boxedResult.getCQLQueryResultCollection();
      }
    }

}