

package edu.pitt.cabig.cae.domain.general;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * The microscopic classification of degree of abnormality of a neoplasm.
   */

public  class HistopathologicGrade 
    extends AnnotationSet
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof HistopathologicGrade) {
				HistopathologicGrade c =(HistopathologicGrade)obj; 			 
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