/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.discovery;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;

/**
 * Retrieve the Domain Model from a serialized Domain Model extract XML file.
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
        DomainModel domainModel = null;
        try {
            domainModel = (DomainModel)Utils.deserializeDocument(fileUrl, DomainModel.class); 
        }  catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return domainModel;
    }
    
    
}
