/*
 * DomainModelRetrievalFactory.java
 *
 * Created on July 3, 2006, 1:08 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.discovery;

import edu.duke.cabig.catrip.gui.common.ServiceMetaDataBean;
import org.apache.axis.message.addressing.EndpointReferenceType;

/**
 *
 * @author Sanjeev Agarwal
 */
public class DomainModelRetrievalFactory {
    
    /** Creates a new instance of DomainModelRetrievalFactory */
    public DomainModelRetrievalFactory() {
    }
    
    public static DomainModelRetrievalStrategy  getDefaultRetrievalStrategy(){
        
        return new JavaObjectDeSerializationDomainModelRetrievalStrategy();
    }
    
    
    public static DomainModelRetrievalStrategy  getRetrievalStrategy(ServiceMetaDataBean sBean){
        
        Object endPointRef = sBean.getDomainModelEndPointRef();
        
        if (endPointRef instanceof String){ // it is pointer to either a xml or an serialized Object
            String fileRef = (String)endPointRef;
            
            if (fileRef.endsWith(".xml")){
                return new XMLDomainModelRetrievalStrategy((String)fileRef);
            }else if (fileRef.endsWith(".obj")){
                return new JavaObjectDeSerializationDomainModelRetrievalStrategy((String)fileRef);
            }
        } else if(endPointRef instanceof EndpointReferenceType){  
            return new DiscoveryClientDomainModelRetrievalStrategy((EndpointReferenceType)endPointRef); 
        }
        
        return null;
    }
    
    
}
