/**
 * BreastSurgicalPathologySpecimen.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Jun 16, 2005 (10:54:53 EDT) WSDL2Java emitter.
 */

package edu.pitt.cabig.cae.domain.breast;

public class BreastSurgicalPathologySpecimen  extends edu.pitt.cabig.cae.domain.general.SurgicalPathologySpecimen  implements java.io.Serializable {
    private java.lang.String otherSurgicalProcedure;  // attribute
    private java.lang.String lymphNodeSamplingProcedure;  // attribute
    private java.lang.String laterality;  // attribute
    private java.lang.String lateralityMVR;  // attribute

    public BreastSurgicalPathologySpecimen() {
    }

    public BreastSurgicalPathologySpecimen(
           java.lang.String laterality,
           java.lang.String lateralityMVR,
           java.lang.String lymphNodeSamplingProcedure,
           java.lang.String otherSurgicalProcedure) {
           this.otherSurgicalProcedure = otherSurgicalProcedure;
           this.lymphNodeSamplingProcedure = lymphNodeSamplingProcedure;
           this.laterality = laterality;
           this.lateralityMVR = lateralityMVR;
    }


    /**
     * Gets the otherSurgicalProcedure value for this BreastSurgicalPathologySpecimen.
     * 
     * @return otherSurgicalProcedure
     */
    public java.lang.String getOtherSurgicalProcedure() {
        return otherSurgicalProcedure;
    }


    /**
     * Sets the otherSurgicalProcedure value for this BreastSurgicalPathologySpecimen.
     * 
     * @param otherSurgicalProcedure
     */
    public void setOtherSurgicalProcedure(java.lang.String otherSurgicalProcedure) {
        this.otherSurgicalProcedure = otherSurgicalProcedure;
    }


    /**
     * Gets the lymphNodeSamplingProcedure value for this BreastSurgicalPathologySpecimen.
     * 
     * @return lymphNodeSamplingProcedure
     */
    public java.lang.String getLymphNodeSamplingProcedure() {
        return lymphNodeSamplingProcedure;
    }


    /**
     * Sets the lymphNodeSamplingProcedure value for this BreastSurgicalPathologySpecimen.
     * 
     * @param lymphNodeSamplingProcedure
     */
    public void setLymphNodeSamplingProcedure(java.lang.String lymphNodeSamplingProcedure) {
        this.lymphNodeSamplingProcedure = lymphNodeSamplingProcedure;
    }


    /**
     * Gets the laterality value for this BreastSurgicalPathologySpecimen.
     * 
     * @return laterality
     */
    public java.lang.String getLaterality() {
        return laterality;
    }


    /**
     * Sets the laterality value for this BreastSurgicalPathologySpecimen.
     * 
     * @param laterality
     */
    public void setLaterality(java.lang.String laterality) {
        this.laterality = laterality;
    }


    /**
     * Gets the lateralityMVR value for this BreastSurgicalPathologySpecimen.
     * 
     * @return lateralityMVR
     */
    public java.lang.String getLateralityMVR() {
        return lateralityMVR;
    }


    /**
     * Sets the lateralityMVR value for this BreastSurgicalPathologySpecimen.
     * 
     * @param lateralityMVR
     */
    public void setLateralityMVR(java.lang.String lateralityMVR) {
        this.lateralityMVR = lateralityMVR;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BreastSurgicalPathologySpecimen)) return false;
        BreastSurgicalPathologySpecimen other = (BreastSurgicalPathologySpecimen) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.otherSurgicalProcedure==null && other.getOtherSurgicalProcedure()==null) || 
             (this.otherSurgicalProcedure!=null &&
              this.otherSurgicalProcedure.equals(other.getOtherSurgicalProcedure()))) &&
            ((this.lymphNodeSamplingProcedure==null && other.getLymphNodeSamplingProcedure()==null) || 
             (this.lymphNodeSamplingProcedure!=null &&
              this.lymphNodeSamplingProcedure.equals(other.getLymphNodeSamplingProcedure()))) &&
            ((this.laterality==null && other.getLaterality()==null) || 
             (this.laterality!=null &&
              this.laterality.equals(other.getLaterality()))) &&
            ((this.lateralityMVR==null && other.getLateralityMVR()==null) || 
             (this.lateralityMVR!=null &&
              this.lateralityMVR.equals(other.getLateralityMVR())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getOtherSurgicalProcedure() != null) {
            _hashCode += getOtherSurgicalProcedure().hashCode();
        }
        if (getLymphNodeSamplingProcedure() != null) {
            _hashCode += getLymphNodeSamplingProcedure().hashCode();
        }
        if (getLaterality() != null) {
            _hashCode += getLaterality().hashCode();
        }
        if (getLateralityMVR() != null) {
            _hashCode += getLateralityMVR().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BreastSurgicalPathologySpecimen.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caTissueCAE.caBIG/1.0/edu.pitt.cabig.cae.domain.breast", "BreastSurgicalPathologySpecimen"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("otherSurgicalProcedure");
        attrField.setXmlName(new javax.xml.namespace.QName("", "otherSurgicalProcedure"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("lymphNodeSamplingProcedure");
        attrField.setXmlName(new javax.xml.namespace.QName("", "lymphNodeSamplingProcedure"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("laterality");
        attrField.setXmlName(new javax.xml.namespace.QName("", "laterality"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("lateralityMVR");
        attrField.setXmlName(new javax.xml.namespace.QName("", "lateralityMVR"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
