/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.pitt.cabig.cae.domain.general;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.duke.catrip.cae.domain.general.CAEDataGenerator;

public class AnnotationSetInsertTest extends TestCase {
  CAEDataGenerator dg = new CAEDataGenerator();
    
  public AnnotationSetInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(AnnotationSetInsertTest.class);
   }

   //test reading data from ANNOTATION_EVENT_PARAMETER table and insert into ANNOTATION_SET table
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;
		
		if (DEBUG) System.out.println("Inside testReadFile()...");
	
		dg.buildAnnotationSet();
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	
}




