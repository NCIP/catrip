/**
 * CQLQueryResults.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Jun 16, 2005 (10:54:53 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.cqlresultset;


/**
 * Results from a CQL query executed against a caGrid data service
 */
public class CQLQueryResults  implements java.io.Serializable {
    private gov.nih.nci.cagrid.cqlresultset.CQLObjectResult[] objectResult;
    private gov.nih.nci.cagrid.cqlresultset.CQLIdentifierResult[] identifierResult;
    private gov.nih.nci.cagrid.cqlresultset.CQLAttributeResult[] attributeResult;

    public CQLQueryResults() {
    }

    public CQLQueryResults(
           gov.nih.nci.cagrid.cqlresultset.CQLAttributeResult[] attributeResult,
           gov.nih.nci.cagrid.cqlresultset.CQLIdentifierResult[] identifierResult,
           gov.nih.nci.cagrid.cqlresultset.CQLObjectResult[] objectResult) {
           this.objectResult = objectResult;
           this.identifierResult = identifierResult;
           this.attributeResult = attributeResult;
    }


    /**
     * Gets the objectResult value for this CQLQueryResults.
     * 
     * @return objectResult
     */
    public gov.nih.nci.cagrid.cqlresultset.CQLObjectResult[] getObjectResult() {
        return objectResult;
    }


    /**
     * Sets the objectResult value for this CQLQueryResults.
     * 
     * @param objectResult
     */
    public void setObjectResult(gov.nih.nci.cagrid.cqlresultset.CQLObjectResult[] objectResult) {
        this.objectResult = objectResult;
    }

    public gov.nih.nci.cagrid.cqlresultset.CQLObjectResult getObjectResult(int i) {
        return this.objectResult[i];
    }

    public void setObjectResult(int i, gov.nih.nci.cagrid.cqlresultset.CQLObjectResult _value) {
        this.objectResult[i] = _value;
    }


    /**
     * Gets the identifierResult value for this CQLQueryResults.
     * 
     * @return identifierResult
     */
    public gov.nih.nci.cagrid.cqlresultset.CQLIdentifierResult[] getIdentifierResult() {
        return identifierResult;
    }


    /**
     * Sets the identifierResult value for this CQLQueryResults.
     * 
     * @param identifierResult
     */
    public void setIdentifierResult(gov.nih.nci.cagrid.cqlresultset.CQLIdentifierResult[] identifierResult) {
        this.identifierResult = identifierResult;
    }

    public gov.nih.nci.cagrid.cqlresultset.CQLIdentifierResult getIdentifierResult(int i) {
        return this.identifierResult[i];
    }

    public void setIdentifierResult(int i, gov.nih.nci.cagrid.cqlresultset.CQLIdentifierResult _value) {
        this.identifierResult[i] = _value;
    }


    /**
     * Gets the attributeResult value for this CQLQueryResults.
     * 
     * @return attributeResult
     */
    public gov.nih.nci.cagrid.cqlresultset.CQLAttributeResult[] getAttributeResult() {
        return attributeResult;
    }


    /**
     * Sets the attributeResult value for this CQLQueryResults.
     * 
     * @param attributeResult
     */
    public void setAttributeResult(gov.nih.nci.cagrid.cqlresultset.CQLAttributeResult[] attributeResult) {
        this.attributeResult = attributeResult;
    }

    public gov.nih.nci.cagrid.cqlresultset.CQLAttributeResult getAttributeResult(int i) {
        return this.attributeResult[i];
    }

    public void setAttributeResult(int i, gov.nih.nci.cagrid.cqlresultset.CQLAttributeResult _value) {
        this.attributeResult[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CQLQueryResults)) return false;
        CQLQueryResults other = (CQLQueryResults) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.objectResult==null && other.getObjectResult()==null) || 
             (this.objectResult!=null &&
              java.util.Arrays.equals(this.objectResult, other.getObjectResult()))) &&
            ((this.identifierResult==null && other.getIdentifierResult()==null) || 
             (this.identifierResult!=null &&
              java.util.Arrays.equals(this.identifierResult, other.getIdentifierResult()))) &&
            ((this.attributeResult==null && other.getAttributeResult()==null) || 
             (this.attributeResult!=null &&
              java.util.Arrays.equals(this.attributeResult, other.getAttributeResult())));
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
        if (getObjectResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getObjectResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getObjectResult(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdentifierResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdentifierResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdentifierResult(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAttributeResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttributeResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAttributeResult(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CQLQueryResults.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLResultSet", "CQLQueryResults"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLResultSet", "ObjectResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLResultSet", "CQLObjectResult"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identifierResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLResultSet", "IdentifierResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLResultSet", "CQLIdentifierResult"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attributeResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLResultSet", "AttributeResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLResultSet", "CQLAttributeResult"));
        elemField.setMinOccurs(0);
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
