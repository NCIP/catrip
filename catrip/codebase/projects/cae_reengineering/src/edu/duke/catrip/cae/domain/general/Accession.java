

package edu.duke.catrip.cae.domain.general;
import edu.duke.catrip.cae.domain.general.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Harmonized Accession class . This is part of CAE backbone object hrmonization . Attributes of AccessionIdentifiers 
   * class of CAE are harmonized into this Accession class . 
   * 
   */

public  class Accession 
    extends edu.pitt.cabig.cae.domain.general.Accession
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   private java.util.Date accessionDate;
	   public  java.util.Date getAccessionDate(){
	      return accessionDate;
	   }
	   public void setAccessionDate( java.util.Date accessionDate){
	      this.accessionDate = accessionDate;
	   }
	
	   
	   private java.lang.String surgicalPathologyNumber;
	   public  java.lang.String getSurgicalPathologyNumber(){
	      return surgicalPathologyNumber;
	   }
	   public void setSurgicalPathologyNumber( java.lang.String surgicalPathologyNumber){
	      this.surgicalPathologyNumber = surgicalPathologyNumber;
	   }
	

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Accession) {
				Accession c =(Accession)obj; 			 
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