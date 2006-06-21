package edu.pitt.cabig.cae.domain.breast;
import edu.pitt.cabig.cae.domain.general.SurgicalMargin;
import java.lang.String;




public class BreastPositiveSurgicalMargin extends SurgicalMargin {

    private String neoplasmPresent;
    private String extentInvolvement;
    private String otherExtentInvolvement;

    public BreastPositiveSurgicalMargin(){

    }

    public void finalize() throws Throwable {
            super.finalize();
    }

    public void setNeoplasmPresent(String neoplasmPresent) {
        this.neoplasmPresent = neoplasmPresent;
    }

    /**
     * @hibernate.property
     *   column="NEOPLASM_PRESENT"
     *   type="java.lang.String"
     *
     */
    public String getNeoplasmPresent() {
        return neoplasmPresent;
    }

    public void setExtentInvolvement(String extentInvolvement) {
        this.extentInvolvement = extentInvolvement;
    }

    /**
     * @hibernate.property
     *   column="EXTENT_INVOLVEMENT"
     *   type="java.lang.String"
     *
     */
    public String getExtentInvolvement() {
        return extentInvolvement;
    }

    public void setOtherExtentInvolvement(String otherExtentInvolvement) {
        this.otherExtentInvolvement = otherExtentInvolvement;
    }

    /**
     * @hibernate.property
     *   column="OTHER_EXTENT_INVOLVEMENT"
     *   type="java.lang.String"
     *
     */
    public String getOtherExtentInvolvement() {
        return otherExtentInvolvement;
    }
}
