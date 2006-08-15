/**
 * BreastCancerTNMFinding.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Jun 16, 2005 (10:54:53 EDT) WSDL2Java emitter.
 */

package edu.pitt.cabig.cae.domain.breast;

public class BreastCancerTNMFinding  extends edu.pitt.cabig.cae.domain.general.CancerTNMFinding  implements java.io.Serializable {
    private java.math.BigInteger numberLymphNodesExamined;  // attribute
    private java.math.BigInteger numberLymphNodesInvolved;  // attribute
    private java.lang.String metastasisAnatomicSite;  // attribute
    private java.lang.String otherMetastaticAnatomicSite;  // attribute

    public BreastCancerTNMFinding() {
    }

    public BreastCancerTNMFinding(
           java.lang.String metastasisAnatomicSite,
           java.math.BigInteger numberLymphNodesExamined,
           java.math.BigInteger numberLymphNodesInvolved,
           java.lang.String otherMetastaticAnatomicSite) {
           this.numberLymphNodesExamined = numberLymphNodesExamined;
           this.numberLymphNodesInvolved = numberLymphNodesInvolved;
           this.metastasisAnatomicSite = metastasisAnatomicSite;
           this.otherMetastaticAnatomicSite = otherMetastaticAnatomicSite;
    }


    /**
     * Gets the numberLymphNodesExamined value for this BreastCancerTNMFinding.
     * 
     * @return numberLymphNodesExamined
     */
    public java.math.BigInteger getNumberLymphNodesExamined() {
        return numberLymphNodesExamined;
    }


    /**
     * Sets the numberLymphNodesExamined value for this BreastCancerTNMFinding.
     * 
     * @param numberLymphNodesExamined
     */
    public void setNumberLymphNodesExamined(java.math.BigInteger numberLymphNodesExamined) {
        this.numberLymphNodesExamined = numberLymphNodesExamined;
    }


    /**
     * Gets the numberLymphNodesInvolved value for this BreastCancerTNMFinding.
     * 
     * @return numberLymphNodesInvolved
     */
    public java.math.BigInteger getNumberLymphNodesInvolved() {
        return numberLymphNodesInvolved;
    }


    /**
     * Sets the numberLymphNodesInvolved value for this BreastCancerTNMFinding.
     * 
     * @param numberLymphNodesInvolved
     */
    public void setNumberLymphNodesInvolved(java.math.BigInteger numberLymphNodesInvolved) {
        this.numberLymphNodesInvolved = numberLymphNodesInvolved;
    }


    /**
     * Gets the metastasisAnatomicSite value for this BreastCancerTNMFinding.
     * 
     * @return metastasisAnatomicSite
     */
    public java.lang.String getMetastasisAnatomicSite() {
        return metastasisAnatomicSite;
    }


    /**
     * Sets the metastasisAnatomicSite value for this BreastCancerTNMFinding.
     * 
     * @param metastasisAnatomicSite
     */
    public void setMetastasisAnatomicSite(java.lang.String metastasisAnatomicSite) {
        this.metastasisAnatomicSite = metastasisAnatomicSite;
    }


    /**
     * Gets the otherMetastaticAnatomicSite value for this BreastCancerTNMFinding.
     * 
     * @return otherMetastaticAnatomicSite
     */
    public java.lang.String getOtherMetastaticAnatomicSite() {
        return otherMetastaticAnatomicSite;
    }


    /**
     * Sets the otherMetastaticAnatomicSite value for this BreastCancerTNMFinding.
     * 
     * @param otherMetastaticAnatomicSite
     */
    public void setOtherMetastaticAnatomicSite(java.lang.String otherMetastaticAnatomicSite) {
        this.otherMetastaticAnatomicSite = otherMetastaticAnatomicSite;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BreastCancerTNMFinding)) return false;
        BreastCancerTNMFinding other = (BreastCancerTNMFinding) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.numberLymphNodesExamined==null && other.getNumberLymphNodesExamined()==null) || 
             (this.numberLymphNodesExamined!=null &&
              this.numberLymphNodesExamined.equals(other.getNumberLymphNodesExamined()))) &&
            ((this.numberLymphNodesInvolved==null && other.getNumberLymphNodesInvolved()==null) || 
             (this.numberLymphNodesInvolved!=null &&
              this.numberLymphNodesInvolved.equals(other.getNumberLymphNodesInvolved()))) &&
            ((this.metastasisAnatomicSite==null && other.getMetastasisAnatomicSite()==null) || 
             (this.metastasisAnatomicSite!=null &&
              this.metastasisAnatomicSite.equals(other.getMetastasisAnatomicSite()))) &&
            ((this.otherMetastaticAnatomicSite==null && other.getOtherMetastaticAnatomicSite()==null) || 
             (this.otherMetastaticAnatomicSite!=null &&
              this.otherMetastaticAnatomicSite.equals(other.getOtherMetastaticAnatomicSite())));
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
        if (getNumberLymphNodesExamined() != null) {
            _hashCode += getNumberLymphNodesExamined().hashCode();
        }
        if (getNumberLymphNodesInvolved() != null) {
            _hashCode += getNumberLymphNodesInvolved().hashCode();
        }
        if (getMetastasisAnatomicSite() != null) {
            _hashCode += getMetastasisAnatomicSite().hashCode();
        }
        if (getOtherMetastaticAnatomicSite() != null) {
            _hashCode += getOtherMetastaticAnatomicSite().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BreastCancerTNMFinding.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caTissueCAE.caBIG/1.0/edu.pitt.cabig.cae.domain.breast", "BreastCancerTNMFinding"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("numberLymphNodesExamined");
        attrField.setXmlName(new javax.xml.namespace.QName("", "numberLymphNodesExamined"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("numberLymphNodesInvolved");
        attrField.setXmlName(new javax.xml.namespace.QName("", "numberLymphNodesInvolved"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("metastasisAnatomicSite");
        attrField.setXmlName(new javax.xml.namespace.QName("", "metastasisAnatomicSite"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("otherMetastaticAnatomicSite");
        attrField.setXmlName(new javax.xml.namespace.QName("", "otherMetastaticAnatomicSite"));
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
