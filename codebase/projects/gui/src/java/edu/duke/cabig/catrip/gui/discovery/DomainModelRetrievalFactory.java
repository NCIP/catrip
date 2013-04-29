/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.discovery;

import edu.duke.cabig.catrip.gui.common.ServiceMetaDataBean;
import org.apache.axis.message.addressing.EndpointReferenceType;

/**
 * The RetrievalStrategy Factory class.
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
    
    /** Return the correct Strategy based on the end point ref set in the ServiceMetaDataBean object. */
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
