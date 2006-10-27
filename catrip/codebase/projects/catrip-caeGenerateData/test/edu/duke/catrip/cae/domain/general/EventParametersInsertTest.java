/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.cae.domain.general;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class EventParametersInsertTest extends TestCase {
  CAEDataGenerator dg = new CAEDataGenerator();
    
  public EventParametersInsertTest(String sTestName) {
      super(sTestName);
  }

   //test reading data from Specimen table and insert into EVENT_PARAMETERS table
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;
		
		if (DEBUG) System.out.println("Inside testReadFile()...");
	
		dg.buildEventParameters();
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println("\tInside main...");
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(EventParametersInsertTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}




