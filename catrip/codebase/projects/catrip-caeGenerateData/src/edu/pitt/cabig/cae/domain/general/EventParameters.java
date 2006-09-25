

package edu.pitt.cabig.cae.domain.general;

import java.util.Collection;
import java.util.Date;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * The parameters for an event
   */

public  class EventParameters 
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
	
	   
	   private Date timeStamp;
	   public Date getTimeStamp(){
	      return timeStamp;
	   }
	   public void setTimeStamp(Date timeStamp){
	      this.timeStamp = timeStamp;
	   }
	public Collection annotationSetCollection;
	public Collection getAnnotationSetCollection() {
		return annotationSetCollection;
	}

	public void setAnnotationSetCollection(Collection annotationSetCollection) {
		this.annotationSetCollection = annotationSetCollection;
	}

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof EventParameters) {
				EventParameters c =(EventParameters)obj; 			 
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