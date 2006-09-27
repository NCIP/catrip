/**
 * CaTissueCore_FullResourceProperties.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Mar 03, 2006 (12:17:06 EST) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.catissuecorefull.stubs;

public class CaTissueCore_FullResourceProperties  implements java.io.Serializable {
    private gov.nih.nci.cagrid.metadata.dataservice.DomainModel domainModel;

    public CaTissueCore_FullResourceProperties() {
    }

    public CaTissueCore_FullResourceProperties(
           gov.nih.nci.cagrid.metadata.dataservice.DomainModel domainModel) {
           this.domainModel = domainModel;
    }


    /**
     * Gets the domainModel value for this CaTissueCore_FullResourceProperties.
     * 
     * @return domainModel
     */
    public gov.nih.nci.cagrid.metadata.dataservice.DomainModel getDomainModel() {
        return domainModel;
    }


    /**
     * Sets the domainModel value for this CaTissueCore_FullResourceProperties.
     * 
     * @param domainModel
     */
    public void setDomainModel(gov.nih.nci.cagrid.metadata.dataservice.DomainModel domainModel) {
        this.domainModel = domainModel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CaTissueCore_FullResourceProperties)) return false;
        CaTissueCore_FullResourceProperties other = (CaTissueCore_FullResourceProperties) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.domainModel==null && other.getDomainModel()==null) || 
             (this.domainModel!=null &&
              this.domainModel.equals(other.getDomainModel())));
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
        if (getDomainModel() != null) {
            _hashCode += getDomainModel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CaTissueCore_FullResourceProperties.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://helloworld.cagrid.nci.nih.gov/CaTissueCore_Full", ">CaTissueCore_FullResourceProperties"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("domainModel");
        elemField.setXmlName(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.dataservice", "DomainModel"));
        elemField.setXmlType(new javax.xml.namespace.QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.dataservice", "DomainModel"));
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