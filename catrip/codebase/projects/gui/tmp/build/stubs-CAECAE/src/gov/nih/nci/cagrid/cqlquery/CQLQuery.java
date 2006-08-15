/**
 * CQLQuery.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Jun 16, 2005 (10:54:53 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.cqlquery;

public class CQLQuery  implements java.io.Serializable {
    /** Defines the target data type of a CQL query */
    private gov.nih.nci.cagrid.cqlquery.Object target;

    public CQLQuery() {
    }

    public CQLQuery(
           gov.nih.nci.cagrid.cqlquery.Object target) {
           this.target = target;
    }


    /**
     * Gets the target value for this CQLQuery.
     * 
     * @return target Defines the target data type of a CQL query
     */
    public gov.nih.nci.cagrid.cqlquery.Object getTarget() {
        return target;
    }


    /**
     * Sets the target value for this CQLQuery.
     * 
     * @param target Defines the target data type of a CQL query
     */
    public void setTarget(gov.nih.nci.cagrid.cqlquery.Object target) {
        this.target = target;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CQLQuery)) return false;
        CQLQuery other = (CQLQuery) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.target==null && other.getTarget()==null) || 
             (this.target!=null &&
              this.target.equals(other.getTarget())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getTarget() != null) {
            _hashCode += getTarget().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CQLQuery.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery", ">CQLQuery"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("target");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery", "Target"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery", "Object"));
        typeDesc.addFieldDesc(elemField);
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
