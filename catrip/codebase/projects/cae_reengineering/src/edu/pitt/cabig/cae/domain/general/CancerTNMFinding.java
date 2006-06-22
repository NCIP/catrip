package edu.pitt.cabig.cae.domain.general;
import java.lang.String;





/**
 * @version 1.0
 * @created 15-Jun-2006 2:15:29 PM
 */
public class CancerTNMFinding extends AnnotationSet {

	private String category;
	private String primaryTumorFinding;
	private String regionalLymphNodesFinding;
	private String distantMetastasisFinding;

	public CancerTNMFinding(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

    public void setCategory(String category) {
        this.category = category;
    }
    /**
     * @hibernate.property
     *   column="CATEGORY"
     *   type="java.lang.String"
     *
     */
    public String getCategory() {
        return category;
    }

    public void setPrimaryTumorFinding(String primaryTumorFinding) {
        this.primaryTumorFinding = primaryTumorFinding;
    }

    /**
     * @hibernate.property
     *   column="PRIMARY_TUMOR_FINDING"
     *   type="java.lang.String"
     *
     */
    public String getPrimaryTumorFinding() {
        return primaryTumorFinding;
    }

    public void setRegionalLymphNodesFinding(String regionalLymphNodesFinding) {
        this.regionalLymphNodesFinding = regionalLymphNodesFinding;
    }

    /**
     * @hibernate.property
     *   column="REGIONAL_NODES_FINDING"
     *   type="java.lang.String"
     *
     */
    public String getRegionalLymphNodesFinding() {
        return regionalLymphNodesFinding;
    }

    public void setDistantMetastasisFinding(String distantMetastasisFinding) {
        this.distantMetastasisFinding = distantMetastasisFinding;
    }

    /**
     * @hibernate.property
     *   column="DISTANT_MET_FINDING"
     *   type="java.lang.String"
     *
     */
    public String getDistantMetastasisFinding() {
        return distantMetastasisFinding;
    }
}
