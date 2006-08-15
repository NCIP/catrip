/**
 * BreastPositiveSurgicalMargin.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Jun 16, 2005 (10:54:53 EDT) WSDL2Java emitter.
 */

package edu.pitt.cabig.cae.domain.breast;

public class BreastPositiveSurgicalMargin  extends edu.pitt.cabig.cae.domain.general.SurgicalMargin  implements java.io.Serializable {
    private java.lang.String neoplasmPresent;  // attribute
    private java.lang.String extentInvolvement;  // attribute
    private java.lang.String otherExtentInvolvement;  // attribute

    public BreastPositiveSurgicalMargin() {
    }

    public BreastPositiveSurgicalMargin(
           java.lang.String extentInvolvement,
           java.lang.String neoplasmPresent,
           java.lang.String otherExtentInvolvement) {
           this.neoplasmPresent = neoplasmPresent;
           this.extentInvolvement = extentInvolvement;
           this.otherExtentInvolvement = otherExtentInvolvement;
    }


    /**
     * Gets the neoplasmPresent value for this BreastPositiveSurgicalMargin.
     * 
     * @return neoplasmPresent
     */
    public java.lang.String getNeoplasmPresent() {
        return neoplasmPresent;
    }


    /**
     * Sets the neoplasmPresent value for this BreastPositiveSurgicalMargin.
     * 
     * @param neoplasmPresent
     */
    public void setNeoplasmPresent(java.lang.String neoplasmPresent) {
        this.neoplasmPresent = neoplasmPresent;
    }


    /**
     * Gets the extentInvolvement value for this BreastPositiveSurgicalMargin.
     * 
     * @return extentInvolvement
     */
    public java.lang.String getExtentInvolvement() {
        return extentInvolvement;
    }


    /**
     * Sets the extentInvolvement value for this BreastPositiveSurgicalMargin.
     * 
     * @param extentInvolvement
     */
    public void setExtentInvolvement(java.lang.String extentInvolvement) {
        this.extentInvolvement = extentInvolvement;
    }


    /**
     * Gets the otherExtentInvolvement value for this BreastPositiveSurgicalMargin.
     * 
     * @return otherExtentInvolvement
     */
    public java.lang.String getOtherExtentInvolvement() {
        return otherExtentInvolvement;
    }


    /**
     * Sets the otherExtentInvolvement value for this BreastPositiveSurgicalMargin.
     * 
     * @param otherExtentInvolvement
     */
    public void setOtherExtentInvolvement(java.lang.String otherExtentInvolvement) {
        this.otherExtentInvolvement = otherExtentInvolvement;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BreastPositiveSurgicalMargin)) return false;
        BreastPositiveSurgicalMargin other = (BreastPositiveSurgicalMargin) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.neoplasmPresent==null && other.getNeoplasmPresent()==null) || 
             (this.neoplasmPresent!=null &&
              this.neoplasmPresent.equals(other.getNeoplasmPresent()))) &&
            ((this.extentInvolvement==null && other.getExtentInvolvement()==null) || 
             (this.extentInvolvement!=null &&
              this.extentInvolvement.equals(other.getExtentInvolvement()))) &&
            ((this.otherExtentInvolvement==null && other.getOtherExtentInvolvement()==null) || 
             (this.otherExtentInvolvement!=null &&
              this.otherExtentInvolvement.equals(other.getOtherExtentInvolvement())));
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
        if (getNeoplasmPresent() != null) {
            _hashCode += getNeoplasmPresent().hashCode();
        }
        if (getExtentInvolvement() != null) {
            _hashCode += getExtentInvolvement().hashCode();
        }
        if (getOtherExtentInvolvement() != null) {
            _hashCode += getOtherExtentInvolvement().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BreastPositiveSurgicalMargin.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caTissueCAE.caBIG/1.0/edu.pitt.cabig.cae.domain.breast", "BreastPositiveSurgicalMargin"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("neoplasmPresent");
        attrField.setXmlName(new javax.xml.namespace.QName("", "neoplasmPresent"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("extentInvolvement");
        attrField.setXmlName(new javax.xml.namespace.QName("", "extentInvolvement"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("otherExtentInvolvement");
        attrField.setXmlName(new javax.xml.namespace.QName("", "otherExtentInvolvement"));
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
