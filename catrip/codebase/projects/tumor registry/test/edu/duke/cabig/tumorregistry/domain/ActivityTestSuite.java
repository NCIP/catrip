package edu.duke.cabig.tumorregistry.domain;

import junit.framework.TestSuite;

import junit.framework.Test;

public class ActivityTestSuite {
	
    public static Test suite() {

        TestSuite suite = new TestSuite();
	
        suite.addTestSuite(PatientTest.class);
        suite.addTestSuite(PatientToActivityTest.class);
        suite.addTestSuite(ProcedureTest.class);
        suite.addTestSuite(AddressTest.class);
        suite.addTestSuite(DiseaseExtentTest.class);
        suite.addTestSuite(ProcedureTest.class);
        suite.addTestSuite(SubstanceAdministrationTest.class);
        suite.addTestSuite(SurgeryTest.class);
        suite.addTestSuite(CollaborativeStagingTest.class);

         return suite;
    }

    /**
     * Runs the test suite using the textual runner.
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
