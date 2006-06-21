package edu.pitt.cabig.cae.domain.general;
import java.util.Collection;
import java.lang.String;





/**
 * @author Jonathan Tobias
 * @version 1.0
 * @created 15-Jun-2006 2:15:37 PM
 */
public class SurgicalPathologySpecimen extends AnnotationSet {

    private Collection surgicalProcedure;
    private String surgicalProcedureMVR;

    public SurgicalPathologySpecimen(){

    }

    public void setSurgicalProcedure(Collection surgicalProcedure) {
        this.surgicalProcedure = surgicalProcedure;
    }

    public Collection getSurgicalProcedure() {
        return surgicalProcedure;
    }

    public void setSurgicalProcedureMVR(String surgicalProcedureMVR) {
        this.surgicalProcedureMVR = surgicalProcedureMVR;
    }

    /**
     * @hibernate.property
     *   column="SURGICAL_PROCEDURE_MVR"
     *   type="java.lang.String"
     *
     */
    public String getSurgicalProcedureMVR() {
        return surgicalProcedureMVR;
    }
}
