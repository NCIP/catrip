package gov.nih.nci.cagrid.authentication.service.globus;

import gov.nih.nci.cagrid.authentication.service.AuthenticationServiceImpl;

import java.rmi.RemoteException;

/** 
 *  DO NOT EDIT:  This class is autogenerated!
 * 
 * @created by Introduce Toolkit version 1.0
 * 
 */
public class AuthenticationServiceProviderImpl{
	
	AuthenticationServiceImpl impl;
	
	public AuthenticationServiceProviderImpl() throws RemoteException {
		impl = new AuthenticationServiceImpl();
	}
	

	public gov.nih.nci.cagrid.authentication.stubs.AuthenticateResponse authenticate(gov.nih.nci.cagrid.authentication.stubs.AuthenticateRequest params) throws RemoteException, gov.nih.nci.cagrid.authentication.stubs.types.InvalidCredentialFault, gov.nih.nci.cagrid.authentication.stubs.types.InsufficientAttributeFault, gov.nih.nci.cagrid.authentication.stubs.types.AuthenticationProviderFault {
		AuthenticationServiceAuthorization.authorizeAuthenticate();
		gov.nih.nci.cagrid.authentication.stubs.AuthenticateResponse boxedResult = new gov.nih.nci.cagrid.authentication.stubs.AuthenticateResponse();
		boxedResult.setSAMLAssertion(impl.authenticate(params.getCredential().getCredential()));
		return boxedResult;
	}

}