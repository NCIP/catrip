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
   * Any items that can be annotated by CAE, i.e. Accession, Specimen
   */

public  class AnnotatableEntity 
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
	      
			private java.util.Collection annotationEventParametersCollection = new java.util.HashSet();
			public java.util.Collection getAnnotationEventParametersCollection(){

                        	              return annotationEventParametersCollection;
        	          }
			   
			   
			   
			   
			   
	      
	               
	   
	   	public void setAnnotationEventParametersCollection(java.util.Collection annotationEventParametersCollection){
	   		this.annotationEventParametersCollection = annotationEventParametersCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof AnnotatableEntity) {
				AnnotatableEntity c =(AnnotatableEntity)obj; 			 
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