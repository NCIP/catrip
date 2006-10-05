package gov.nih.nci.cagrid.catriptumorregistry.service.globus;

import gov.nih.nci.cagrid.catriptumorregistry.service.CaTRIPTumorRegistryImpl;

import java.rmi.RemoteException;

/** 
 *  DO NOT EDIT:  This class is autogenerated!
 * 
 * @created by Introduce Toolkit version 1.0
 * 
 */
public class CaTRIPTumorRegistryProviderImpl{
	
	CaTRIPTumorRegistryImpl impl;
	
	public CaTRIPTumorRegistryProviderImpl() throws RemoteException {
		impl = new CaTRIPTumorRegistryImpl();
	}
	
	protected String getCallerIdentity() {
		String caller = org.globus.wsrf.security.SecurityManager.getManager().getCaller();
		if ((caller == null) || (caller.equals("<anonymous>"))) {
			return null;
		} else {
			return caller;
		}
	}
	

}
