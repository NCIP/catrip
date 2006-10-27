/*
 * Created on Oct 4, 2006
 */
package edu.duke.cabig.catrip.deid;

import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class DeIdServiceClientTest
	extends DeIdServiceTest
{
	private DeIdService service;
	
	public DeIdServiceClientTest(String name) 
		throws Exception
	{
		super(name);
		
		service = new DeIdServiceClient(
			System.getProperty("serviceUrl", "http://localhost:8080/axis2/services/DeIdService"),
			System.getProperty("user", "root"),
			System.getProperty("password", "")
		);
	}

	public void testDeId() throws Exception
	{
		super.performDeIdTest(service);
	}
	
	public static void main(String[] args) throws Exception
	{
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(DeIdServiceClientTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}
