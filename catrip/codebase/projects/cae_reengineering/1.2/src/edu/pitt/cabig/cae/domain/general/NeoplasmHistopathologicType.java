

package edu.pitt.cabig.cae.domain.general;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * The diagnostic subclassification of neoplasm.
   */

public  class NeoplasmHistopathologicType 
    extends AnnotationSet
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   private java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   public void setName( java.lang.String name){
	      this.name = name;
	   }
	
	   
	   private java.lang.String nameMVR;
	   public  java.lang.String getNameMVR(){
	      return nameMVR;
	   }
	   public void setNameMVR( java.lang.String nameMVR){
	      this.nameMVR = nameMVR;
	   }
	
	   
	   private java.lang.String otherName;
	   public  java.lang.String getOtherName(){
	      return otherName;
	   }
	   public void setOtherName( java.lang.String otherName){
	      this.otherName = otherName;
	   }
	

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof NeoplasmHistopathologicType) {
				NeoplasmHistopathologicType c =(NeoplasmHistopathologicType)obj; 			 
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