/**
 * OtherBreastCancerHistopathologicGrade.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Jun 16, 2005 (10:54:53 EDT) WSDL2Java emitter.
 */

package edu.pitt.cabig.cae.domain.breast;

public class OtherBreastCancerHistopathologicGrade  extends edu.pitt.cabig.cae.domain.general.HistopathologicGrade  implements java.io.Serializable {
    private java.lang.String systemName;  // attribute
    private java.math.BigInteger score;  // attribute
    private java.lang.String scoreMVR;  // attribute
    private java.math.BigInteger mitoticCount;  // attribute

    public OtherBreastCancerHistopathologicGrade() {
    }

    public OtherBreastCancerHistopathologicGrade(
           java.math.BigInteger mitoticCount,
           java.math.BigInteger score,
           java.lang.String scoreMVR,
           java.lang.String systemName) {
           this.systemName = systemName;
           this.score = score;
           this.scoreMVR = scoreMVR;
           this.mitoticCount = mitoticCount;
    }


    /**
     * Gets the systemName value for this OtherBreastCancerHistopathologicGrade.
     * 
     * @return systemName
     */
    public java.lang.String getSystemName() {
        return systemName;
    }


    /**
     * Sets the systemName value for this OtherBreastCancerHistopathologicGrade.
     * 
     * @param systemName
     */
    public void setSystemName(java.lang.String systemName) {
        this.systemName = systemName;
    }


    /**
     * Gets the score value for this OtherBreastCancerHistopathologicGrade.
     * 
     * @return score
     */
    public java.math.BigInteger getScore() {
        return score;
    }


    /**
     * Sets the score value for this OtherBreastCancerHistopathologicGrade.
     * 
     * @param score
     */
    public void setScore(java.math.BigInteger score) {
        this.score = score;
    }


    /**
     * Gets the scoreMVR value for this OtherBreastCancerHistopathologicGrade.
     * 
     * @return scoreMVR
     */
    public java.lang.String getScoreMVR() {
        return scoreMVR;
    }


    /**
     * Sets the scoreMVR value for this OtherBreastCancerHistopathologicGrade.
     * 
     * @param scoreMVR
     */
    public void setScoreMVR(java.lang.String scoreMVR) {
        this.scoreMVR = scoreMVR;
    }


    /**
     * Gets the mitoticCount value for this OtherBreastCancerHistopathologicGrade.
     * 
     * @return mitoticCount
     */
    public java.math.BigInteger getMitoticCount() {
        return mitoticCount;
    }


    /**
     * Sets the mitoticCount value for this OtherBreastCancerHistopathologicGrade.
     * 
     * @param mitoticCount
     */
    public void setMitoticCount(java.math.BigInteger mitoticCount) {
        this.mitoticCount = mitoticCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OtherBreastCancerHistopathologicGrade)) return false;
        OtherBreastCancerHistopathologicGrade other = (OtherBreastCancerHistopathologicGrade) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.systemName==null && other.getSystemName()==null) || 
             (this.systemName!=null &&
              this.systemName.equals(other.getSystemName()))) &&
            ((this.score==null && other.getScore()==null) || 
             (this.score!=null &&
              this.score.equals(other.getScore()))) &&
            ((this.scoreMVR==null && other.getScoreMVR()==null) || 
             (this.scoreMVR!=null &&
              this.scoreMVR.equals(other.getScoreMVR()))) &&
            ((this.mitoticCount==null && other.getMitoticCount()==null) || 
             (this.mitoticCount!=null &&
              this.mitoticCount.equals(other.getMitoticCount())));
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
        if (getSystemName() != null) {
            _hashCode += getSystemName().hashCode();
        }
        if (getScore() != null) {
            _hashCode += getScore().hashCode();
        }
        if (getScoreMVR() != null) {
            _hashCode += getScoreMVR().hashCode();
        }
        if (getMitoticCount() != null) {
            _hashCode += getMitoticCount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OtherBreastCancerHistopathologicGrade.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caTissueCAE.caBIG/1.0/edu.pitt.cabig.cae.domain.breast", "OtherBreastCancerHistopathologicGrade"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("systemName");
        attrField.setXmlName(new javax.xml.namespace.QName("", "systemName"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("score");
        attrField.setXmlName(new javax.xml.namespace.QName("", "score"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("scoreMVR");
        attrField.setXmlName(new javax.xml.namespace.QName("", "scoreMVR"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("mitoticCount");
        attrField.setXmlName(new javax.xml.namespace.QName("", "mitoticCount"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
