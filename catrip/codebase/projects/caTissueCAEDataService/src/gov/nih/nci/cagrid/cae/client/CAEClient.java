package gov.nih.nci.cagrid.cae.client;

import edu.duke.catrip.cae.domain.general.Participant;

import gov.nih.nci.cagrid.cae.common.CAEI;
import gov.nih.nci.cagrid.cae.stubs.CAEPortType;
import gov.nih.nci.cagrid.cae.stubs.service.CAEServiceAddressingLocator;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.Object;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;
import gov.nih.nci.cagrid.introduce.security.client.ServiceSecurityClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import java.rmi.RemoteException;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.AxisClient;
import org.apache.axis.client.Stub;
import org.apache.axis.configuration.FileProvider;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.types.URI.MalformedURIException;
import org.apache.axis.utils.ClassUtils;

import org.globus.gsi.GlobusCredential;


/**
 * This class is autogenerated, DO NOT EDIT.
 *
 * This class is not thread safe.  A new instance should be created for any threads using this class.
 * On construction the class instance will contact the remote service and retrieve it's security
 * metadata description which it will use to configure the Stub specifically for each method call.
 *
 * @created by Introduce Toolkit version 1.0
 */
public class CAEClient extends ServiceSecurityClient implements CAEI {
	protected CAEPortType portType;
	private Object portTypeMutex;

	public CAEClient(String url) throws MalformedURIException, RemoteException {
		this(url,null);
	}

	public CAEClient(String url, GlobusCredential proxy) throws MalformedURIException, RemoteException {
	   	super(url,proxy);
	   	initialize();
	}

	public CAEClient(EndpointReferenceType epr) throws MalformedURIException, RemoteException {
	   	this(epr,null);
	}

	public CAEClient(EndpointReferenceType epr, GlobusCredential proxy) throws MalformedURIException, RemoteException {
	   	super(epr,proxy);
		initialize();
	}

	private void initialize() throws RemoteException {
	    this.portTypeMutex = new Object();
		this.portType = createPortType();
	}

	private CAEPortType createPortType() throws RemoteException {

		CAEServiceAddressingLocator locator = new CAEServiceAddressingLocator();
		// attempt to load our context sensitive wsdd file
		InputStream resourceAsStream = ClassUtils.getResourceAsStream(getClass(), "client-config.wsdd");
		if (resourceAsStream != null) {
			// we found it, so tell axis to configure an engine to use it
			EngineConfiguration engineConfig = new FileProvider(resourceAsStream);
			// set the engine of the locator
			locator.setEngine(new AxisClient(engineConfig));
		}
		CAEPortType port = null;
		try {
			port = locator.getCAEPortTypePort(getEndpointReference());
		} catch (Exception e) {
			throw new RemoteException("Unable to locate portType:" + e.getMessage(), e);
		}

		return port;
	}

	public static void usage(){
		System.out.println(CAEClient.class.getName() + " -url <service url>");
	}

	public static void main(String [] args){
	    System.out.println("Running the Grid Service Client");
		try{
            CAEClient client = new CAEClient("http://localhost:8181/wsrf/services/cagrid/CAE");



            CQLQuery cqlQuery = new CQLQuery();

            Object target = new Object();

            target.setName(edu.duke.catrip.cae.domain.general.Participant.class.getName());//WashU_sanju_baba


            cqlQuery.setTarget(target);
            CQLQueryResults results = client.query(cqlQuery);

            CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("src/gov/nih/nci/cagrid/cae/client/client-config.wsdd")));
            //System.out.println("sixe "+results.getObjectResult().length);
            while (iter.hasNext()) {
                edu.duke.catrip.cae.domain.general.Participant de = (edu.duke.catrip.cae.domain.general.Participant) iter.next();
                //System.out.println("Xxxxx  "+de.getClass().getName());
                System.out.println(de.getFirstName() );
            }





		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
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
    public gov.nih.nci.cagrid.cqlresultset.CQLQueryResults query(gov.nih.nci.cagrid.cqlquery.CQLQuery cqlQuery) throws RemoteException, gov.nih.nci.cagrid.data.QueryProcessingException, gov.nih.nci.cagrid.data.MalformedQueryException {
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
