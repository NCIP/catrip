/*
 * JavaObjectDeSerializationDomainModelRetrievalStrategy.java
 *
 * Created on July 3, 2006, 1:11 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.discovery;

import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 *
 * @author Sanjeev Agarwal
 */
public class JavaObjectDeSerializationDomainModelRetrievalStrategy extends DomainModelRetrievalStrategy{
    
    private String fileUrl;
    
    /** Creates a new instance of JavaObjectDeSerializationDomainModelRetrievalStrategy */
    public JavaObjectDeSerializationDomainModelRetrievalStrategy() {
    }
    
    public JavaObjectDeSerializationDomainModelRetrievalStrategy(String fileUrl_) {
        fileUrl = fileUrl_;
    }
    
    public DomainModel retrievDomainModel(Object sName)  {
        DomainModel dModel = null;
        if (sName instanceof String){
            String fileUrl = (String) sName;
            ObjectInputStream oist;
            try {
                oist = new ObjectInputStream(new FileInputStream(fileUrl));
                dModel = (DomainModel) oist.readObject();
            }  catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return dModel;
    }
    
    
    public DomainModel retrievDomainModel()  {
        DomainModel dModel = null;
        ObjectInputStream oist;
        try {
            oist = new ObjectInputStream(new FileInputStream(fileUrl));
            dModel = (DomainModel) oist.readObject();
        }  catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return dModel;
    }
    
    
    
    
}
