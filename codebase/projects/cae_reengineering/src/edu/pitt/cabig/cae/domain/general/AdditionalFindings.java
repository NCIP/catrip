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
   * Significant pathologic findings that are present in addition to the tumor.
   */

public  class AdditionalFindings 
    extends AnnotationSet
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   private java.lang.String otherFindings;
	   public  java.lang.String getOtherFindings(){
	      return otherFindings;
	   }
	   public void setOtherFindings( java.lang.String otherFindings){
	      this.otherFindings = otherFindings;
	   }
	

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof AdditionalFindings) {
				AdditionalFindings c =(AdditionalFindings)obj; 			 
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