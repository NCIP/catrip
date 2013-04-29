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
   * A set of surgical pathologic specimens resulting from a surgical procedure for breast cancer. 
   * 
   */

public  class BreastSurgicalPathologySpecimen 
    extends edu.pitt.cabig.cae.domain.general.SurgicalPathologySpecimen
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   private java.lang.String otherSurgicalProcedure;
	   public  java.lang.String getOtherSurgicalProcedure(){
	      return otherSurgicalProcedure;
	   }
	   public void setOtherSurgicalProcedure( java.lang.String otherSurgicalProcedure){
	      this.otherSurgicalProcedure = otherSurgicalProcedure;
	   }
	
	   
	   private java.lang.String lymphNodeSamplingProcedure;
	   public  java.lang.String getLymphNodeSamplingProcedure(){
	      return lymphNodeSamplingProcedure;
	   }
	   public void setLymphNodeSamplingProcedure( java.lang.String lymphNodeSamplingProcedure){
	      this.lymphNodeSamplingProcedure = lymphNodeSamplingProcedure;
	   }
	
	   
	   private java.lang.String laterality;
	   public  java.lang.String getLaterality(){
	      return laterality;
	   }
	   public void setLaterality( java.lang.String laterality){
	      this.laterality = laterality;
	   }
	
	   
	   private java.lang.String lateralityMVR;
	   public  java.lang.String getLateralityMVR(){
	      return lateralityMVR;
	   }
	   public void setLateralityMVR( java.lang.String lateralityMVR){
	      this.lateralityMVR = lateralityMVR;
	   }
	

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof BreastSurgicalPathologySpecimen) {
				BreastSurgicalPathologySpecimen c =(BreastSurgicalPathologySpecimen)obj; 			 
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