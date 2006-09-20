

package edu.duke.catrip.cae.domain.general;

import java.util.Set;


/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Harmonized Specimen class . This is part of CAE backbone object hrmonization . Attributes of SpecimenIdentifiers 
   * class of CAE are harmonized into this Specimen class . 
   * 
   */

public  class Specimen 
   extends edu.pitt.cabig.cae.domain.general.AnnotatableEntity{
	private static final long serialVersionUID = 1234567890L;
	private Set specimenCollection;
	   
	public Specimen(){

	}
	   
	   private java.lang.String surgicalLabel;
	   public  java.lang.String getSurgicalLabel(){
	      return surgicalLabel;
	   }
	   public void setSurgicalLabel( java.lang.String surgicalLabel){
	      this.surgicalLabel = surgicalLabel;
	   }
	

	public Set getSpecimenCollection() {
		return specimenCollection;
	}

	public void setSpecimenCollection(Set specimenCollection) {
		this.specimenCollection = specimenCollection;
	}

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Specimen) {
				Specimen c =(Specimen)obj; 			 
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