/*
 * XML Type:  Group
 * Namespace: http://nci.nih.gov/cagrid/DCQL
 * Java type: gov.nih.nci.cagrid.dcql.Group
 *
 * Automatically generated - do not modify.
 */
package gov.nih.nci.cagrid.dcql;


/**
 * An XML Group(@http://nci.nih.gov/cagrid/DCQL).
 *
 * This is a complex type.
 */
public interface Group extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Group.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sB13676C6E423A5FB4A50598EB177036F").resolveHandle("group4981type");
    
    /**
     * Gets array of all "Association" elements
     */
    gov.nih.nci.cagrid.dcql.Association[] getAssociationArray();
    
    /**
     * Gets ith "Association" element
     */
    gov.nih.nci.cagrid.dcql.Association getAssociationArray(int i);
    
    /**
     * Returns number of "Association" element
     */
    int sizeOfAssociationArray();
    
    /**
     * Sets array of all "Association" element
     */
    void setAssociationArray(gov.nih.nci.cagrid.dcql.Association[] associationArray);
    
    /**
     * Sets ith "Association" element
     */
    void setAssociationArray(int i, gov.nih.nci.cagrid.dcql.Association association);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Association" element
     */
    gov.nih.nci.cagrid.dcql.Association insertNewAssociation(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Association" element
     */
    gov.nih.nci.cagrid.dcql.Association addNewAssociation();
    
    /**
     * Removes the ith "Association" element
     */
    void removeAssociation(int i);
    
    /**
     * Gets array of all "Attribute" elements
     */
    gov.nih.nci.cagrid.dcql.Attribute[] getAttributeArray();
    
    /**
     * Gets ith "Attribute" element
     */
    gov.nih.nci.cagrid.dcql.Attribute getAttributeArray(int i);
    
    /**
     * Returns number of "Attribute" element
     */
    int sizeOfAttributeArray();
    
    /**
     * Sets array of all "Attribute" element
     */
    void setAttributeArray(gov.nih.nci.cagrid.dcql.Attribute[] attributeArray);
    
    /**
     * Sets ith "Attribute" element
     */
    void setAttributeArray(int i, gov.nih.nci.cagrid.dcql.Attribute attribute);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Attribute" element
     */
    gov.nih.nci.cagrid.dcql.Attribute insertNewAttribute(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Attribute" element
     */
    gov.nih.nci.cagrid.dcql.Attribute addNewAttribute();
    
    /**
     * Removes the ith "Attribute" element
     */
    void removeAttribute(int i);
    
    /**
     * Gets array of all "Group" elements
     */
    gov.nih.nci.cagrid.dcql.Group[] getGroupArray();
    
    /**
     * Gets ith "Group" element
     */
    gov.nih.nci.cagrid.dcql.Group getGroupArray(int i);
    
    /**
     * Returns number of "Group" element
     */
    int sizeOfGroupArray();
    
    /**
     * Sets array of all "Group" element
     */
    void setGroupArray(gov.nih.nci.cagrid.dcql.Group[] groupArray);
    
    /**
     * Sets ith "Group" element
     */
    void setGroupArray(int i, gov.nih.nci.cagrid.dcql.Group group);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Group" element
     */
    gov.nih.nci.cagrid.dcql.Group insertNewGroup(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Group" element
     */
    gov.nih.nci.cagrid.dcql.Group addNewGroup();
    
    /**
     * Removes the ith "Group" element
     */
    void removeGroup(int i);
    
    /**
     * Gets the "logicRelation" attribute
     */
    gov.nih.nci.cagrid.dcql.LogicalOperator.Enum getLogicRelation();
    
    /**
     * Gets (as xml) the "logicRelation" attribute
     */
    gov.nih.nci.cagrid.dcql.LogicalOperator xgetLogicRelation();
    
    /**
     * Sets the "logicRelation" attribute
     */
    void setLogicRelation(gov.nih.nci.cagrid.dcql.LogicalOperator.Enum logicRelation);
    
    /**
     * Sets (as xml) the "logicRelation" attribute
     */
    void xsetLogicRelation(gov.nih.nci.cagrid.dcql.LogicalOperator logicRelation);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static gov.nih.nci.cagrid.dcql.Group newInstance() {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.Group newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static gov.nih.nci.cagrid.dcql.Group parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.Group parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static gov.nih.nci.cagrid.dcql.Group parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.Group parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.Group parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.Group parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.Group parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.Group parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.Group parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.Group parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.Group parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.Group parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.Group parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.Group parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static gov.nih.nci.cagrid.dcql.Group parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static gov.nih.nci.cagrid.dcql.Group parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (gov.nih.nci.cagrid.dcql.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
