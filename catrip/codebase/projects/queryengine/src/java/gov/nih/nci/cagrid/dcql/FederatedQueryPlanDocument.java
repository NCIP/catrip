/*
 * An XML document type.
 * Localname: FederatedQueryPlan
 * Namespace: http://nci.nih.gov/cagrid/DCQL
 * Java type: gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument
 *
 * Automatically generated - do not modify.
 */
package gov.nih.nci.cagrid.dcql;


/**
 * A document containing one FederatedQueryPlan(@http://nci.nih.gov/cagrid/DCQL) element.
 *
 * This is a complex type.
 */
public interface FederatedQueryPlanDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(FederatedQueryPlanDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sB13676C6E423A5FB4A50598EB177036F").resolveHandle("federatedqueryplan8251doctype");
    
    /**
     * Gets the "FederatedQueryPlan" element
     */
    gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan getFederatedQueryPlan();
    
    /**
     * Sets the "FederatedQueryPlan" element
     */
    void setFederatedQueryPlan(gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan federatedQueryPlan);
    
    /**
     * Appends and returns a new empty "FederatedQueryPlan" element
     */
    gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan addNewFederatedQueryPlan();
    
    /**
     * An XML FederatedQueryPlan(@http://nci.nih.gov/cagrid/DCQL).
     *
     * This is a complex type.
     */
    public interface FederatedQueryPlan extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(FederatedQueryPlan.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sB13676C6E423A5FB4A50598EB177036F").resolveHandle("federatedqueryplanfcfaelemtype");
        
        /**
         * Gets the "TargetObject" element
         */
        gov.nih.nci.cagrid.dcql.Object getTargetObject();
        
        /**
         * Sets the "TargetObject" element
         */
        void setTargetObject(gov.nih.nci.cagrid.dcql.Object targetObject);
        
        /**
         * Appends and returns a new empty "TargetObject" element
         */
        gov.nih.nci.cagrid.dcql.Object addNewTargetObject();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan newInstance() {
              return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument newInstance() {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
