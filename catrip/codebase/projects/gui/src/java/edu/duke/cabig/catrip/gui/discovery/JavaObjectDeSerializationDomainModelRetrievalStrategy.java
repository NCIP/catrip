
package edu.duke.cabig.catrip.gui.discovery;

import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Retrieve the Domain Model from a serialized 'DomainModel' java object.
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
