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
   * The edge of the specimen removed during a surgical procedure.
   */

public  class SurgicalMargin 
    extends AnnotationSet
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   private java.lang.String spatialRelationshipToPatient;
	   public  java.lang.String getSpatialRelationshipToPatient(){
	      return spatialRelationshipToPatient;
	   }
	   public void setSpatialRelationshipToPatient( java.lang.String spatialRelationshipToPatient){
	      this.spatialRelationshipToPatient = spatialRelationshipToPatient;
	   }
	
	   
	   private java.lang.String otherSpatialRelationshipToPatient;
	   public  java.lang.String getOtherSpatialRelationshipToPatient(){
	      return otherSpatialRelationshipToPatient;
	   }
	   public void setOtherSpatialRelationshipToPatient( java.lang.String otherSpatialRelationshipToPatient){
	      this.otherSpatialRelationshipToPatient = otherSpatialRelationshipToPatient;
	   }
	
	   
	   private java.lang.String MVR;
	   public  java.lang.String getMVR(){
	      return MVR;
	   }
	   public void setMVR( java.lang.String MVR){
	      this.MVR = MVR;
	   }
	

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof SurgicalMargin) {
				SurgicalMargin c =(SurgicalMargin)obj; 			 
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