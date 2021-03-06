/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * Created on Jun 11, 2006
 */
package edu.duke.cabig.catrip.test.system;

import edu.duke.cabig.catrip.test.system.steps.CGEMSCleanupStep;
import edu.duke.cabig.catrip.test.system.steps.CGEMSConfigureStep;
import gov.nci.nih.cagrid.tests.core.util.GlobusHelper;
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
 * This is an integration test that tests the functionality of the CGEMS grid service. 
 * It deploys the service and then invokes a number of queries.
 * @testType integration
 * @steps GlobusCreateStep, CGEMSConfigureStep, GlobusDeployServiceStep, GlobusStartStep
 * @steps ServiceInvokeStep
 * @steps GlobusStopStep, GlobusCleanupStep, CGEMSCleanupStep
 * @author Patrick McConnell
 */
public class CGEMSTest
	extends Story
{
	private GlobusHelper globus;
	private File serviceDir;
	private int port;
	private CGEMSCleanupStep cleanupStep;
	
	public CGEMSTest()
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
		if (cleanupStep != null) {
			cleanupStep.runStep();
		}
	}
	
	@SuppressWarnings("unchecked")
	protected Vector steps()		
	{
		globus = new GlobusHelper();
		port = Integer.parseInt(System.getProperty("test.globus.port", "8080"));
		serviceDir = new File(System.getProperty("cgems.dir",
			".." + File.separator + "CGEMSDataServiceV2"
		));
		CGEMSConfigureStep configStep = new CGEMSConfigureStep(serviceDir);
		
		Vector steps = new Vector();
		steps.add(new GlobusCreateStep(globus));
		steps.add(configStep);
		steps.add(new GlobusDeployServiceStep(globus, serviceDir));
		steps.add(new GlobusStartStep(globus, port));
		try {
			steps.addAll((new ServiceHelper("CGEMS", serviceDir)).getInvokeSteps());
		} catch (Exception e) {
			throw new RuntimeException("unable to add invoke steps", e);
		}
		steps.add(new GlobusStopStep(globus, port));
		steps.add(new GlobusCleanupStep(globus));
		steps.add(cleanupStep = new CGEMSCleanupStep(configStep));
		return steps;
	}

	public String getDescription()
	{
		return "CGEMSTest";
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
		TestResult result = runner.doRun(new TestSuite(CGEMSTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}

}
