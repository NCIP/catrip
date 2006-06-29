package gov.nih.nci.ctom.domain;


import junit.framework.Test;
import junit.framework.TestSuite;

public class CTOMTestSuite {
	
    public static Test suite() {

        TestSuite suite = new TestSuite();
	
        suite.addTestSuite(TestActivityDb.class);
        suite.addTestSuite(TestAssessmentDb.class);
        suite.addTestSuite(TestObservationDb.class);
        suite.addTestSuite(TestOrganizationDb.class);
        suite.addTestSuite(TestPersonDb.class);
        suite.addTestSuite(TestProtocolDb.class);

 
        return suite;
    }

    /**
     * Runs the test suite using the textual runner.
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
