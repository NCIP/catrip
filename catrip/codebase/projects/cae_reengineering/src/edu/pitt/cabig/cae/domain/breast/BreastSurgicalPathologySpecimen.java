package edu.pitt.cabig.cae.domain.breast;
import edu.pitt.cabig.cae.domain.general.SurgicalPathologySpecimen;
import java.lang.String;


 /**
  *
  * @hibernate.class
  *           table="BREAST_SURGICAL_PATHOLOGY_SPECIMANS"
  *
  *
  */
public class BreastSurgicalPathologySpecimen extends SurgicalPathologySpecimen {

    private String otherSurgicalProcedure;
    private String lymphNodeSamplingProcedure;
    private String laterality;
    private String lateralityMVR;

    public BreastSurgicalPathologySpecimen(){

    }

    public void setOtherSurgicalProcedure(String otherSurgicalProcedure) {
        this.otherSurgicalProcedure = otherSurgicalProcedure;
    }

    /**
     * @hibernate.property
     *   column="OTHER_SURGICAL_PROCEDURE"
     *   type="java.lang.String"
     *
     */
    public String getOtherSurgicalProcedure() {
        return otherSurgicalProcedure;
    }

    public void setLymphNodeSamplingProcedure(String lymphNodeSamplingProcedure) {
        this.lymphNodeSamplingProcedure = lymphNodeSamplingProcedure;
    }

    /**
     * @hibernate.property
     *   column="LYMPH_NODE_SAMPLING_PROCEDURE"
     *   type="java.lang.String"
     *
     */
    public String getLymphNodeSamplingProcedure() {
        return lymphNodeSamplingProcedure;
    }

    public void setLaterality(String laterality) {
        this.laterality = laterality;
    }

    /**
     * @hibernate.property
     *   column="LATERALITY"
     *   type="java.lang.String"
     *
     */
    public String getLaterality() {
        return laterality;
    }

    public void setLateralityMVR(String lateralityMVR) {
        this.lateralityMVR = lateralityMVR;
    }

    /**
     * @hibernate.property
     *   column="LATERALITY_MVR"
     *   type="java.lang.String"
     *
     */
    public String getLateralityMVR() {
        return lateralityMVR;
    }
}
