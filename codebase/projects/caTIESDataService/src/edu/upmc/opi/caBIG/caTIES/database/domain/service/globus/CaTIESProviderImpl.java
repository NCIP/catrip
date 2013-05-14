/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.upmc.opi.caBIG.caTIES.database.domain.service.globus;

import edu.upmc.opi.caBIG.caTIES.database.domain.service.CaTIESImpl;

import java.rmi.RemoteException;

/** 
 * DO NOT EDIT:  This class is autogenerated!
 *
 * This class implements each method in the portType of the service.  Each method call represented
 * in the port type will be then mapped into the unwrapped implementation which the user provides
 * in the CaTIESImpl class.  This class handles the boxing and unboxing of each method call
 * so that it can be correclty mapped in the unboxed interface that the developer has designed and 
 * has implemented.  Authorization callbacks are automatically made for each method based
 * on each methods authorization requirements.
 * 
 * @created by Introduce Toolkit version 1.0
 * 
 */
public class CaTIESProviderImpl{
	
	CaTIESImpl impl;
	
	public CaTIESProviderImpl() throws RemoteException {
		impl = new CaTIESImpl();
	}
	

}