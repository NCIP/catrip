/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */


package edu.pitt.cabig.cae.domain.breast;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * A grading system for breast cancer.
   */

public  class NottinghamHistopathologicGrade 
    extends edu.pitt.cabig.cae.domain.general.HistopathologicGrade
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   private java.lang.Integer tubuleFormation;
	   public  java.lang.Integer getTubuleFormation(){
	      return tubuleFormation;
	   }
	   public void setTubuleFormation( java.lang.Integer tubuleFormation){
	      this.tubuleFormation = tubuleFormation;
	   }
	
	   
	   private java.lang.Integer nuclearPleomorphism;
	   public  java.lang.Integer getNuclearPleomorphism(){
	      return nuclearPleomorphism;
	   }
	   public void setNuclearPleomorphism( java.lang.Integer nuclearPleomorphism){
	      this.nuclearPleomorphism = nuclearPleomorphism;
	   }
	
	   
	   private java.lang.Integer mitoticCount;
	   public  java.lang.Integer getMitoticCount(){
	      return mitoticCount;
	   }
	   public void setMitoticCount( java.lang.Integer mitoticCount){
	      this.mitoticCount = mitoticCount;
	   }
	
	   
	   private java.lang.Integer totalScore;
	   public  java.lang.Integer getTotalScore(){
	      return totalScore;
	   }
	   public void setTotalScore( java.lang.Integer totalScore){
	      this.totalScore = totalScore;
	   }
	
	   
	   private java.lang.String totalScoreMVR;
	   public  java.lang.String getTotalScoreMVR(){
	      return totalScoreMVR;
	   }
	   public void setTotalScoreMVR( java.lang.String totalScoreMVR){
	      this.totalScoreMVR = totalScoreMVR;
	   }
	

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof NottinghamHistopathologicGrade) {
				NottinghamHistopathologicGrade c =(NottinghamHistopathologicGrade)obj; 			 
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