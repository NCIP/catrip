/*
 * An XML document type.
 * Localname: CQLQuery
 * Namespace: http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery
 * Java type: caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument
 *
 * Automatically generated - do not modify.
 */
package caBIG.cql.x1.govNihNciCagridCQLQuery;


/**
 * A document containing one CQLQuery(@http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery) element.
 *
 * This is a complex type.
 */
public interface CQLQueryDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CQLQueryDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sB13676C6E423A5FB4A50598EB177036F").resolveHandle("cqlquery7809doctype");
    
    /**
     * Gets the "CQLQuery" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery getCQLQuery();
    
    /**
     * Sets the "CQLQuery" element
     */
    void setCQLQuery(caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery cqlQuery);
    
    /**
     * Appends and returns a new empty "CQLQuery" element
     */
    caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery addNewCQLQuery();
    
    /**
     * An XML CQLQuery(@http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery).
     *
     * This is a complex type.
     */
    public interface CQLQuery extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CQLQuery.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sB13676C6E423A5FB4A50598EB177036F").resolveHandle("cqlquery72efelemtype");
        
        /**
         * Gets the "Target" element
         */
        caBIG.cql.x1.govNihNciCagridCQLQuery.Object getTarget();
        
        /**
         * Sets the "Target" element
         */
        void setTarget(caBIG.cql.x1.govNihNciCagridCQLQuery.Object target);
        
        /**
         * Appends and returns a new empty "Target" element
         */
        caBIG.cql.x1.govNihNciCagridCQLQuery.Object addNewTarget();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery newInstance() {
              return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument newInstance() {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
