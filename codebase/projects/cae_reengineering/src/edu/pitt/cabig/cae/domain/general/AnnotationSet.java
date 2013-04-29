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
   * The collection of all the data in the annotation
   */

public  class AnnotationSet 
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   private java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private edu.pitt.cabig.cae.domain.general.AnnotationEventParameters annotationEventParameters;
			public edu.pitt.cabig.cae.domain.general.AnnotationEventParameters getAnnotationEventParameters(){
			
			

			  return annotationEventParameters;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setAnnotationEventParameters(edu.pitt.cabig.cae.domain.general.AnnotationEventParameters annotationEventParameters){
		this.annotationEventParameters = annotationEventParameters;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof AnnotationSet) {
				AnnotationSet c =(AnnotationSet)obj; 			 
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