package edu.pitt.cabig.cae.domain.general;
import java.lang.Integer;
import java.lang.String;

/**
 *
 * @hibernate.class
 *           table="THREE_DIMENSIONAL_SIZES"
 *
 *
 */
public class ThreeDimensionalSize extends AnnotationSet {

	private Integer greatestDimension;
	private Integer additionalDimensionY;
	private Integer additionalDimensionZ;
	private String MVR;

	public ThreeDimensionalSize(){

	}



    public void setGreatestDimension(Integer greatestDimension) {
        this.greatestDimension = greatestDimension;
    }

    /**
       *
       * @hibernate.property
       *    column="GREATEST_DIMENSION"
       *    type="java.lang.Integer"
    */
    public Integer getGreatestDimension() {
        return greatestDimension;
    }

    public void setAdditionalDimensionY(Integer additionalDimensionY) {
        this.additionalDimensionY = additionalDimensionY;
    }

    /**
       *
       * @hibernate.property
       *    column="ADDITIONAL_DIMENSION_Y"
       *    type="java.lang.Integer"
    */
    public Integer getAdditionalDimensionY() {
        return additionalDimensionY;
    }

    public void setAdditionalDimensionZ(Integer additionalDimensionZ) {
        this.additionalDimensionZ = additionalDimensionZ;
    }

    /**
       *
       * @hibernate.property
       *    column="ADDITIONAL_DIMENSION_Z"
       *    type="java.lang.Integer"
    */
    public Integer getAdditionalDimensionZ() {
        return additionalDimensionZ;
    }

    public void setMVR(String mVR) {
        this.MVR = mVR;
    }
    /**
       *
       * @hibernate.property
       *    column="MVR"
       *    type="java.lang.String"
       *    size="255"
    */
    public String getMVR() {
        return MVR;
    }
}
