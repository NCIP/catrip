/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * Created on Oct 4, 2006
 */
package edu.duke.cabig.catrip.deid;

import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class DeIdServiceImplTest
	extends DeIdServiceTest
{
	private DeIdService service;
	
	public DeIdServiceImplTest(String name) 
		throws ClassNotFoundException
	{
		super(name);
		
		service = new DeIdServiceImpl(
			System.getProperty("dbUrl", "jdbc:mysql://localhost/mysql"),
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
		TestResult result = runner.doRun(new TestSuite(DeIdServiceImplTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}
