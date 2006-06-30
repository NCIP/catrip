/*
 * XML Type:  Association
 * Namespace: http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery
 * Java type: caBIG.cql.x1.govNihNciCagridCQLQuery.Association
 *
 * Automatically generated - do not modify.
 */
package caBIG.cql.x1.govNihNciCagridCQLQuery;


/**
 * An XML Association(@http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery).
 *
 * This is a complex type.
 */
public interface Association extends caBIG.cql.x1.govNihNciCagridCQLQuery.Object
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Association.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sB13676C6E423A5FB4A50598EB177036F").resolveHandle("association0654type");
    
    /**
     * Gets the "roleName" attribute
     */
    java.lang.String getRoleName();
    
    /**
     * Gets (as xml) the "roleName" attribute
     */
    org.apache.xmlbeans.XmlString xgetRoleName();
    
    /**
     * True if has "roleName" attribute
     */
    boolean isSetRoleName();
    
    /**
     * Sets the "roleName" attribute
     */
    void setRoleName(java.lang.String roleName);
    
    /**
     * Sets (as xml) the "roleName" attribute
     */
    void xsetRoleName(org.apache.xmlbeans.XmlString roleName);
    
    /**
     * Unsets the "roleName" attribute
     */
    void unsetRoleName();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association newInstance() {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.Association parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.Association) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
