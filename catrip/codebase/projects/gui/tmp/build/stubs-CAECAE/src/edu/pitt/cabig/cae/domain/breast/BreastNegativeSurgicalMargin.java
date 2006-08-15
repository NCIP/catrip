/**
 * BreastNegativeSurgicalMargin.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Jun 16, 2005 (10:54:53 EDT) WSDL2Java emitter.
 */

package edu.pitt.cabig.cae.domain.breast;

public class BreastNegativeSurgicalMargin  extends edu.pitt.cabig.cae.domain.general.SurgicalMargin  implements java.io.Serializable {
    private java.lang.String closestNeoplasmPresent;  // attribute
    private float distanceToClosestNeoplasm;  // attribute

    public BreastNegativeSurgicalMargin() {
    }

    public BreastNegativeSurgicalMargin(
           java.lang.String closestNeoplasmPresent,
           float distanceToClosestNeoplasm) {
           this.closestNeoplasmPresent = closestNeoplasmPresent;
           this.distanceToClosestNeoplasm = distanceToClosestNeoplasm;
    }


    /**
     * Gets the closestNeoplasmPresent value for this BreastNegativeSurgicalMargin.
     * 
     * @return closestNeoplasmPresent
     */
    public java.lang.String getClosestNeoplasmPresent() {
        return closestNeoplasmPresent;
    }


    /**
     * Sets the closestNeoplasmPresent value for this BreastNegativeSurgicalMargin.
     * 
     * @param closestNeoplasmPresent
     */
    public void setClosestNeoplasmPresent(java.lang.String closestNeoplasmPresent) {
        this.closestNeoplasmPresent = closestNeoplasmPresent;
    }


    /**
     * Gets the distanceToClosestNeoplasm value for this BreastNegativeSurgicalMargin.
     * 
     * @return distanceToClosestNeoplasm
     */
    public float getDistanceToClosestNeoplasm() {
        return distanceToClosestNeoplasm;
    }


    /**
     * Sets the distanceToClosestNeoplasm value for this BreastNegativeSurgicalMargin.
     * 
     * @param distanceToClosestNeoplasm
     */
    public void setDistanceToClosestNeoplasm(float distanceToClosestNeoplasm) {
        this.distanceToClosestNeoplasm = distanceToClosestNeoplasm;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BreastNegativeSurgicalMargin)) return false;
        BreastNegativeSurgicalMargin other = (BreastNegativeSurgicalMargin) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.closestNeoplasmPresent==null && other.getClosestNeoplasmPresent()==null) || 
             (this.closestNeoplasmPresent!=null &&
              this.closestNeoplasmPresent.equals(other.getClosestNeoplasmPresent()))) &&
            this.distanceToClosestNeoplasm == other.getDistanceToClosestNeoplasm();
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
        if (getClosestNeoplasmPresent() != null) {
            _hashCode += getClosestNeoplasmPresent().hashCode();
        }
        _hashCode += new Float(getDistanceToClosestNeoplasm()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BreastNegativeSurgicalMargin.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caTissueCAE.caBIG/1.0/edu.pitt.cabig.cae.domain.breast", "BreastNegativeSurgicalMargin"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("closestNeoplasmPresent");
        attrField.setXmlName(new javax.xml.namespace.QName("", "closestNeoplasmPresent"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("distanceToClosestNeoplasm");
        attrField.setXmlName(new javax.xml.namespace.QName("", "distanceToClosestNeoplasm"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
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
