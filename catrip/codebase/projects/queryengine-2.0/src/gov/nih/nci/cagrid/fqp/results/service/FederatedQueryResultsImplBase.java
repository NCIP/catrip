package gov.nih.nci.cagrid.fqp.results.service;

import gov.nih.nci.cagrid.fqp.service.ServiceConfiguration;

import java.rmi.RemoteException;

import javax.naming.InitialContext;
import javax.xml.namespace.QName;

import org.apache.axis.MessageContext;
import org.globus.wsrf.Constants;
import org.globus.wsrf.ResourceContext;
import org.globus.wsrf.ResourceContextException;
import org.globus.wsrf.ResourceException;
import org.globus.wsrf.ResourceHome;
import org.globus.wsrf.ResourceProperty;
import org.globus.wsrf.ResourcePropertySet;
/** 
 * Provides some simple accessors for the Impl.
 * 
 * @created by Introduce Toolkit version 1.0
 * 
 */
public abstract class FederatedQueryResultsImplBase {
	
	public FederatedQueryResultsImplBase() throws RemoteException {
	
	}
	
	public ServiceConfiguration getConfiguration() throws Exception {
		return ServiceConfiguration.getConfiguration();
	}
	
	
	
	
	public gov.nih.nci.cagrid.fqp.service.globus.resource.BaseResourceHome getFederatedQueryProcessorResourceHome() throws Exception {
		ResourceHome resource = getResourceHome("federatedQueryProcessorHome");
		return (gov.nih.nci.cagrid.fqp.service.globus.resource.BaseResourceHome)resource;
	}
	
	
	protected ResourceHome getResourceHome(String resourceKey) throws Exception {
		MessageContext ctx = MessageContext.getCurrentContext();

		ResourceHome resourceHome = null;
		
		String servicePath = ctx.getTargetService();

		String jndiName = Constants.JNDI_SERVICES_BASE_NAME + servicePath + "/" + resourceKey;
		try {
			javax.naming.Context initialContext = new InitialContext();
			resourceHome = (ResourceHome) initialContext.lookup(jndiName);
		} catch (Exception e) {
			throw new Exception("Unable to instantiate resource home. : " + resourceKey, e);
		}

		return resourceHome;
	}
	
		
	
	


}

