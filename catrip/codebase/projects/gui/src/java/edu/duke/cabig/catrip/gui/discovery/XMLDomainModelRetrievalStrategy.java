/*
 * XMLDomainModelRetrievalStrategy.java
 *
 * Created on July 3, 2006, 1:09 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.discovery;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;

/**
 *
 * @author Sanjeev Agarwal
 */
public class XMLDomainModelRetrievalStrategy extends DomainModelRetrievalStrategy{
    
    private String fileUrl;
    
    /** Creates a new instance of XMLDomainModelRetrievalStrategy */
    public XMLDomainModelRetrievalStrategy() {
    }
    
    public XMLDomainModelRetrievalStrategy(String fileUrl_) {
        fileUrl = fileUrl_;
    }
    
    public DomainModel retrievDomainModel(Object sName) {
        return null;
    }
    
    public DomainModel retrievDomainModel()  {
        // Deserialize the XML by the url "fileUrl" and return the DomainModel.
        DomainModel dModel = null;
        try {
            dModel = (DomainModel)Utils.deserializeDocument(fileUrl, DomainModel.class); 
        }  catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return dModel;
    }
    
    
}