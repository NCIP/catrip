/*
 * Created on Jun 11, 2006
 */
package edu.duke.cabig.catrip.test.system;

import edu.duke.cabig.catrip.test.system.steps.CAECleanupStep;
import edu.duke.cabig.catrip.test.system.steps.CAEConfigureStep;
import edu.duke.cabig.catrip.test.system.steps.CGEMSCleanupStep;
import edu.duke.cabig.catrip.test.system.steps.CGEMSConfigureStep;
import edu.duke.cabig.catrip.test.system.steps.CaTissueCoreCleanupStep;
import edu.duke.cabig.catrip.test.system.steps.CaTissueCoreConfigureStep;
import edu.duke.cabig.catrip.test.system.steps.DQEConfigureStep;
import edu.duke.cabig.catrip.test.system.steps.DQEInvokeStep;
import edu.duke.cabig.catrip.test.system.steps.TumorRegistryCleanupStep;
import edu.duke.cabig.catrip.test.system.steps.TumorRegistryConfigureStep;
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
 * This is an integration test that tests the functionality of the Distribute Query Engine (DQE). 
 * It deploys all four caTRIP domain services (CAE, caTissue CORE, CGEMS, and Tumor Registry), 
 * configures them, and then invokes a number of distributed queries on them.
 * @testType integration
 * @steps GlobusCreateStep, 
 * @steps CaTissueCoreConfigureStep, CAEConfigureStep, CAEConfigureStep, CAEConfigureStep
 * @steps GlobusDeployServiceStep, GlobusStartStep
 * @steps ServiceInvokeStep
 * @steps GlobusStopStep, GlobusCleanupStep, CAECleanupStep
 * @author Patrick McConnell
 */
public class DQETest
	extends Story
{
	private GlobusHelper globus;
	private int port;
	private File caTissueCoreServiceDir;
	private File caeServiceDir;
	private File cgemsServiceDir;
	private File tumorRegistryServiceDir;

	private CAECleanupStep caeCleanupStep;
	private CaTissueCoreCleanupStep caTissueCoreCleanupStep;
	private TumorRegistryCleanupStep tumorRegistryCleanupStep;
	private CGEMSCleanupStep cgemsCleanupStep;

	public DQETest()
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
		if (caeCleanupStep != null) {
			caeCleanupStep.runStep();
		}
		if (caTissueCoreCleanupStep != null) {
			caTissueCoreCleanupStep.runStep();
		}
		if (tumorRegistryCleanupStep != null) {
			tumorRegistryCleanupStep.runStep();
		}
		if (cgemsCleanupStep != null) {
			cgemsCleanupStep.runStep();
		}
	}
	
	@SuppressWarnings("unchecked")
	protected Vector steps()		
	{
		boolean performDeploy = "true".equals(System.getProperty("dqetest.deploy", "true"));
		
		globus = new GlobusHelper();
		port = Integer.parseInt(System.getProperty("test.globus.port", "8080"));
		caTissueCoreServiceDir = new File(System.getProperty("catissuecore.dir",
			".." + File.separator + "CaTissueCoreDataServiceV2"
		));
		caeServiceDir = new File(System.getProperty("cae.dir",
			".." + File.separator + "CAEDataServiceV2"
		));
		cgemsServiceDir = new File(System.getProperty("cgems.dir",
			".." + File.separator + "CGEMSDataServiceV2"
		));
		tumorRegistryServiceDir = new File(System.getProperty("tumorregistry.dir",
			".." + File.separator + "TumorRegistryDataService"
		));
		File queryDir = new File(System.getProperty("dqe.query.dir",
			"test" + File.separator + "resources" + File.separator + "DQE"
		));
		File dqeConfigFile = new File(System.getProperty("dqe.config.file",
			"test" + File.separator + "resources" + File.separator + "DQE" + File.separator + "query_engine_services_config.xml"
		)); 
		
		Vector steps = new Vector();
		
		CaTissueCoreConfigureStep caTissueCoreConfigStep = null;
		CAEConfigureStep caeConfigStep = null;
		CGEMSConfigureStep cgemsConfigStep = null;
		TumorRegistryConfigureStep tumorRegistryConfigStep = null;
		if (performDeploy) {
			steps.add(new GlobusCreateStep(globus));
			
			caTissueCoreConfigStep = new CaTissueCoreConfigureStep(caTissueCoreServiceDir); 
			caeConfigStep = new CAEConfigureStep(caeServiceDir); 
			cgemsConfigStep = new CGEMSConfigureStep(cgemsServiceDir); 
			tumorRegistryConfigStep = new TumorRegistryConfigureStep(tumorRegistryServiceDir); 
			
			steps.add(caTissueCoreConfigStep);
			steps.add(caeConfigStep);
			steps.add(cgemsConfigStep);
			steps.add(tumorRegistryConfigStep);
		}
		
		steps.add(new DQEConfigureStep(dqeConfigFile, port));
	
		if (performDeploy) {
			steps.add(new GlobusDeployServiceStep(globus, caTissueCoreServiceDir));
			steps.add(new GlobusDeployServiceStep(globus, caeServiceDir));
			steps.add(new GlobusDeployServiceStep(globus, cgemsServiceDir));
			steps.add(new GlobusDeployServiceStep(globus, tumorRegistryServiceDir));
			
			steps.add(new GlobusStartStep(globus, port));
		}
		
		try {
			for (File dir : ServiceHelper.getInvokeDirs(queryDir)) {
				steps.add(new DQEInvokeStep(dir));
			}
		} catch (Exception e) {
			throw new RuntimeException("unable to add invoke steps", e);
		}
		
		if (performDeploy) {
			steps.add(new GlobusStopStep(globus, port));
			steps.add(new GlobusCleanupStep(globus));
			steps.add(caeCleanupStep = new CAECleanupStep(caeConfigStep));
			steps.add(caTissueCoreCleanupStep = new CaTissueCoreCleanupStep(caTissueCoreConfigStep));
			steps.add(cgemsCleanupStep = new CGEMSCleanupStep(cgemsConfigStep));
			steps.add(tumorRegistryCleanupStep = new TumorRegistryCleanupStep(tumorRegistryConfigStep));
		}
		return steps;
	}

	public String getDescription()
	{
		return "DQETest";
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
		TestResult result = runner.doRun(new TestSuite(DQETest.class));
		System.exit(result.errorCount() + result.failureCount());
	}

}
