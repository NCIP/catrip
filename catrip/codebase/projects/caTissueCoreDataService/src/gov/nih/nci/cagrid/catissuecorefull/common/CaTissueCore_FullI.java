package gov.nih.nci.cagrid.catissuecorefull.common;

import java.rmi.RemoteException;

/** 
 *   This class is autogenerated, DO NOT EDIT.
 * 
 * @created by Introduce Toolkit version 1.0
 * 
 */
public interface CaTissueCore_FullI {

public gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata getServiceSecurityMetadata() throws RemoteException ;
public gov.nih.nci.cagrid.cqlresultset.CQLQueryResults query(gov.nih.nci.cagrid.cqlquery.CQLQuery cqlQuery) throws RemoteException, gov.nih.nci.cagrid.data.QueryProcessingException, gov.nih.nci.cagrid.data.MalformedQueryException ;

}

