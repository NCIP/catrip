/*
 * Created on Jun 11, 2006
 */
package edu.duke.cabig.catrip.test.system.steps;

import gov.nci.nih.cagrid.tests.core.compare.BeanComparator;
import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.catrip.dcql.DCQLQueryDocument;
import gov.nih.nci.catrip.fqe.engine.FederatedQueryEngineImpl;

import java.io.File;

import com.atomicobject.haste.framework.Step;

public class DQEInvokeStep
	extends Step
{
	private File queryDir;
	
	public DQEInvokeStep(File queryDir) 
	{
		super();
		
		this.queryDir = queryDir;
	}
	
	public void runStep() 
		throws Exception
	{
		// parse dcql
		File dcqlFile = new File(queryDir, "0_dcqlQuery.xml");
		DCQLQueryDocument query = DCQLQueryDocument.Factory.parse(dcqlFile);
		
		// run query
		FederatedQueryEngineImpl dqe = new FederatedQueryEngineImpl();
		CQLQueryResults results = dqe.execute(query);
		
		// check results
		File resultsFile = new File(queryDir, "out.xml");
		if (resultsFile.exists()) {
			BeanComparator bc = new BeanComparator(this);
			bc.assertEquals(
				Utils.deserializeDocument(resultsFile.toString(), CQLQueryResults.class),
				results
			);
		}
	}
}
