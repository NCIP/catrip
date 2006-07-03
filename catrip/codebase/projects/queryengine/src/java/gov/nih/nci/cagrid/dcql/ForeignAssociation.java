/*
 * XML Type:  ForeignAssociation
 * Namespace: http://nci.nih.gov/cagrid/DCQL
 * Java type: gov.nih.nci.cagrid.dcql.ForeignAssociation
 *
 * Automatically generated - do not modify.
 */
package gov.nih.nci.cagrid.dcql;


/**
 * An XML ForeignAssociation(@http://nci.nih.gov/cagrid/DCQL).
 *
 * This is a complex type.
 */
public interface ForeignAssociation extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ForeignAssociation.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s7412972FF10F5B966C04BA3A81CC6A02").resolveHandle("foreignassociation11c1type");
    
    /**
     * Gets the "JoinCondition" element
     */
    gov.nih.nci.cagrid.dcql.JoinCondition getJoinCondition();
    
    /**
     * Sets the "JoinCondition" element
     */
    void setJoinCondition(gov.nih.nci.cagrid.dcql.JoinCondition joinCondition);
    
    /**
     * Appends and returns a new empty "JoinCondition" element
     */
    gov.nih.nci.cagrid.dcql.JoinCondition addNewJoinCondition();
    
    /**
     * Gets the "ForeignObject" element
     */
    gov.nih.nci.cagrid.dcql.Object getForeignObject();
    
    /**
     * Sets the "ForeignObject" element
     */
    void setForeignObject(gov.nih.nci.cagrid.dcql.Object foreignObject);
    
    /**
     * Appends and returns a new empty "ForeignObject" element
     */
    gov.nih.nci.cagrid.dcql.Object addNewForeignObject();
    
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
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation newInstance() {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static gov.nih.nci.cagrid.dcql.ForeignAssociation parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (gov.nih.nci.cagrid.dcql.ForeignAssociation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
