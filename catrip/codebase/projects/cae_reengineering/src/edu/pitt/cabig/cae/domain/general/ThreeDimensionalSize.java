

package edu.pitt.cabig.cae.domain.general;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * General measurement of size in 3 dimensions.
   */

public  class ThreeDimensionalSize 
    extends AnnotationSet
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	public ThreeDimensionalSize(){

	}
	   
	   private java.lang.Integer greatestDimension;
	   public  java.lang.Integer getGreatestDimension(){
	      return greatestDimension;
	   }
	   public void setGreatestDimension( java.lang.Integer greatestDimension){
	      this.greatestDimension = greatestDimension;
	   }
	
	   
	   private java.lang.Integer additionalDimensionY;
	   public  java.lang.Integer getAdditionalDimensionY(){
	      return additionalDimensionY;
	   }
	   public void setAdditionalDimensionY( java.lang.Integer additionalDimensionY){
	      this.additionalDimensionY = additionalDimensionY;
	   }
	
	   
	   private java.lang.Integer additionalDimensionZ;
	   public  java.lang.Integer getAdditionalDimensionZ(){
	      return additionalDimensionZ;
	   }
	   public void setAdditionalDimensionZ( java.lang.Integer additionalDimensionZ){
	      this.additionalDimensionZ = additionalDimensionZ;
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
			if(obj instanceof ThreeDimensionalSize) {
				ThreeDimensionalSize c =(ThreeDimensionalSize)obj; 			 
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