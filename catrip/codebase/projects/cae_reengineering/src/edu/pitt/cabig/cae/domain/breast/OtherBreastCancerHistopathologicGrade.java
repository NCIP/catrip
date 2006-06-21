package edu.pitt.cabig.cae.domain.breast;
import edu.pitt.cabig.cae.domain.general.HistopathologicGrade;
import java.lang.String;
import java.lang.Integer;




public class OtherBreastCancerHistopathologicGrade extends HistopathologicGrade {

    private String systemName;
    private Integer score;
    private String scoreMVR;
    private Integer mitoticCount;

    public OtherBreastCancerHistopathologicGrade(){

    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    /**
     * @hibernate.property
     *   column="SYSTEM_NAME"
     *   type="java.lang.String"
     *
     */
    public String getSystemName() {
        return systemName;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * @hibernate.property
     *   column="SCORE"
     *   type="java.lang.Integer"
     *
     */
    public Integer getScore() {
        return score;
    }

    public void setScoreMVR(String scoreMVR) {
        this.scoreMVR = scoreMVR;
    }

    /**
     * @hibernate.property
     *   column="SCORE_MVR"
     *   type="java.lang.String"
     *
     */
    public String getScoreMVR() {
        return scoreMVR;
    }

    public void setMitoticCount(Integer mitoticCount) {
        this.mitoticCount = mitoticCount;
    }

    /**
     * @hibernate.property
     *   column="MITOTIC_COUNT_O"
     *   type="java.lang.Integer"
     *
     */
    public Integer getMitoticCount() {
        return mitoticCount;
    }
}
