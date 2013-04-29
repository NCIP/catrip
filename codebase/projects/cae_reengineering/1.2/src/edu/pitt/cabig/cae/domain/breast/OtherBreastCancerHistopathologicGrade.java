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
   * An alternative grading system for breast cancer (not Nottingham Grade).
   */

public  class OtherBreastCancerHistopathologicGrade 
    extends edu.pitt.cabig.cae.domain.general.HistopathologicGrade
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   private java.lang.String systemName;
	   public  java.lang.String getSystemName(){
	      return systemName;
	   }
	   public void setSystemName( java.lang.String systemName){
	      this.systemName = systemName;
	   }
	
	   
	   private java.lang.Integer score;
	   public  java.lang.Integer getScore(){
	      return score;
	   }
	   public void setScore( java.lang.Integer score){
	      this.score = score;
	   }
	
	   
	   private java.lang.String scoreMVR;
	   public  java.lang.String getScoreMVR(){
	      return scoreMVR;
	   }
	   public void setScoreMVR( java.lang.String scoreMVR){
	      this.scoreMVR = scoreMVR;
	   }
	
	   
	   private java.lang.Integer mitoticCount;
	   public  java.lang.Integer getMitoticCount(){
	      return mitoticCount;
	   }
	   public void setMitoticCount( java.lang.Integer mitoticCount){
	      this.mitoticCount = mitoticCount;
	   }
	

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof OtherBreastCancerHistopathologicGrade) {
				OtherBreastCancerHistopathologicGrade c =(OtherBreastCancerHistopathologicGrade)obj; 			 
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