/*
 * XMLFileServiceLocatorTest.java
 * JUnit based test
 *
 * Created on August 10, 2006, 11:52 AM
 */

package edu.duke.cabig.catrip.gui.discovery;

import junit.framework.*;
import edu.duke.cabig.catrip.gui.common.ServiceMetaDataBean;
import java.util.ArrayList;

/**
 *
 * @author Sanjeev Agarwal
 */
public class XMLFileServiceLocatorTest extends TestCase {
    
    public XMLFileServiceLocatorTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(XMLFileServiceLocatorTest.class);
        
        return suite;
    }
    
    /**
     * Test of discoverAllLocalServices method, of class edu.duke.cabig.catrip.gui.discovery.XMLFileServiceLocator.
     */
    public void testDiscoverAllLocalServices() {
        System.out.println("Running the test for discovering service from service Metadata extracts");
        ArrayList<ServiceMetaDataBean> result = null;
        try{
            XMLFileServiceLocator instance = new XMLFileServiceLocator();
            result = instance.discoverAllLocalServices();
//            System.out.println(" xxxxx  "+result.size());
        } catch(Exception ee){
            ee.printStackTrace();
            fail("The discover services test is failed.");
        }
        assertTrue( (result != null) && (result.size() == 2) );

    }
    
    /**
     * Test of discoverServices method, of class edu.duke.cabig.catrip.gui.discovery.XMLFileServiceLocator.
     */
    
    
}
