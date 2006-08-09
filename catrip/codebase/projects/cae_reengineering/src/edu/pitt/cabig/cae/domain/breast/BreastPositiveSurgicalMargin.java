

package edu.pitt.cabig.cae.domain.breast;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * An edge of the specimen removed suring a surgical procedure which is involved by disease. 
   * 
   */

public  class BreastPositiveSurgicalMargin 
    extends edu.pitt.cabig.cae.domain.general.SurgicalMargin
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   private java.lang.String neoplasmPresent;
	   public  java.lang.String getNeoplasmPresent(){
	      return neoplasmPresent;
	   }
	   public void setNeoplasmPresent( java.lang.String neoplasmPresent){
	      this.neoplasmPresent = neoplasmPresent;
	   }
	
	   
	   private java.lang.String extentInvolvement;
	   public  java.lang.String getExtentInvolvement(){
	      return extentInvolvement;
	   }
	   public void setExtentInvolvement( java.lang.String extentInvolvement){
	      this.extentInvolvement = extentInvolvement;
	   }
	
	   
	   private java.lang.String otherExtentInvolvement;
	   public  java.lang.String getOtherExtentInvolvement(){
	      return otherExtentInvolvement;
	   }
	   public void setOtherExtentInvolvement( java.lang.String otherExtentInvolvement){
	      this.otherExtentInvolvement = otherExtentInvolvement;
	   }
	

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof BreastPositiveSurgicalMargin) {
				BreastPositiveSurgicalMargin c =(BreastPositiveSurgicalMargin)obj; 			 
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