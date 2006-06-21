package edu.pitt.cabig.cae.domain.general;
import java.lang.String;



 /**
  *
  * @hibernate.class
  *           table="ADDITIONAL_FINDINGS"
  *
  *
  */
public class AdditionalFindings extends AnnotationSet {

    private String otherFindings;

    public AdditionalFindings(){

    }


    public void setOtherFindings(String otherFindings) {
        this.otherFindings = otherFindings;
    }


    /**
     * @hibernate.property
     *   column="OTHER_FINDINGS"
     *   type="java.lang.String"
     *
     */
    public String getOtherFindings() {
        return otherFindings;
    }
}
