/*
 * Created on Jun 11, 2006
 */
package edu.duke.cabig.catrip.test.system;

import edu.duke.cabig.catrip.test.system.steps.TumorRegistryConfigureStep;
import gov.nci.nih.cagrid.tests.core.GlobusHelper;
import gov.nci.nih.cagrid.tests.core.steps.GlobusCleanupStep;
import gov.nci.nih.cagrid.tests.core.steps.GlobusCreateStep;
import gov.nci.nih.cagrid.tests.core.steps.GlobusDeployServiceStep;
import gov.nci.nih.cagrid.tests.core.steps.GlobusStartStep;
import gov.nci.nih.cagrid.tests.core.steps.GlobusStopStep;
import gov.nci.nih.cagrid.tests.core.util.ServiceHelper;

import java.io.File;
import java.util.Vector;

import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import com.atomicobject.haste.framework.Story;

/**
 * This is an integration test that tests the functionality of the caDSR grid service. 
 * It deploys the service and then compares a number of domain models against their
 * cached XML extracts.
 * @testType integration
 * @steps ServiceCreateStep, 
 * @steps GlobusCreateStep, GlobusDeployServiceStep, CaDSRServiceConfigStep, GlobusStartStep
 * @steps CaDSRCheckServiceStep
 * @steps GlobusStopStep, GlobusCleanupStep
 * @author Patrick McConnell
 */
public class TumorRegistryTest
	extends Story
{
	private GlobusHelper globus;
	private File serviceDir;
	private int port;
	
	public TumorRegistryTest()
	{
		super();
	}

	protected boolean storySetUp() 
		throws Throwable
	{
		return true;
	}

	protected void storyTearDown() 
		throws Throwable
	{
		if (globus != null) {
			globus.stopGlobus(port);
			globus.cleanupTempGlobus();
		}
	}
	
	@SuppressWarnings("unchecked")
	protected Vector steps()		
	{
		globus = new GlobusHelper();
		port = Integer.parseInt(System.getProperty("test.globus.port", "8080"));
		serviceDir = new File(System.getProperty("tumorregistry.dir",
			".." + File.separator + "TumorRegistryDataService"
		));
		
		Vector steps = new Vector();
		steps.add(new GlobusCreateStep(globus));
		steps.add(new TumorRegistryConfigureStep(serviceDir));
		steps.add(new GlobusDeployServiceStep(globus, serviceDir));
		steps.add(new GlobusStartStep(globus, port));
		try {
			steps.addAll((new ServiceHelper("CaTRIPTumorRegistry", serviceDir)).getInvokeSteps());
		} catch (Exception e) {
			throw new RuntimeException("unable to add invoke steps", e);
		}
		steps.add(new GlobusStopStep(globus, port));
		steps.add(new GlobusCleanupStep(globus));
		return steps;
	}

	public String getDescription()
	{
		return "TumorRegistryTest";
	}
	
	/**
	 * used to make sure that if we are going to use a junit testsuite to test
	 * this that the test suite will not error out looking for a single test......
	 */
	public void testDummy() throws Throwable {
	}

	/**
	 * Convenience method for running all the Steps in this Story.
	 */
	public static void main(String args[]) {
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(TumorRegistryTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}

}
