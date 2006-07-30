

package edu.pitt.cabig.cae.domain.general;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * The parameters used in the annotation
   */

public  class AnnotationEventParameters 
    extends EventParameters
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   private String source;
	   public String getSource(){
	      return source;
	   }
	   public void setSource(String source){
	      this.source = source;
	   }
	
	   
	   private java.util.Date sourceDate;
	   public  java.util.Date getSourceDate(){
	      return sourceDate;
	   }
	   public void setSourceDate( java.util.Date sourceDate){
	      this.sourceDate = sourceDate;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private edu.pitt.cabig.cae.domain.general.AnnotatableEntity annotatableEntity;
			public edu.pitt.cabig.cae.domain.general.AnnotatableEntity getAnnotatableEntity(){
			
			

			  return annotatableEntity;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setAnnotatableEntity(edu.pitt.cabig.cae.domain.general.AnnotatableEntity annotatableEntity){
		this.annotatableEntity = annotatableEntity;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection annotationSetCollection = new java.util.HashSet();
			public java.util.Collection getAnnotationSetCollection(){

	              return annotationSetCollection;
	          }
			   
			   
			   
			   
			   
	      
	               
	   
	   	public void setAnnotationSetCollection(java.util.Collection annotationSetCollection){
	   		this.annotationSetCollection = annotationSetCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof AnnotationEventParameters) {
				AnnotationEventParameters c =(AnnotationEventParameters)obj; 			 
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