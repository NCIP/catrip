package gov.nih.nci.cagrid.fqp.results.client;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.HashMap;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.AxisClient;
import org.apache.axis.client.Stub;
import org.apache.axis.configuration.FileProvider;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.types.URI.MalformedURIException;
import org.apache.axis.utils.ClassUtils;

import org.globus.gsi.GlobusCredential;
import org.globus.wsrf.impl.security.authorization.NoAuthorization;

import gov.nih.nci.cagrid.common.security.ProxyUtil;
import gov.nih.nci.cagrid.fqp.results.stubs.FederatedQueryResultsPortType;
import gov.nih.nci.cagrid.fqp.results.stubs.service.FederatedQueryResultsServiceAddressingLocator;
import gov.nih.nci.cagrid.fqp.results.common.FederatedQueryResultsI;
import gov.nih.nci.cagrid.introduce.security.client.ServiceSecurityClient;
import gov.nih.nci.cagrid.metadata.security.CommunicationMechanism;
import gov.nih.nci.cagrid.metadata.security.Operation;
import gov.nih.nci.cagrid.metadata.security.ProtectionLevelType;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadataOperations;

/**
 * This class is autogenerated, DO NOT EDIT.
 * 
 * On construction the class instance will contact the remote service and
 * retrieve it's security metadata description which it will use to configure
 * the Stub specifically for each method call.
 * 
 * @created by Introduce Toolkit version 1.0
 */
public class FederatedQueryResultsClient extends ServiceSecurityClient implements FederatedQueryResultsI {
	protected FederatedQueryResultsPortType portType;
	private Object portTypeMutex;

	public FederatedQueryResultsClient(String url) throws MalformedURIException, RemoteException {
		this(url, null);
	}

	public FederatedQueryResultsClient(String url, GlobusCredential proxy) throws MalformedURIException,
		RemoteException {
		super(url, proxy);
		initialize();
	}

	public FederatedQueryResultsClient(EndpointReferenceType epr) throws MalformedURIException, RemoteException {
		this(epr, null);
	}

	public FederatedQueryResultsClient(EndpointReferenceType epr, GlobusCredential proxy) throws MalformedURIException,
		RemoteException {
		super(epr, proxy);
		initialize();
	}

	private void initialize() throws RemoteException {
		this.portTypeMutex = new Object();
		this.portType = createPortType();
	}

	private FederatedQueryResultsPortType createPortType() throws RemoteException {

		FederatedQueryResultsServiceAddressingLocator locator = new FederatedQueryResultsServiceAddressingLocator();
		// attempt to load our context sensitive wsdd file
		InputStream resourceAsStream = ClassUtils.getResourceAsStream(getClass(), "client-config.wsdd");
		if (resourceAsStream != null) {
			// we found it, so tell axis to configure an engine to use it
			EngineConfiguration engineConfig = new FileProvider(resourceAsStream);
			// set the engine of the locator
			locator.setEngine(new AxisClient(engineConfig));
		}
		FederatedQueryResultsPortType port = null;
		try {
			port = locator.getFederatedQueryResultsPortTypePort(getEndpointReference());
		} catch (Exception e) {
			throw new RemoteException("Unable to locate portType:" + e.getMessage(), e);
		}

		return port;
	}

	public static void usage() {
		System.out.println(FederatedQueryResultsClient.class.getName() + " -url <service url>");
	}

	public static void main(String[] args) {
		System.out.println("Running the Grid Service Client");
		try {
			if (!(args.length < 2)) {
				if (args[0].equals("-url")) {
					FederatedQueryResultsClient client = new FederatedQueryResultsClient(args[1]);
					// place client calls here if you want to use this main as a
					// test....
				} else {
					usage();
					System.exit(1);
				}
			} else {
				usage();
				System.exit(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	// overloaded to not communicate annonymously if credentials are provided
	protected void configureStubSecurity(Stub stub, String method) throws RemoteException {

		boolean https = false;
		if (epr.getAddress().getScheme().equals("https")) {
			https = true;
		}

		if (method.equals("getServiceSecurityMetadata")) {
			if (https) {
				resetStub(stub);
				stub._setProperty(org.globus.wsrf.security.Constants.GSI_TRANSPORT,
					org.globus.wsrf.security.Constants.SIGNATURE);
				stub._setProperty(org.globus.wsrf.security.Constants.GSI_ANONYMOUS, Boolean.TRUE);
				stub._setProperty(org.globus.wsrf.security.Constants.AUTHORIZATION,
					org.globus.wsrf.impl.security.authorization.NoAuthorization.getInstance());
			}
			return;
		}

		if (this.securityMetadata == null) {
			operations = new HashMap();
			this.authorization = NoAuthorization.getInstance();
			this.securityMetadata = getServiceSecurityMetadata();
			ServiceSecurityMetadataOperations ssmo = securityMetadata.getOperations();
			if (ssmo != null) {
				Operation[] ops = ssmo.getOperation();
				if (ops != null) {
					for (int i = 0; i < ops.length; i++) {
						operations.put(ops[i].getName(), ops[i]);
					}
				}
			}

		}
		resetStub(stub);

		CommunicationMechanism serviceDefault = securityMetadata.getDefaultCommunicationMechanism();

		CommunicationMechanism mechanism = null;
		if (operations.containsKey(method)) {
			Operation o = (Operation) operations.get(method);
			mechanism = o.getCommunicationMechanism();
		} else {
			mechanism = serviceDefault;
		}
		boolean anonymousAllowed = false;
		boolean authorizationAllowed = true;
		boolean delegationAllowed = true;
		boolean credentialsAllowed = true;

		if ((https) && (mechanism.getGSITransport() != null)) {
			ProtectionLevelType level = mechanism.getGSITransport().getProtectionLevel();
			if (level != null) {
				if ((level.equals(ProtectionLevelType.privacy)) || (level.equals(ProtectionLevelType.either))) {
					stub._setProperty(org.globus.wsrf.security.Constants.GSI_TRANSPORT,
						org.globus.wsrf.security.Constants.ENCRYPTION);
				} else {
					stub._setProperty(org.globus.wsrf.security.Constants.GSI_TRANSPORT,
						org.globus.wsrf.security.Constants.SIGNATURE);
				}

			} else {
				stub._setProperty(org.globus.wsrf.security.Constants.GSI_TRANSPORT,
					org.globus.wsrf.security.Constants.SIGNATURE);
			}
			delegationAllowed = false;

		} else if (https) {
			stub._setProperty(org.globus.wsrf.security.Constants.GSI_TRANSPORT,
				org.globus.wsrf.security.Constants.SIGNATURE);
			delegationAllowed = false;
		} else if (mechanism.getGSISecureConversation() != null) {
			ProtectionLevelType level = mechanism.getGSISecureConversation().getProtectionLevel();
			if (level != null) {
				if ((level.equals(ProtectionLevelType.privacy)) || (level.equals(ProtectionLevelType.either))) {
					stub._setProperty(org.globus.wsrf.security.Constants.GSI_SEC_CONV,
						org.globus.wsrf.security.Constants.ENCRYPTION);

				} else {
					stub._setProperty(org.globus.wsrf.security.Constants.GSI_SEC_CONV,
						org.globus.wsrf.security.Constants.SIGNATURE);
				}

			} else {
				stub._setProperty(org.globus.wsrf.security.Constants.GSI_SEC_CONV,
					org.globus.wsrf.security.Constants.ENCRYPTION);
			}

		} else if (mechanism.getGSISecureMessage() != null) {
			ProtectionLevelType level = mechanism.getGSISecureMessage().getProtectionLevel();
			if (level != null) {
				if ((level.equals(ProtectionLevelType.privacy)) || (level.equals(ProtectionLevelType.either))) {
					stub._setProperty(org.globus.wsrf.security.Constants.GSI_SEC_MSG,
						org.globus.wsrf.security.Constants.ENCRYPTION);
				} else {
					stub._setProperty(org.globus.wsrf.security.Constants.GSI_SEC_MSG,
						org.globus.wsrf.security.Constants.SIGNATURE);
				}

			} else {
				stub._setProperty(org.globus.wsrf.security.Constants.GSI_SEC_MSG,
					org.globus.wsrf.security.Constants.ENCRYPTION);
			}
			delegationAllowed = false;
			anonymousAllowed = false;
		} else {
			anonymousAllowed = false;
			authorizationAllowed = false;
			delegationAllowed = false;
			credentialsAllowed = false;
		}

		if ((credentialsAllowed) && (proxy == null)) {
			try {
				GlobusCredential cred = ProxyUtil.getDefaultProxy();
				if (cred.getTimeLeft() > 0) {
					proxy = cred;
				}
			} catch (Exception e) {
				System.out.println("Error loading default proxy: " + e.getMessage());
			}

		}

		if ((credentialsAllowed) && (proxy != null)) {
			try {
				org.ietf.jgss.GSSCredential gss = new org.globus.gsi.gssapi.GlobusGSSCredentialImpl(proxy,
					org.ietf.jgss.GSSCredential.INITIATE_AND_ACCEPT);
				stub._setProperty(org.globus.axis.gsi.GSIConstants.GSI_CREDENTIALS, gss);
			} catch (org.ietf.jgss.GSSException ex) {
				throw new RemoteException(ex.getMessage());
			}
		} else if (anonymousAllowed) {
			stub._setProperty(org.globus.wsrf.security.Constants.GSI_ANONYMOUS, Boolean.TRUE);
		}

		if (authorizationAllowed) {
			if (authorization == null) {
				stub._setProperty(org.globus.wsrf.security.Constants.AUTHORIZATION, NoAuthorization.getInstance());
			} else {
				stub._setProperty(org.globus.wsrf.security.Constants.AUTHORIZATION, getAuthorization());
			}
		}
		if (delegationAllowed) {
			if (getDelegationMode() != null) {
				stub._setProperty(org.globus.axis.gsi.GSIConstants.GSI_MODE, getDelegationMode());
			}
		}
	}
	public org.oasis.wsrf.lifetime.DestroyResponse destroy(org.oasis.wsrf.lifetime.Destroy params) throws RemoteException {
      synchronized(portTypeMutex){
        configureStubSecurity((Stub)portType,"destroy");
        return portType.destroy(params);
      }
    }
	public org.oasis.wsrf.lifetime.SetTerminationTimeResponse setTerminationTime(org.oasis.wsrf.lifetime.SetTerminationTime params) throws RemoteException {
      synchronized(portTypeMutex){
        configureStubSecurity((Stub)portType,"setTerminationTime");
        return portType.setTerminationTime(params);
      }
    }
	public gov.nih.nci.cagrid.dcqlresult.DCQLQueryResultsCollection getResults() throws RemoteException, gov.nih.nci.cagrid.fqp.results.stubs.types.ProcessingNotCompleteFault, gov.nih.nci.cagrid.fqp.stubs.types.FederatedQueryProcessingFault, gov.nih.nci.cagrid.fqp.stubs.types.InternalErrorFault {
      synchronized(portTypeMutex){
        configureStubSecurity((Stub)portType,"getResults");
        gov.nih.nci.cagrid.fqp.results.stubs.GetResultsRequest params = new gov.nih.nci.cagrid.fqp.results.stubs.GetResultsRequest();
        gov.nih.nci.cagrid.fqp.results.stubs.GetResultsResponse boxedResult = portType.getResults(params);
        return boxedResult.getDCQLQueryResultsCollection();
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
	public boolean isProcessingComplete() throws RemoteException {
      synchronized(portTypeMutex){
        configureStubSecurity((Stub)portType,"isProcessingComplete");
        gov.nih.nci.cagrid.fqp.results.stubs.IsProcessingCompleteRequest params = new gov.nih.nci.cagrid.fqp.results.stubs.IsProcessingCompleteRequest();
        gov.nih.nci.cagrid.fqp.results.stubs.IsProcessingCompleteResponse boxedResult = portType.isProcessingComplete(params);
        return boxedResult.isResponse();
      }
    }

}