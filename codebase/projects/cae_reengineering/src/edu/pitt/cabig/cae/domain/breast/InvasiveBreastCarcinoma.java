/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */


package edu.pitt.cabig.cae.domain.breast;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * A primary infiltrating carcinoma of the breast.
   */

public  class InvasiveBreastCarcinoma 
    extends edu.pitt.cabig.cae.domain.general.Neoplasm
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   private java.util.Collection location;
	   public  java.util.Collection getLocation(){
	      return location;
	   }
	   public void setLocation( java.util.Collection location){
	      this.location = location;
	   }
	
	   
	   private java.lang.String locationMVR;
	   public  java.lang.String getLocationMVR(){
	      return locationMVR;
	   }
	   public void setLocationMVR( java.lang.String locationMVR){
	      this.locationMVR = locationMVR;
	   }
	
	   
	   private java.lang.String venousLymphaticInvasion;
	   public  java.lang.String getVenousLymphaticInvasion(){
	      return venousLymphaticInvasion;
	   }
	   public void setVenousLymphaticInvasion( java.lang.String venousLymphaticInvasion){
	      this.venousLymphaticInvasion = venousLymphaticInvasion;
	   }
	
	   
	   private java.util.Collection microcalcificationLocation;
	   public  java.util.Collection getMicrocalcificationLocation(){
	      return microcalcificationLocation;
	   }
	   public void setMicrocalcificationLocation( java.util.Collection microcalcificationLocation){
	      this.microcalcificationLocation = microcalcificationLocation;
	   }
	

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof InvasiveBreastCarcinoma) {
				InvasiveBreastCarcinoma c =(InvasiveBreastCarcinoma)obj; 			 
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