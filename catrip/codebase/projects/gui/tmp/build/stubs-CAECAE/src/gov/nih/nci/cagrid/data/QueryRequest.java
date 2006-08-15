/**
 * QueryRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Jun 16, 2005 (10:54:53 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.data;

public class QueryRequest  implements java.io.Serializable {
    private gov.nih.nci.cagrid.data.QueryRequestCqlQuery cqlQuery;

    public QueryRequest() {
    }

    public QueryRequest(
           gov.nih.nci.cagrid.data.QueryRequestCqlQuery cqlQuery) {
           this.cqlQuery = cqlQuery;
    }


    /**
     * Gets the cqlQuery value for this QueryRequest.
     * 
     * @return cqlQuery
     */
    public gov.nih.nci.cagrid.data.QueryRequestCqlQuery getCqlQuery() {
        return cqlQuery;
    }


    /**
     * Sets the cqlQuery value for this QueryRequest.
     * 
     * @param cqlQuery
     */
    public void setCqlQuery(gov.nih.nci.cagrid.data.QueryRequestCqlQuery cqlQuery) {
        this.cqlQuery = cqlQuery;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryRequest)) return false;
        QueryRequest other = (QueryRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cqlQuery==null && other.getCqlQuery()==null) || 
             (this.cqlQuery!=null &&
              this.cqlQuery.equals(other.getCqlQuery())));
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
        if (getCqlQuery() != null) {
            _hashCode += getCqlQuery().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QueryRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://gov.nih.nci.cagrid.data/DataService", ">QueryRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cqlQuery");
        elemField.setXmlName(new javax.xml.namespace.QName("http://gov.nih.nci.cagrid.data/DataService", "cqlQuery"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gov.nih.nci.cagrid.data/DataService", ">>QueryRequest>cqlQuery"));
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
