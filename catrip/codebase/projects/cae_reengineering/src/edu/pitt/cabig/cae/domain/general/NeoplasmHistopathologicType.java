package edu.pitt.cabig.cae.domain.general;
import java.lang.String;





/**
 * @version 1.0
 * @created 15-Jun-2006 2:15:33 PM
 */
public class NeoplasmHistopathologicType extends AnnotationSet {

    private String name;
    private String nameMVR;
    private String otherName;

    public NeoplasmHistopathologicType(){

    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @hibernate.property
     *   column="NAME"
     *   type="java.lang.String"
     *
     */
    public String getName() {
        return name;
    }

    public void setNameMVR(String nameMVR) {
        this.nameMVR = nameMVR;
    }
    /**
     * @hibernate.property
     *   column="NAME_MVR"
     *   type="java.lang.String"
     *
     */
    public String getNameMVR() {
        return nameMVR;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }
    /**
     * @hibernate.property
     *   column="OTHER_NAME"
     *   type="java.lang.String"
     *
     */
    public String getOtherName() {
        return otherName;
    }
}
