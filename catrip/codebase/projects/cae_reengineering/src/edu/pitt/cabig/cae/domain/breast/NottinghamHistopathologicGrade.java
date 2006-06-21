package edu.pitt.cabig.cae.domain.breast;
import edu.pitt.cabig.cae.domain.general.HistopathologicGrade;
import java.lang.Integer;
import java.lang.String;



public class NottinghamHistopathologicGrade extends HistopathologicGrade {

    private Integer tubuleFormation;
    private Integer nuclearPleomorphism;
    private Integer mitoticCount;
    private Integer totalScore;
    private String totalScoreMVR;

    public NottinghamHistopathologicGrade(){

    }



    public void setTubuleFormation(Integer tubuleFormation) {
        this.tubuleFormation = tubuleFormation;
    }

    /**
     * @hibernate.property
     *   column="TUBULE_FORMATION"
     *   type="java.lang.Integer"
     *
     */
    public Integer getTubuleFormation() {
        return tubuleFormation;
    }

    public void setNuclearPleomorphism(Integer nuclearPleomorphism) {
        this.nuclearPleomorphism = nuclearPleomorphism;
    }
    /**
     * @hibernate.property
     *   column="NUCLEAR_PLEOMORPHISM"
     *   type="java.lang.Integer"
     *
     */
    public Integer getNuclearPleomorphism() {
        return nuclearPleomorphism;
    }

    public void setMitoticCount(Integer mitoticCount) {
        this.mitoticCount = mitoticCount;
    }
    /**
     * @hibernate.property
     *   column="MITOTIC_COUNT_N"
     *   type="java.lang.Integer"
     *
     */
    public Integer getMitoticCount() {
        return mitoticCount;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
    /**
     * @hibernate.property
     *   column="TOTAL_SCORE"
     *   type="java.lang.Integer"
     *
     */
    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScoreMVR(String totalScoreMVR) {
        this.totalScoreMVR = totalScoreMVR;
    }
    /**
     * @hibernate.property
     *   column="TOTAL_SCORE_MVR"
     *   type="java.lang.String"
     *
     */
    public String getTotalScoreMVR() {
        return totalScoreMVR;
    }
}
