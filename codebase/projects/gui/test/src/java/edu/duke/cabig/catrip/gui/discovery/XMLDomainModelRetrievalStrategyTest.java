/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.discovery;

import edu.duke.cabig.catrip.gui.common.ServiceMetaDataBean;
import junit.framework.*;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import java.util.ArrayList;

/**
 * Unit test that tests whether a domain model can be retrieved.
 * @testType unit
 * @author Sanjeev Agarwal
 */
public class XMLDomainModelRetrievalStrategyTest extends TestCase {
    
    public XMLDomainModelRetrievalStrategyTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(XMLDomainModelRetrievalStrategyTest.class);
        
        return suite;
    }

    /**
     * Test of retrievDomainModel method, of class edu.duke.cabig.catrip.gui.discovery.XMLDomainModelRetrievalStrategy.
     */
    public void testRetrievDomainModel() {
        System.out.println("Retrieve domain model from domain model Metadata extracts");
        ArrayList<ServiceMetaDataBean> alist = null; 
        try{
            XMLFileServiceLocator instance = new XMLFileServiceLocator();
            alist = instance.discoverAllLocalServices();
            
            for (int i = 0; i < alist.size(); i++) { 
                ServiceMetaDataBean sBean = (ServiceMetaDataBean)alist.get(i);
                XMLDomainModelRetrievalStrategy xmlStrategy = new XMLDomainModelRetrievalStrategy((String)sBean.getDomainModelEndPointRef());  
                DomainModel model = xmlStrategy.retrievDomainModel();  
                assertTrue( model.getProjectShortName() != null );
            }

        } catch(Exception ee){
            ee.printStackTrace();
            fail("The retrieve domain model test is failed.");
        }
        
    }
    
}
