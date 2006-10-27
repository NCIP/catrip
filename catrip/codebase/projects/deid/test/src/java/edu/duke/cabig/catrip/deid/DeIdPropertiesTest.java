/*
 * Created on Oct 9, 2006
 */
package edu.duke.cabig.catrip.deid;

import java.io.IOException;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class DeIdPropertiesTest
	extends TestCase
{
	public DeIdPropertiesTest(String name)
	{
		super(name);
	}
	
	public void testProperties() throws IOException, ClassNotFoundException
	{
		DeIdServiceImpl service = new DeIdServiceImpl();
		assertEquals("jdbc:mysql://localhost/mysql", service.dbUrl);
		assertEquals("root", service.user);
		assertEquals("", service.password);
	}
	
	public static void main(String[] args) throws Exception
	{
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(DeIdPropertiesTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}

}
