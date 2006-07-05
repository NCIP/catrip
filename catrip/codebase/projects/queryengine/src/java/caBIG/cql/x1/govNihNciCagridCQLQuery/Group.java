/*
 * XML Type:  Group
 * Namespace: http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery
 * Java type: caBIG.cql.x1.govNihNciCagridCQLQuery.Group
 *
 * Automatically generated - do not modify.
 */
package caBIG.cql.x1.govNihNciCagridCQLQuery;


/**
 * An XML Group(@http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery).
 *
 * This is a complex type.
 */
public interface Group extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Group.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sB13676C6E423A5FB4A50598EB177036F").resolveHandle("group5356type");
    
    /**
     * Gets array of all "Association" elements
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Association[] getAssociationArray();
    
    /**
     * Gets ith "Association" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Association getAssociationArray(int i);
    
    /**
     * Returns number of "Association" element
     */
    int sizeOfAssociationArray();
    
    /**
     * Sets array of all "Association" element
     */
    void setAssociationArray(caBIG.cql.x1.govNihNciCagridCQLQuery.Association[] associationArray);
    
    /**
     * Sets ith "Association" element
     */
    void setAssociationArray(int i, caBIG.cql.x1.govNihNciCagridCQLQuery.Association association);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Association" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Association insertNewAssociation(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Association" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Association addNewAssociation();
    
    /**
     * Removes the ith "Association" element
     */
    void removeAssociation(int i);
    
    /**
     * Gets array of all "Attribute" elements
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute[] getAttributeArray();
    
    /**
     * Gets ith "Attribute" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute getAttributeArray(int i);
    
    /**
     * Returns number of "Attribute" element
     */
    int sizeOfAttributeArray();
    
    /**
     * Sets array of all "Attribute" element
     */
    void setAttributeArray(caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute[] attributeArray);
    
    /**
     * Sets ith "Attribute" element
     */
    void setAttributeArray(int i, caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute attribute);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Attribute" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute insertNewAttribute(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Attribute" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute addNewAttribute();
    
    /**
     * Removes the ith "Attribute" element
     */
    void removeAttribute(int i);
    
    /**
     * Gets array of all "Group" elements
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Group[] getGroupArray();
    
    /**
     * Gets ith "Group" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Group getGroupArray(int i);
    
    /**
     * Returns number of "Group" element
     */
    int sizeOfGroupArray();
    
    /**
     * Sets array of all "Group" element
     */
    void setGroupArray(caBIG.cql.x1.govNihNciCagridCQLQuery.Group[] groupArray);
    
    /**
     * Sets ith "Group" element
     */
    void setGroupArray(int i, caBIG.cql.x1.govNihNciCagridCQLQuery.Group group);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Group" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Group insertNewGroup(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Group" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Group addNewGroup();
    
    /**
     * Removes the ith "Group" element
     */
    void removeGroup(int i);
    
    /**
     * Gets the "logicRelation" attribute
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator.Enum getLogicRelation();
    
    /**
     * Gets (as xml) the "logicRelation" attribute
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator xgetLogicRelation();
    
    /**
     * Sets the "logicRelation" attribute
     */
    void setLogicRelation(caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator.Enum logicRelation);
    
    /**
     * Sets (as xml) the "logicRelation" attribute
     */
    void xsetLogicRelation(caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator logicRelation);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group newInstance() {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Group parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Group) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}