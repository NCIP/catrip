/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * Created on Jul 20, 2006
 */
package edu.duke.cabig.catrip.test.system;

import java.io.File;
import java.util.Vector;

import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import com.atomicobject.haste.framework.Story;

import edu.duke.cabig.catrip.test.system.steps.JUnitDocReportCheckStep;
import edu.duke.cabig.catrip.test.system.steps.JUnitDocReportCreateAntFileStep;
import edu.duke.cabig.catrip.test.system.steps.JUnitDocReportRunStep;

/**
 * This is a integration test that tests the user's ability to run the JUnitDocReport ant task.
 * @testType integration
 * @steps CreateJUnitDocReportAntFile, RunJUnitDocReportStep, CheckJUnitDocReportStep 
 * @author MCCON012
 */
public class JUnitDocReportTest
	extends Story
{
	private File antFile;
	private File xmlDir;
	private File destFile;
	
	public JUnitDocReportTest()
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
		if (antFile != null) antFile.delete();
		if (xmlDir != null) xmlDir.delete();
		if (destFile != null) destFile.delete();
	}
	
	@SuppressWarnings("unchecked")
	protected Vector steps()		
	{
		try {
			antFile = File.createTempFile("RunJUnitDocReportStep", ".xml");;
			xmlDir = new File("test" + File.separator + "resources" + File.separator + "JUnitDocReportTest");
			destFile = File.createTempFile("RunJUnitDocReportStep", ".html");
			File coberturaDir = new File(System.getProperty("cobertura.dir", 
				".." + File.separator + ".." + File.separator + "share" + File.separator + "cobertura-1.8"
			));
			
			Vector steps = new Vector();
			steps.add(new JUnitDocReportCreateAntFileStep(antFile, xmlDir, destFile, coberturaDir));
			steps.add(new JUnitDocReportRunStep(antFile));
			steps.add(new JUnitDocReportCheckStep(destFile));
			return steps;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getDescription()
	{
		return "JUnitDocReportTest";
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
		TestResult result = runner.doRun(new TestSuite(JUnitDocReportTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}
