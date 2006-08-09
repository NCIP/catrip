

package edu.pitt.cabig.cae.domain.breast;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * An edge of the specimen removed suring a surgical procedure which is NOT involved by disease. 
   * 
   */

public  class BreastNegativeSurgicalMargin 
    extends edu.pitt.cabig.cae.domain.general.SurgicalMargin
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   private java.lang.String closestNeoplasmPresent;
	   public  java.lang.String getClosestNeoplasmPresent(){
	      return closestNeoplasmPresent;
	   }
	   public void setClosestNeoplasmPresent( java.lang.String closestNeoplasmPresent){
	      this.closestNeoplasmPresent = closestNeoplasmPresent;
	   }
	
	   
	   private java.lang.Float distanceToClosestNeoplasm;
	   public  java.lang.Float getDistanceToClosestNeoplasm(){
	      return distanceToClosestNeoplasm;
	   }
	   public void setDistanceToClosestNeoplasm( java.lang.Float distanceToClosestNeoplasm){
	      this.distanceToClosestNeoplasm = distanceToClosestNeoplasm;
	   }
	

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof BreastNegativeSurgicalMargin) {
				BreastNegativeSurgicalMargin c =(BreastNegativeSurgicalMargin)obj; 			 
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