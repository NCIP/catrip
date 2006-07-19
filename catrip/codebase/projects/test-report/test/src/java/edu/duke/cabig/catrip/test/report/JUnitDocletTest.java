/*
 * Created on Jul 19, 2006
 */
package edu.duke.cabig.catrip.test.report;

import java.io.File;
import java.io.IOException;

import edu.duke.cabig.catrip.test.report.data.TestCase;
import edu.duke.cabig.catrip.test.report.data.TestSuite;
import junit.textui.TestRunner;

/**
 * This is a unit test for testing the JUnitDoclet class, which is used do attach JavaDoc comments
 * to JUnit test results.
 * @author MCCON012
 */
public class JUnitDocletTest
	extends junit.framework.TestCase
{
	public JUnitDocletTest(String name)
	{
		super(name);
	}
	
	/**
	 * This test case tests the doclet functionality of adding class docs and method docs to test results.
	 * @throws IOException
	 */
	public void testDoclet() 
		throws IOException
	{
		TestSuite suite = new TestSuite();
		suite.name = "JUnitDocletTest";
		suite.pkg = "edu.duke.cabig.catrip.test.report";
		TestCase test = new TestCase();
		test.className = "JUnitDocletTest";
		test.name = "testDoclet";
		suite.testCases.add(test);
		
		JUnitDoclet.addDocs(
			new File[] { new File("src" + File.separator + "java") }, 
			new TestSuite[] { suite }
		);
		
		assertEquals(
			"This is a unit test for testing the JUnitDoclet class, which is used do attach JavaDoc comments to JUnit test results",
			suite.docs
		);
		assertEquals(
			"This test case tests the doclet functionality of adding class docs and method docs to test results.",
			test.docs
		);
	}
	
	/**
	 * Run the tests
	 * @param args ignored
	 */
	public static void main(String[] args)
	{
		TestRunner runner = new TestRunner();
		junit.framework.TestResult result = runner.doRun(new junit.framework.TestSuite(JUnitDocletTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}
