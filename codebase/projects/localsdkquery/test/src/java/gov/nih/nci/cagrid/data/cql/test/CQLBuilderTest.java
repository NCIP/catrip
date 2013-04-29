/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.cagrid.data.cql.test;

import gov.nih.nci.cagrid.data.cql.tools.CQLBuilder;

import junit.framework.TestCase;

import org.jdom.Element;

/**
 * Generate multiple CQLs to get attributes from other objects
 * @testType unit
 * @author Srini Akkala 
 */
public class CQLBuilderTest extends TestCase {
    public CQLBuilderTest(String sTestName) {
        super(sTestName);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
     /**
     * 
     * Generate multiple CQLs , to get attributes from other objects
     */
    public void testCQLGeneration() throws Exception{
        
       /*
        
        Long t1 = System.currentTimeMillis(); 
        CQLBuilder builder = new CQLBuilder();
        builder.loadDocument("./testCQL/test-multiple-qrys.xml");
        //   Element ell = getNodeForXpath("/CQLQuery/Target");
        Element ell = builder.getDocument().getRootElement().getChild("Target");

        int c = 1;
        
        Element childAssoc = ell.getChild("Association");
        while (childAssoc != null ) {
            childAssoc = childAssoc.getChild("Association");
            System.out.println("Query # " + c);
            System.out.println(builder.buildCQL(c));
            c++;
        }
        Long t2 = System.currentTimeMillis(); 
        System.out.println("Time Taken " + (t2-t1)/1000);     
*/   
    }
    
    
}
