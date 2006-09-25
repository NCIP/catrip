

package edu.duke.catrip.cae.domain.general;

import java.util.Set;


/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Harmonized Particpant class . This is part of CAE backbone object hrmonization . Attributes of ParticipantIdentifiers 
   * and Demographics classes of CAE are harmonized into this Participant class . 
   * 
   */

public class Participant extends edu.pitt.cabig.cae.domain.general.AnnotatableEntity
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	   private java.lang.String lastName;
	   public  java.lang.String getLastName(){
	      return lastName;
	   }
	   
	   public void setLastName( java.lang.String lastName){
	      this.lastName = lastName;
	   }
	
	   
	   private java.lang.String firstName;
	   public  java.lang.String getFirstName(){
	      return firstName;
	   }
	   public void setFirstName( java.lang.String firstName){
	      this.firstName = firstName;
	   }
	
	   
	   private java.lang.String middleName;
	   public  java.lang.String getMiddleName(){
	      return middleName;
	   }
	   public void setMiddleName( java.lang.String middleName){
	      this.middleName = middleName;
	   }
	
	   
	   private java.util.Date birthDate;
	   public  java.util.Date getBirthDate(){
	      return birthDate;
	   }
	   public void setBirthDate( java.util.Date birthDate){
	      this.birthDate = birthDate;
	   }
	
	    
	   private java.lang.String uniquePatientIdentifier;
	   public  java.lang.String getUniquePatientIdentifier(){
	      return uniquePatientIdentifier;
	   }
	   public void setUniquePatientIdentifier( java.lang.String uniquePatientIdentifier){
	      this.uniquePatientIdentifier = uniquePatientIdentifier;
	   }
	
	   
	   private java.util.Set medicalRecordNumberCollection;
	   public  java.util.Set getMedicalRecordNumberCollection(){
	      return medicalRecordNumberCollection;
	   }
	   public void setMedicalRecordNumberCollection( java.util.Set medicalRecordNumberCollection){
	      this.medicalRecordNumberCollection = medicalRecordNumberCollection;
	   }
	
	   
	   private java.lang.String gender;
	   public  java.lang.String getGender(){
	      return gender;
	   }
	   public void setGender( java.lang.String gender){
	      this.gender = gender;
	   }
	
	   
	   private java.lang.String race;
	   public  java.lang.String getRace(){
	      return race;
	   }
	   public void setRace( java.lang.String race){
	      this.race = race;
	   }
	
	   
	   private java.lang.String ethnicity;
	   public  java.lang.String getEthnicity(){
	      return ethnicity;
	   }
	   public void setEthnicity( java.lang.String ethnicity){
	      this.ethnicity = ethnicity;
	   }
	

	
	  private Set accessionCollection;
		public Set getAccessionCollection() {
			return accessionCollection;
		}

		public void setAccessionCollection(Set accessionCollection) {
			this.accessionCollection = accessionCollection;
		}

	   
	   

	
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Participant) {
				Participant c =(Participant)obj; 			 
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