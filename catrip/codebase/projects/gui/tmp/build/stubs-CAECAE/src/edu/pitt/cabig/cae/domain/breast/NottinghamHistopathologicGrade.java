/**
 * NottinghamHistopathologicGrade.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Jun 16, 2005 (10:54:53 EDT) WSDL2Java emitter.
 */

package edu.pitt.cabig.cae.domain.breast;

public class NottinghamHistopathologicGrade  extends edu.pitt.cabig.cae.domain.general.HistopathologicGrade  implements java.io.Serializable {
    private java.math.BigInteger tubuleFormation;  // attribute
    private java.math.BigInteger nuclearPleomorphism;  // attribute
    private java.math.BigInteger mitoticCount;  // attribute
    private java.math.BigInteger totalScore;  // attribute
    private java.lang.String totalScoreMVR;  // attribute

    public NottinghamHistopathologicGrade() {
    }

    public NottinghamHistopathologicGrade(
           java.math.BigInteger mitoticCount,
           java.math.BigInteger nuclearPleomorphism,
           java.math.BigInteger totalScore,
           java.lang.String totalScoreMVR,
           java.math.BigInteger tubuleFormation) {
           this.tubuleFormation = tubuleFormation;
           this.nuclearPleomorphism = nuclearPleomorphism;
           this.mitoticCount = mitoticCount;
           this.totalScore = totalScore;
           this.totalScoreMVR = totalScoreMVR;
    }


    /**
     * Gets the tubuleFormation value for this NottinghamHistopathologicGrade.
     * 
     * @return tubuleFormation
     */
    public java.math.BigInteger getTubuleFormation() {
        return tubuleFormation;
    }


    /**
     * Sets the tubuleFormation value for this NottinghamHistopathologicGrade.
     * 
     * @param tubuleFormation
     */
    public void setTubuleFormation(java.math.BigInteger tubuleFormation) {
        this.tubuleFormation = tubuleFormation;
    }


    /**
     * Gets the nuclearPleomorphism value for this NottinghamHistopathologicGrade.
     * 
     * @return nuclearPleomorphism
     */
    public java.math.BigInteger getNuclearPleomorphism() {
        return nuclearPleomorphism;
    }


    /**
     * Sets the nuclearPleomorphism value for this NottinghamHistopathologicGrade.
     * 
     * @param nuclearPleomorphism
     */
    public void setNuclearPleomorphism(java.math.BigInteger nuclearPleomorphism) {
        this.nuclearPleomorphism = nuclearPleomorphism;
    }


    /**
     * Gets the mitoticCount value for this NottinghamHistopathologicGrade.
     * 
     * @return mitoticCount
     */
    public java.math.BigInteger getMitoticCount() {
        return mitoticCount;
    }


    /**
     * Sets the mitoticCount value for this NottinghamHistopathologicGrade.
     * 
     * @param mitoticCount
     */
    public void setMitoticCount(java.math.BigInteger mitoticCount) {
        this.mitoticCount = mitoticCount;
    }


    /**
     * Gets the totalScore value for this NottinghamHistopathologicGrade.
     * 
     * @return totalScore
     */
    public java.math.BigInteger getTotalScore() {
        return totalScore;
    }


    /**
     * Sets the totalScore value for this NottinghamHistopathologicGrade.
     * 
     * @param totalScore
     */
    public void setTotalScore(java.math.BigInteger totalScore) {
        this.totalScore = totalScore;
    }


    /**
     * Gets the totalScoreMVR value for this NottinghamHistopathologicGrade.
     * 
     * @return totalScoreMVR
     */
    public java.lang.String getTotalScoreMVR() {
        return totalScoreMVR;
    }


    /**
     * Sets the totalScoreMVR value for this NottinghamHistopathologicGrade.
     * 
     * @param totalScoreMVR
     */
    public void setTotalScoreMVR(java.lang.String totalScoreMVR) {
        this.totalScoreMVR = totalScoreMVR;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NottinghamHistopathologicGrade)) return false;
        NottinghamHistopathologicGrade other = (NottinghamHistopathologicGrade) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.tubuleFormation==null && other.getTubuleFormation()==null) || 
             (this.tubuleFormation!=null &&
              this.tubuleFormation.equals(other.getTubuleFormation()))) &&
            ((this.nuclearPleomorphism==null && other.getNuclearPleomorphism()==null) || 
             (this.nuclearPleomorphism!=null &&
              this.nuclearPleomorphism.equals(other.getNuclearPleomorphism()))) &&
            ((this.mitoticCount==null && other.getMitoticCount()==null) || 
             (this.mitoticCount!=null &&
              this.mitoticCount.equals(other.getMitoticCount()))) &&
            ((this.totalScore==null && other.getTotalScore()==null) || 
             (this.totalScore!=null &&
              this.totalScore.equals(other.getTotalScore()))) &&
            ((this.totalScoreMVR==null && other.getTotalScoreMVR()==null) || 
             (this.totalScoreMVR!=null &&
              this.totalScoreMVR.equals(other.getTotalScoreMVR())));
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
        if (getTubuleFormation() != null) {
            _hashCode += getTubuleFormation().hashCode();
        }
        if (getNuclearPleomorphism() != null) {
            _hashCode += getNuclearPleomorphism().hashCode();
        }
        if (getMitoticCount() != null) {
            _hashCode += getMitoticCount().hashCode();
        }
        if (getTotalScore() != null) {
            _hashCode += getTotalScore().hashCode();
        }
        if (getTotalScoreMVR() != null) {
            _hashCode += getTotalScoreMVR().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NottinghamHistopathologicGrade.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caTissueCAE.caBIG/1.0/edu.pitt.cabig.cae.domain.breast", "NottinghamHistopathologicGrade"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("tubuleFormation");
        attrField.setXmlName(new javax.xml.namespace.QName("", "tubuleFormation"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("nuclearPleomorphism");
        attrField.setXmlName(new javax.xml.namespace.QName("", "nuclearPleomorphism"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("mitoticCount");
        attrField.setXmlName(new javax.xml.namespace.QName("", "mitoticCount"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("totalScore");
        attrField.setXmlName(new javax.xml.namespace.QName("", "totalScore"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("totalScoreMVR");
        attrField.setXmlName(new javax.xml.namespace.QName("", "totalScoreMVR"));
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
