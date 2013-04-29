/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.cae.domain.general;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AnnotationEventParametersInsertTest extends TestCase {
  CAEDataGenerator dg = new CAEDataGenerator();
    
  public AnnotationEventParametersInsertTest(String sTestName) {
      super(sTestName);
  }

   //test reading data from ANNOTATABLE_ENTITY table and insert into ANNOTATION_EVENT_PARAMETER table
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;
		
		if (DEBUG) System.out.println("Inside testReadFile()...");
	
		dg.buildAnnotationEventParameters();
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println("\tInside main...");
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(AnnotationEventParametersInsertTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}




