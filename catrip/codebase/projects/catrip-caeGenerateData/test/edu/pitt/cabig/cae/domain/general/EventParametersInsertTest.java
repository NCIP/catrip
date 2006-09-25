/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.pitt.cabig.cae.domain.general;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.duke.catrip.cae.domain.general.CAEDataGenerator;

public class EventParametersInsertTest extends TestCase {
  CAEDataGenerator dg = new CAEDataGenerator();
    
  public EventParametersInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(EventParametersInsertTest.class);
   }

   //test reading data from Specimen table and insert into EVENT_PARAMETERS table
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;
		
		if (DEBUG) System.out.println("Inside testReadFile()...");
	
		dg.buildEventParameters();
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	
}




