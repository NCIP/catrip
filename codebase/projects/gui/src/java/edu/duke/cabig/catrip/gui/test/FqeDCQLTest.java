/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * FqeDCQLTest.java
 *
 * Created on October 4, 2006, 12:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.test;

import edu.duke.cabig.catrip.gui.config.GUIConfigurationLoader;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;
import gov.nih.nci.catrip.dcql.DCQLQueryDocument;
import gov.nih.nci.catrip.fqe.engine.FederatedQueryEngine;
import gov.nih.nci.catrip.fqe.engine.FederatedQueryEngineImpl;
import java.io.File;
import java.io.FileInputStream;

/**
 *
 * @author Sanjeev Agarwal
 */
public class FqeDCQLTest {
    
    /** Creates a new instance of FqeDCQLTest */
    public FqeDCQLTest() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
       
        
        
        String dcql = "";
        
        FederatedQueryEngine fqe = new FederatedQueryEngineImpl(); 
        DCQLQueryDocument dcqlQueryDocument = DCQLQueryDocument.Factory.parse(dcql);
     
        CQLQueryResults results = fqe.execute(dcqlQueryDocument); 
         
        CQLQueryResultsIterator iterator = new CQLQueryResultsIterator(results, new FileInputStream(new File(GUIConfigurationLoader.getGUIConfiguration().getConfigRootLocation() + File.separator +"client-config.wsdd")));
        
        while (iterator.hasNext()) {
                    
                    
                }
        
        
        
        
        
        
        
        
        
        
        
        
        
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
        
        
        
        
    }
    
}
