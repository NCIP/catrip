

package edu.pitt.cabig.cae.domain.breast;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * TNM finding of the American Joint Committee on Cancer (AJCC) and Internation Union Against Cancer 
   * (UICC) recommended classification system for breast cancers. 
   * 
   */

public  class BreastCancerTNMFinding 
    extends edu.pitt.cabig.cae.domain.general.CancerTNMFinding
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   private java.lang.Integer numberLymphNodesExamined;
	   public  java.lang.Integer getNumberLymphNodesExamined(){
	      return numberLymphNodesExamined;
	   }
	   public void setNumberLymphNodesExamined( java.lang.Integer numberLymphNodesExamined){
	      this.numberLymphNodesExamined = numberLymphNodesExamined;
	   }
	
	   
	   private java.lang.Integer numberLymphNodesInvolved;
	   public  java.lang.Integer getNumberLymphNodesInvolved(){
	      return numberLymphNodesInvolved;
	   }
	   public void setNumberLymphNodesInvolved( java.lang.Integer numberLymphNodesInvolved){
	      this.numberLymphNodesInvolved = numberLymphNodesInvolved;
	   }
	
	   
	   private java.util.Collection metastasisAnatomicSite;
	   public  java.util.Collection getMetastasisAnatomicSite(){
	      return metastasisAnatomicSite;
	   }
	   public void setMetastasisAnatomicSite( java.util.Collection metastasisAnatomicSite){
	      this.metastasisAnatomicSite = metastasisAnatomicSite;
	   }
	
	   
	   private java.lang.String otherMetastaticAnatomicSite;
	   public  java.lang.String getOtherMetastaticAnatomicSite(){
	      return otherMetastaticAnatomicSite;
	   }
	   public void setOtherMetastaticAnatomicSite( java.lang.String otherMetastaticAnatomicSite){
	      this.otherMetastaticAnatomicSite = otherMetastaticAnatomicSite;
	   }
	

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof BreastCancerTNMFinding) {
				BreastCancerTNMFinding c =(BreastCancerTNMFinding)obj; 			 
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