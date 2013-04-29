/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */


package edu.pitt.cabig.cae.domain.general;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * TNM finding of the American Joint Committee on Cancer (AJCC) and International Union Against Cancer 
   * (UICC) recommended classification system for cancers. 
   * 
   */

public  class CancerTNMFinding 
    extends AnnotationSet
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   private java.lang.String category;
	   public  java.lang.String getCategory(){
	      return category;
	   }
	   public void setCategory( java.lang.String category){
	      this.category = category;
	   }
	
	   
	   private java.lang.String primaryTumorFinding;
	   public  java.lang.String getPrimaryTumorFinding(){
	      return primaryTumorFinding;
	   }
	   public void setPrimaryTumorFinding( java.lang.String primaryTumorFinding){
	      this.primaryTumorFinding = primaryTumorFinding;
	   }
	
	   
	   private java.lang.String regionalLymphNodesFinding;
	   public  java.lang.String getRegionalLymphNodesFinding(){
	      return regionalLymphNodesFinding;
	   }
	   public void setRegionalLymphNodesFinding( java.lang.String regionalLymphNodesFinding){
	      this.regionalLymphNodesFinding = regionalLymphNodesFinding;
	   }
	
	   
	   private java.lang.String distantMetastasisFinding;
	   public  java.lang.String getDistantMetastasisFinding(){
	      return distantMetastasisFinding;
	   }
	   public void setDistantMetastasisFinding( java.lang.String distantMetastasisFinding){
	      this.distantMetastasisFinding = distantMetastasisFinding;
	   }
	

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof CancerTNMFinding) {
				CancerTNMFinding c =(CancerTNMFinding)obj; 			 
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