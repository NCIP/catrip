/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.catrip.cagrid.catripquery.service.globus;

import gov.nih.nci.catrip.cagrid.catripquery.service.QueryServiceImpl;

import java.rmi.RemoteException;

/** 
 * DO NOT EDIT:  This class is autogenerated!
 *
 * This class implements each method in the portType of the service.  Each method call represented
 * in the port type will be then mapped into the unwrapped implementation which the user provides
 * in the QueryServiceImpl class.  This class handles the boxing and unboxing of each method call
 * so that it can be correclty mapped in the unboxed interface that the developer has designed and 
 * has implemented.  Authorization callbacks are automatically made for each method based
 * on each methods authorization requirements.
 * 
 * @created by Introduce Toolkit version 1.0
 * 
 */
public class QueryServiceProviderImpl{
	
	QueryServiceImpl impl;
	
	public QueryServiceProviderImpl() throws RemoteException {
		impl = new QueryServiceImpl();
	}
	

	public gov.nih.nci.catrip.cagrid.catripquery.stubs.SaveResponse save(gov.nih.nci.catrip.cagrid.catripquery.stubs.SaveRequest params) throws RemoteException {
		QueryServiceAuthorization.authorizeSave();
		gov.nih.nci.catrip.cagrid.catripquery.stubs.SaveResponse boxedResult = new gov.nih.nci.catrip.cagrid.catripquery.stubs.SaveResponse();
		impl.save(params.getCatripQuery().getCatripQuery());
		return boxedResult;
	}

	public gov.nih.nci.catrip.cagrid.catripquery.stubs.DeleteResponse delete(gov.nih.nci.catrip.cagrid.catripquery.stubs.DeleteRequest params) throws RemoteException {
		QueryServiceAuthorization.authorizeDelete();
		gov.nih.nci.catrip.cagrid.catripquery.stubs.DeleteResponse boxedResult = new gov.nih.nci.catrip.cagrid.catripquery.stubs.DeleteResponse();
		impl.delete(params.get_long());
		return boxedResult;
	}

}
