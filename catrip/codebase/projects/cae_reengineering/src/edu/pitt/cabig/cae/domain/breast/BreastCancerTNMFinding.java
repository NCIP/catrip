package edu.pitt.cabig.cae.domain.breast;
import edu.pitt.cabig.cae.domain.general.CancerTNMFinding;
import java.lang.Integer;
import java.util.Collection;
import java.lang.String;



 /**
  *
  * @hibernate.class
  *           table="BREAST_CANCER_TNMF_FINDINGS"
  *
  *
  */

public class BreastCancerTNMFinding extends CancerTNMFinding {

    private Integer numberLymphNodesExamined;
    private Integer numberLymphNodesInvolved;
    private Collection metastasisAnatomicSite;
    private String otherMetastaticAnatomicSite;

    public BreastCancerTNMFinding(){

    }


    public void setNumberLymphNodesExamined(Integer numberLymphNodesExamined) {
        this.numberLymphNodesExamined = numberLymphNodesExamined;
    }

    /**
     * @hibernate.property
     *   column="NUMBER_LYMPH_NODES_EXAMINED"
     *   type="java.lang.Integer"
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
     *   type="java.lang.Integer"
     *
     */
    public Integer getNumberLymphNodesInvolved() {
        return numberLymphNodesInvolved;
    }

    public void setMetastasisAnatomicSite(Collection metastasisAnatomicSite) {
        this.metastasisAnatomicSite = metastasisAnatomicSite;
    }

    /**
     *
     * @hibernate.bag  
     *            name="metastasisAnatomicSite"
     *            lazy="false"
     *            table="BREAST_METASTASIS_ANATOMIC_SITES"
     * 
     * @hibernate.collection-key 
     *            column="ID"
     * 
     * @hibernate.collection-element
     *            column="METASTASIS_ANATOMIC_SITE"
     *            type="java.lang.String"
     * 
     */
    public Collection getMetastasisAnatomicSite() {
        return metastasisAnatomicSite;
    }

    public void setOtherMetastaticAnatomicSite(String otherMetastaticAnatomicSite) {
        this.otherMetastaticAnatomicSite = otherMetastaticAnatomicSite;
    }

    /**
     * @hibernate.property
     *   column="OTHER_METASTASIS_ANATOMIC_SITE"
     *   type="java.lang.Integer"
     *
     */
    public String getOtherMetastaticAnatomicSite() {
        return otherMetastaticAnatomicSite;
    }
}
