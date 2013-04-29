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
   * The diagnostic subclassification of invasive breast carcinoma.
   */

public  class InvasiveBreastCarcinomaNeoplasmHistologicType 
    extends edu.pitt.cabig.cae.domain.general.NeoplasmHistopathologicType
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	

	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof InvasiveBreastCarcinomaNeoplasmHistologicType) {
				InvasiveBreastCarcinomaNeoplasmHistologicType c =(InvasiveBreastCarcinomaNeoplasmHistologicType)obj; 			 
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