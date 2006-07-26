

package edu.duke.catrip.cae.domain.general;
import edu.duke.catrip.cae.domain.general.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Harmonized Specimen class . This is part of CAE backbone object hrmonization . Attributes of SpecimenIdentifiers 
   * class of CAE are harmonized into this Specimen class . 
   * 
   */

public  class Specimen 
    extends edu.pitt.cabig.cae.domain.general.Specimen
	
{

	  
	   private java.lang.String identifier;
	   public  java.lang.String getIdentifier(){
	      return identifier;
	   }
	   public void setIdentifier( java.lang.String identifier){
	      this.identifier = identifier;
	   }
	
	   
	   private java.lang.String surgicalLabel;
	   public  java.lang.String getSurgicalLabel(){
	      return surgicalLabel;
	   }
	   public void setSurgicalLabel( java.lang.String surgicalLabel){
	      this.surgicalLabel = surgicalLabel;
	   }
	

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Specimen) {
				Specimen c =(Specimen)obj; 			 
				Long thisId = getId();		
				
					if(thisId != null && thisId.equals(c.getId())) {
					   eq = true;
				    }		
				
			}
			return eq;
		}
		
		public int hashCode(){
			int h = 0;
			
			if(getId() != null) {
				h += getId().hashCode();
			}
			
			return h;
	}
	
	
}