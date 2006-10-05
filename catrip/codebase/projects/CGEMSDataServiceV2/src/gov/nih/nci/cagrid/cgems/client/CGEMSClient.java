package gov.nih.nci.cagrid.cgems.client;

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

import gov.nih.nci.cagrid.cgems.stubs.CGEMSPortType;
import gov.nih.nci.cagrid.cgems.stubs.service.CGEMSServiceAddressingLocator;
import gov.nih.nci.cagrid.cgems.common.CGEMSI;
import gov.nih.nci.cagrid.introduce.security.client.ServiceSecurityClient;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.Object;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;
import gov.nih.nci.cagrid.cqlquery.Association;
import gov.nih.nci.cagrid.cqlquery.Attribute;
import gov.nih.nci.cagrid.cqlquery.Predicate;
/**
 * This class is autogenerated, DO NOT EDIT.
 *
 * On construction the class instance will contact the remote service and retrieve it's security
 * metadata description which it will use to configure the Stub specifically for each method call.
 *
 * @created by Introduce Toolkit version 1.0
 */
public class CGEMSClient extends ServiceSecurityClient implements CGEMSI {
	protected CGEMSPortType portType;
	private Object portTypeMutex;

	public CGEMSClient(String url) throws MalformedURIException, RemoteException {
		this(url,null);
	}

	public CGEMSClient(String url, GlobusCredential proxy) throws MalformedURIException, RemoteException {
	   	super(url,proxy);
	   	initialize();
	}

	public CGEMSClient(EndpointReferenceType epr) throws MalformedURIException, RemoteException {
	   	this(epr,null);
	}

	public CGEMSClient(EndpointReferenceType epr, GlobusCredential proxy) throws MalformedURIException, RemoteException {
	   	super(epr,proxy);
		initialize();
	}

	private void initialize() throws RemoteException {
	    this.portTypeMutex = new Object();
		this.portType = createPortType();
	}

	private CGEMSPortType createPortType() throws RemoteException {

		CGEMSServiceAddressingLocator locator = new CGEMSServiceAddressingLocator();
		// attempt to load our context sensitive wsdd file
		InputStream resourceAsStream = ClassUtils.getResourceAsStream(getClass(), "client-config.wsdd");
		if (resourceAsStream != null) {
			// we found it, so tell axis to configure an engine to use it
			EngineConfiguration engineConfig = new FileProvider(resourceAsStream);
			// set the engine of the locator
			locator.setEngine(new AxisClient(engineConfig));
		}
		CGEMSPortType port = null;
		try {
			port = locator.getCGEMSPortTypePort(getEndpointReference());
		} catch (Exception e) {
			throw new RemoteException("Unable to locate portType:" + e.getMessage(), e);
		}

		return port;
	}

	public static void usage(){
		System.out.println(CGEMSClient.class.getName() + " -url <service url>");
	}

	public static void main(String [] args){

		System.out.println("Running the Grid Service Client");
		try{
            CGEMSClient client = new CGEMSClient("http://localhost:8181/wsrf/services/cagrid/CGEMS");



            CQLQuery cqlQuery = new CQLQuery();

            Object target = new Object();

            target.setName("gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant");

			Association a = new Association();

			a.setName("gov.nih.nci.caintegrator.domain.analysis.snp.bean.SNPAnalysisGroup");
        	a.setRoleName("analysisGroupCollection");

        	a.setAttribute(new Attribute("name",Predicate.LIKE,"BRCA%"));

        	target.setAssociation(a);

            cqlQuery.setTarget(target);
            CQLQueryResults results = client.query(cqlQuery);

            CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("src/gov/nih/nci/cagrid/cgems/client/client-config.wsdd")));

            while (iter.hasNext()) {
                gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant de = (gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant) iter.next();

               System.out.println(de.getStudySubjectIdentifier() + "     " + de.getInstitutionName());
            }
        System.out.println("done ");

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
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
