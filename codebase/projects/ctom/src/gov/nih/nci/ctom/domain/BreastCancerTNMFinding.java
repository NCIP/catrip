/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

import java.lang.Integer;
//import java.util.Collection;
import java.lang.String;



 /**
  *
  * @hibernate.class
  *           table="BREAST_CANCER_TNMF_FINDINGS"
  *
  *
  */
  
public class BreastCancerTNMFinding  {

    private Integer numberLymphNodesExamined;
    private Integer numberLymphNodesInvolved;
    //private Collection metastasisAnatomicSite;
    private String otherMetastaticAnatomicSite;

    public BreastCancerTNMFinding(){

    }


    public void setNumberLymphNodesExamined(Integer numberLymphNodesExamined) {
        this.numberLymphNodesExamined = numberLymphNodesExamined;
    }

    /**
     * @hibernate.property
     *   column="NUMBER_LYMPH_NODES_EXAMINED"
     *   type="integer"
     *      
     */
    public Integer getNumberLymphNodesExamined() {
        return numberLymphNodesExamined;
    }

    public void setNumberLymphNodesInvolved(Integer numberLymphNodesInvolved) {
        this.numberLymphNodesInvolved = numberLymphNodesInvolved;
    }

    /**
     * @hibernate.property
     *   column="NUMBER_LYMPH_NODES_INVOLVED"
     *   type="integer"
     *      
     */
    public Integer getNumberLymphNodesInvolved() {
        return numberLymphNodesInvolved;
    }

  // public void setMetastasisAnatomicSite(Collection metastasisAnatomicSite) {
   //     this.metastasisAnatomicSite = metastasisAnatomicSite;
   // }

    /**
     * @hibernate.id
     *   column="NUMBER_LYMPH_NODES_EXAMINED"
     *   type="integer"
     *      
     */
 //  public Collection getMetastasisAnatomicSite() {
  //      return metastasisAnatomicSite;
  //  }

    public void setOtherMetastaticAnatomicSite(String otherMetastaticAnatomicSite) {
        this.otherMetastaticAnatomicSite = otherMetastaticAnatomicSite;
    }

    /**
     * @hibernate.property
     *   column="OTHER_METASTASIS_ANATOMIC_SITE"
     *   type="integer"
     *      
     */
    public String getOtherMetastaticAnatomicSite() {
        return otherMetastaticAnatomicSite;
    }
}
