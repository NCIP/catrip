package edu.pitt.cabig.cae.domain.general;
import java.lang.String;

 /**
  *
  * @hibernate.class
  *           table="BREAST_SURGICAL_MARGINS"
  *           @hibernate.discriminator column="DISCRIMINATOR"
  *
  *
  */

public class SurgicalMargin extends AnnotationSet {

    private String spatialRelationshipToPatient;
    private String otherSpatialRelationshipToPatient;
    private String MVR;

    public SurgicalMargin(){

    }

    public void setSpatialRelationshipToPatient(String spatialRelationshipToPatient) {
        this.spatialRelationshipToPatient = spatialRelationshipToPatient;
    }

    /**
     * @hibernate.property
     *   column="SPATIAL_REL_TO_PATIENT"
     *   type="java.lang.String"
     *
     */
    public String getSpatialRelationshipToPatient() {
        return spatialRelationshipToPatient;
    }

    public void setOtherSpatialRelationshipToPatient(String otherSpatialRelationshipToPatient) {
        this.otherSpatialRelationshipToPatient = otherSpatialRelationshipToPatient;
    }

    /**
     * @hibernate.property
     *   column="OTHER_SPA_REL_TO_PATIENT"
     *   type="java.lang.String"
     *
     */
    public String getOtherSpatialRelationshipToPatient() {
        return otherSpatialRelationshipToPatient;
    }

    public void setMVR(String mVR) {
        this.MVR = mVR;
    }

    /**
     * @hibernate.property
     *   column="MVR"
     *   type="java.lang.String"
     *
     */
    public String getMVR() {
        return MVR;
    }
}
