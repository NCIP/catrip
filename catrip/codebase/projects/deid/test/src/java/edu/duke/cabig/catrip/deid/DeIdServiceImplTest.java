/*
 * Created on Oct 4, 2006
 */
package edu.duke.cabig.catrip.deid;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class DeIdServiceImplTest
	extends TestCase
{
	private DeIdService service;
	
	public DeIdServiceImplTest(String name)
	{
		super(name);
		
		service = new DeIdServiceImpl(
			System.getProperty("dbUrl", "jdbc:mysql://localhost/mysql"),
			System.getProperty("user", "root"),
			System.getProperty("password", "")
		);
	}
	
	public void testGenerateRandomValue()
	{
		char[] ch = ((DeIdServiceImpl) service).generateRandomValue().toCharArray();
		assertEquals(DeIdServiceImpl.RANDOM_SIZE, ch.length);
		for (char c : ch) {
			assertTrue(Character.isLetterOrDigit(c));
		}
	}

	public void testDeId() throws Exception
	{
		// generate phi
		String[] phi = new String[500];
		for (int i = 0; i < phi.length; i++) {
			phi[i] = ((DeIdServiceImpl) service).generateRandomValue(25);
		}
		
		//  get random vals
		String[] val = new String[phi.length];
		for (int i = 0; i < phi.length; i++) {
			val[i] = service.deid(phi[i]);
		}
		
		// check for no repeats
		for (int i = 0; i < val.length; i++) {
			for (int j = i+1; j < val.length; j++) {
				assertFalse(val[i].equals(val[j]));;
			}
		}
		
		// check that we are getting the same val back
		for (int i = 0; i < phi.length; i++) {
			String nextVal = service.deid(phi[i]);
			assertEquals(val[i], nextVal);
		}				
	}
	
	public static void main(String[] args) throws Exception
	{
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(DeIdServiceImplTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}
