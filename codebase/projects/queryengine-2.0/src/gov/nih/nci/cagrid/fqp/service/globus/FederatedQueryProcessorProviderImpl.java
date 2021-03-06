/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.cagrid.fqp.service.globus;

import gov.nih.nci.cagrid.fqp.service.FederatedQueryProcessorImpl;

import java.rmi.RemoteException;

/** 
 *  DO NOT EDIT:  This class is autogenerated!
 * 
 * @created by Introduce Toolkit version 1.0
 * 
 */
public class FederatedQueryProcessorProviderImpl{
	
	FederatedQueryProcessorImpl impl;
	
	public FederatedQueryProcessorProviderImpl() throws RemoteException {
		impl = new FederatedQueryProcessorImpl();
	}
	

	public gov.nih.nci.cagrid.fqp.stubs.ExecuteAndAggregateResultsResponse executeAndAggregateResults(gov.nih.nci.cagrid.fqp.stubs.ExecuteAndAggregateResultsRequest params) throws RemoteException, gov.nih.nci.cagrid.fqp.stubs.types.FederatedQueryProcessingFault {
		FederatedQueryProcessorAuthorization.authorizeExecuteAndAggregateResults();
		gov.nih.nci.cagrid.fqp.stubs.ExecuteAndAggregateResultsResponse boxedResult = new gov.nih.nci.cagrid.fqp.stubs.ExecuteAndAggregateResultsResponse();
		boxedResult.setCQLQueryResultCollection(impl.executeAndAggregateResults(params.getQuery().getDCQLQuery()));
		return boxedResult;
	}

	public gov.nih.nci.cagrid.fqp.stubs.ExecuteResponse execute(gov.nih.nci.cagrid.fqp.stubs.ExecuteRequest params) throws RemoteException, gov.nih.nci.cagrid.fqp.stubs.types.FederatedQueryProcessingFault {
		FederatedQueryProcessorAuthorization.authorizeExecute();
		gov.nih.nci.cagrid.fqp.stubs.ExecuteResponse boxedResult = new gov.nih.nci.cagrid.fqp.stubs.ExecuteResponse();
		boxedResult.setDCQLQueryResultsCollection(impl.execute(params.getQuery().getDCQLQuery()));
		return boxedResult;
	}

	public gov.nih.nci.cagrid.fqp.stubs.ExecuteAsynchronouslyResponse executeAsynchronously(gov.nih.nci.cagrid.fqp.stubs.ExecuteAsynchronouslyRequest params) throws RemoteException {
		FederatedQueryProcessorAuthorization.authorizeExecuteAsynchronously();
		gov.nih.nci.cagrid.fqp.stubs.ExecuteAsynchronouslyResponse boxedResult = new gov.nih.nci.cagrid.fqp.stubs.ExecuteAsynchronouslyResponse();
		boxedResult.setFederatedQueryResultsReference(impl.executeAsynchronously(params.getQuery().getDCQLQuery()));
		return boxedResult;
	}

}
