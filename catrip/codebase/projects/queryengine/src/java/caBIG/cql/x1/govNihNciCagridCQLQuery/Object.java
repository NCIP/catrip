/*
 * XML Type:  Object
 * Namespace: http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery
 * Java type: caBIG.cql.x1.govNihNciCagridCQLQuery.Object
 *
 * Automatically generated - do not modify.
 */
package caBIG.cql.x1.govNihNciCagridCQLQuery;


/**
 * An XML Object(@http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery).
 *
 * This is a complex type.
 */
public interface Object extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Object.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sB13676C6E423A5FB4A50598EB177036F").resolveHandle("objectf7a4type");
    
    /**
     * Gets the "Attribute" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute getAttribute();
    
    /**
     * True if has "Attribute" element
     */
    boolean isSetAttribute();
    
    /**
     * Sets the "Attribute" element
     */
    void setAttribute(caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute attribute);
    
    /**
     * Appends and returns a new empty "Attribute" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute addNewAttribute();
    
    /**
     * Unsets the "Attribute" element
     */
    void unsetAttribute();
    
    /**
     * Gets the "Association" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Association getAssociation();
    
    /**
     * True if has "Association" element
     */
    boolean isSetAssociation();
    
    /**
     * Sets the "Association" element
     */
    void setAssociation(caBIG.cql.x1.govNihNciCagridCQLQuery.Association association);
    
    /**
     * Appends and returns a new empty "Association" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Association addNewAssociation();
    
    /**
     * Unsets the "Association" element
     */
    void unsetAssociation();
    
    /**
     * Gets the "Group" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Group getGroup();
    
    /**
     * True if has "Group" element
     */
    boolean isSetGroup();
    
    /**
     * Sets the "Group" element
     */
    void setGroup(caBIG.cql.x1.govNihNciCagridCQLQuery.Group group);
    
    /**
     * Appends and returns a new empty "Group" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.Group addNewGroup();
    
    /**
     * Unsets the "Group" element
     */
    void unsetGroup();
    
    /**
     * Gets the "name" attribute
     */
    java.lang.String getName();
    
    /**
     * Gets (as xml) the "name" attribute
     */
    org.apache.xmlbeans.XmlString xgetName();
    
    /**
     * Sets the "name" attribute
     */
    void setName(java.lang.String name);
    
    /**
     * Sets (as xml) the "name" attribute
     */
    void xsetName(org.apache.xmlbeans.XmlString name);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object newInstance() {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Object parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Object) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
