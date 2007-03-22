package gov.nih.nci.catrip.cagrid.catripquery.client;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.faults.MalformedQueryExceptionType;
import gov.nih.nci.cagrid.data.faults.QueryProcessingExceptionType;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;
import gov.nih.nci.cagrid.introduce.security.client.ServiceSecurityClient;
import gov.nih.nci.catrip.cagrid.catripquery.common.QueryServiceI;
import gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb;
import gov.nih.nci.catrip.cagrid.catripquery.stubs.QueryServicePortType;
import gov.nih.nci.catrip.cagrid.catripquery.stubs.service.QueryServiceAddressingLocator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Vector;

import javax.xml.namespace.QName;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.AxisClient;
import org.apache.axis.client.Stub;
import org.apache.axis.configuration.FileProvider;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.types.URI.MalformedURIException;
import org.apache.axis.utils.ClassUtils;
import org.globus.gsi.GlobusCredential;
import org.oasis.wsrf.properties.GetResourcePropertyResponse;

/**
 * This class is autogenerated, DO NOT EDIT GENERATED GRID SERVICE METHODS.
 *
 * This client is generated automatically by Introduce to provide a clean unwrapped API to the
 * service.
 *
 * On construction the class instance will contact the remote service and retrieve it's security
 * metadata description which it will use to configure the Stub specifically for each method call.
 *
 * @created by Introduce Toolkit version 1.0
 */
public class QueryServiceClient extends ServiceSecurityClient implements QueryServiceI {
	protected QueryServicePortType portType;
	private Object portTypeMutex;

	public QueryServiceClient(String url) throws MalformedURIException, RemoteException {
		this(url,null);
	}

	public QueryServiceClient(String url, GlobusCredential proxy) throws MalformedURIException, RemoteException {
		super(url,proxy);
		initialize();
	}

	public QueryServiceClient(EndpointReferenceType epr) throws MalformedURIException, RemoteException {
		this(epr,null);
	}

	public QueryServiceClient(EndpointReferenceType epr, GlobusCredential proxy) throws MalformedURIException, RemoteException {
		super(epr,proxy);
		initialize();
	}

	private void initialize() throws RemoteException {
		this.portTypeMutex = new Object();
		this.portType = createPortType();
	}

	private QueryServicePortType createPortType() throws RemoteException {

		QueryServiceAddressingLocator locator = new QueryServiceAddressingLocator();
		// attempt to load our context sensitive wsdd file
		InputStream resourceAsStream = ClassUtils.getResourceAsStream(getClass(), "client-config.wsdd");
		if (resourceAsStream != null) {
			// we found it, so tell axis to configure an engine to use it
			EngineConfiguration engineConfig = new FileProvider(resourceAsStream);
			// set the engine of the locator
			locator.setEngine(new AxisClient(engineConfig));
		}
		QueryServicePortType port = null;
		try {
			port = locator.getQueryServicePortTypePort(getEndpointReference());
		} catch (Exception e) {
			throw new RemoteException("Unable to locate portType:" + e.getMessage(), e);
		}

		return port;
	}

	public GetResourcePropertyResponse getResourceProperty(QName resourcePropertyQName) throws RemoteException {
		return portType.getResourceProperty(resourcePropertyQName);
	}

	public static void usage(){
		System.out.println(QueryServiceClient.class.getName() + " -url <service url>");
	}

	public static void main(String [] args){
	}

	public static Vector search(CQLQuery cqlQuery , String serviceURI, String wsddURI ) throws Exception {
		return (Vector) getResults(cqlQuery, serviceURI, wsddURI );

	}

//	public static Vector search(CQLQuery cqlQuery ) throws Exception {
//		return (Vector) getResults(cqlQuery);
//
//	}

//	private static Collection getResults(CQLQuery cqlQuery) throws Exception{
//		String defaultUrl = "http://localhost:8181/wsrf/services/cagrid/QueryService";
//		return getResults(cqlQuery, defaultUrl);
//	}


	private static Collection getResults(CQLQuery cqlQuery, String serviceURI, String wsddURI ) throws Exception{
		Vector<QueryDb> queryResultCollection = new java.util.Vector<QueryDb>();
		String wsdd = "client-config.wsdd";
		CQLQueryResults results;
		QueryServiceClient client = null;
		try{
			client = new QueryServiceClient(serviceURI);
			results = client.query(cqlQuery);
			CQLQueryResultsIterator iter = null;
			try {
//				CQLQueryResultsIterator iterator = new CQLQueryResultsIterator(results, new FileInputStream(new
//						File(wsddURI + File.separator +"client-config.wsdd")));

				iter = new CQLQueryResultsIterator(results, new FileInputStream(new File(wsddURI)));
			} catch (FileNotFoundException e) {
				System.out.println(wsdd + " : file not found");
				e.printStackTrace();
			}

			while (iter.hasNext()) {
				gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb de = (gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb) iter.next();
				queryResultCollection.add(de);
			}
			System.out.println("results = " + queryResultCollection.size());
		} catch (QueryProcessingExceptionType e) {
			e.printStackTrace();
			throw e;
		} catch (MalformedQueryExceptionType e) {
			e.printStackTrace();
			throw e;
		}
		catch (MalformedURIException e){
			e.printStackTrace();
			throw e;
		}
//		catch (FileNotFoundException e){
//			e.printStackTrace();
//			throw e;
//		}
		catch (RemoteException e) {
			e.printStackTrace();
			throw e;
		}
		return queryResultCollection;

	}


	public void save(gov.nih.nci.catrip.cagrid.catripquery.CatripQuery catripQuery) throws RemoteException {
		synchronized(portTypeMutex){
			configureStubSecurity((Stub)portType,"save");
			gov.nih.nci.catrip.cagrid.catripquery.stubs.SaveRequest params = new gov.nih.nci.catrip.cagrid.catripquery.stubs.SaveRequest();
			gov.nih.nci.catrip.cagrid.catripquery.stubs.SaveRequestCatripQuery catripQueryContainer = new gov.nih.nci.catrip.cagrid.catripquery.stubs.SaveRequestCatripQuery();
			catripQueryContainer.setCatripQuery(catripQuery);
			params.setCatripQuery(catripQueryContainer);
			gov.nih.nci.catrip.cagrid.catripquery.stubs.SaveResponse boxedResult = portType.save(params);
		}
	}
	public void delete(long _long) throws RemoteException {
		synchronized(portTypeMutex){
			configureStubSecurity((Stub)portType,"delete");
			gov.nih.nci.catrip.cagrid.catripquery.stubs.DeleteRequest params = new gov.nih.nci.catrip.cagrid.catripquery.stubs.DeleteRequest();
			params.set_long(_long);
			gov.nih.nci.catrip.cagrid.catripquery.stubs.DeleteResponse boxedResult = portType.delete(params);
		}
	}
	public gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata getServiceSecurityMetadata() throws RemoteException {
		synchronized(portTypeMutex){
			configureStubSecurity((Stub)portType,"getServiceSecurityMetadata");
			gov.nih.nci.cagrid.introduce.security.stubs.GetServiceSecurityMetadataRequest params = new gov.nih.nci.cagrid.introduce.security.stubs.GetServiceSecurityMetadataRequest();
			gov.nih.nci.cagrid.introduce.security.stubs.GetServiceSecurityMetadataResponse boxedResult = portType.getServiceSecurityMetadata(params);
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
