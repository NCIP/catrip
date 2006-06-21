package edu.pitt.cabig.cae.domain.breast;
import edu.pitt.cabig.cae.domain.general.SurgicalMargin;
import java.lang.String;
import java.lang.Float;





public class BreastNegativeSurgicalMargin extends SurgicalMargin {

    private String closestNeoplasmPresent;
    private Float distanceToClosestNeoplasm;

    public BreastNegativeSurgicalMargin(){

    }

    public void finalize() throws Throwable {
            super.finalize();
    }

    public void setClosestNeoplasmPresent(String closestNeoplasmPresent) {
        this.closestNeoplasmPresent = closestNeoplasmPresent;
    }

    /**
     * @hibernate.property
     *   column="CLOSEST_NEOPLASM_PRESENT"
     *   type="java.lang.String"
     *
     */
    public String getClosestNeoplasmPresent() {
        return closestNeoplasmPresent;
    }

    public void setDistanceToClosestNeoplasm(Float distanceToClosestNeoplasm) {
        this.distanceToClosestNeoplasm = distanceToClosestNeoplasm;
    }

    /**
     * @hibernate.property
     *   column="DISTANCE_TO_CLOSEST_NEOPLASM"
     *   type="float"
     *
     */
    public Float getDistanceToClosestNeoplasm() {
        return distanceToClosestNeoplasm;
    }
}
