/**
 * InvasiveBreastCarcinoma.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Jun 16, 2005 (10:54:53 EDT) WSDL2Java emitter.
 */

package edu.pitt.cabig.cae.domain.breast;

public class InvasiveBreastCarcinoma  extends edu.pitt.cabig.cae.domain.general.Neoplasm  implements java.io.Serializable {
    private java.lang.String location;  // attribute
    private java.lang.String locationMVR;  // attribute
    private java.lang.String venousLymphaticInvasion;  // attribute
    private java.lang.String microcalcificationLocation;  // attribute

    public InvasiveBreastCarcinoma() {
    }

    public InvasiveBreastCarcinoma(
           java.lang.String location,
           java.lang.String locationMVR,
           java.lang.String microcalcificationLocation,
           java.lang.String venousLymphaticInvasion) {
           this.location = location;
           this.locationMVR = locationMVR;
           this.venousLymphaticInvasion = venousLymphaticInvasion;
           this.microcalcificationLocation = microcalcificationLocation;
    }


    /**
     * Gets the location value for this InvasiveBreastCarcinoma.
     * 
     * @return location
     */
    public java.lang.String getLocation() {
        return location;
    }


    /**
     * Sets the location value for this InvasiveBreastCarcinoma.
     * 
     * @param location
     */
    public void setLocation(java.lang.String location) {
        this.location = location;
    }


    /**
     * Gets the locationMVR value for this InvasiveBreastCarcinoma.
     * 
     * @return locationMVR
     */
    public java.lang.String getLocationMVR() {
        return locationMVR;
    }


    /**
     * Sets the locationMVR value for this InvasiveBreastCarcinoma.
     * 
     * @param locationMVR
     */
    public void setLocationMVR(java.lang.String locationMVR) {
        this.locationMVR = locationMVR;
    }


    /**
     * Gets the venousLymphaticInvasion value for this InvasiveBreastCarcinoma.
     * 
     * @return venousLymphaticInvasion
     */
    public java.lang.String getVenousLymphaticInvasion() {
        return venousLymphaticInvasion;
    }


    /**
     * Sets the venousLymphaticInvasion value for this InvasiveBreastCarcinoma.
     * 
     * @param venousLymphaticInvasion
     */
    public void setVenousLymphaticInvasion(java.lang.String venousLymphaticInvasion) {
        this.venousLymphaticInvasion = venousLymphaticInvasion;
    }


    /**
     * Gets the microcalcificationLocation value for this InvasiveBreastCarcinoma.
     * 
     * @return microcalcificationLocation
     */
    public java.lang.String getMicrocalcificationLocation() {
        return microcalcificationLocation;
    }


    /**
     * Sets the microcalcificationLocation value for this InvasiveBreastCarcinoma.
     * 
     * @param microcalcificationLocation
     */
    public void setMicrocalcificationLocation(java.lang.String microcalcificationLocation) {
        this.microcalcificationLocation = microcalcificationLocation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InvasiveBreastCarcinoma)) return false;
        InvasiveBreastCarcinoma other = (InvasiveBreastCarcinoma) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.location==null && other.getLocation()==null) || 
             (this.location!=null &&
              this.location.equals(other.getLocation()))) &&
            ((this.locationMVR==null && other.getLocationMVR()==null) || 
             (this.locationMVR!=null &&
              this.locationMVR.equals(other.getLocationMVR()))) &&
            ((this.venousLymphaticInvasion==null && other.getVenousLymphaticInvasion()==null) || 
             (this.venousLymphaticInvasion!=null &&
              this.venousLymphaticInvasion.equals(other.getVenousLymphaticInvasion()))) &&
            ((this.microcalcificationLocation==null && other.getMicrocalcificationLocation()==null) || 
             (this.microcalcificationLocation!=null &&
              this.microcalcificationLocation.equals(other.getMicrocalcificationLocation())));
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
        if (getLocation() != null) {
            _hashCode += getLocation().hashCode();
        }
        if (getLocationMVR() != null) {
            _hashCode += getLocationMVR().hashCode();
        }
        if (getVenousLymphaticInvasion() != null) {
            _hashCode += getVenousLymphaticInvasion().hashCode();
        }
        if (getMicrocalcificationLocation() != null) {
            _hashCode += getMicrocalcificationLocation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InvasiveBreastCarcinoma.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("gme://caTissueCAE.caBIG/1.0/edu.pitt.cabig.cae.domain.breast", "InvasiveBreastCarcinoma"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("location");
        attrField.setXmlName(new javax.xml.namespace.QName("", "location"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("locationMVR");
        attrField.setXmlName(new javax.xml.namespace.QName("", "locationMVR"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("venousLymphaticInvasion");
        attrField.setXmlName(new javax.xml.namespace.QName("", "venousLymphaticInvasion"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("microcalcificationLocation");
        attrField.setXmlName(new javax.xml.namespace.QName("", "microcalcificationLocation"));
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
