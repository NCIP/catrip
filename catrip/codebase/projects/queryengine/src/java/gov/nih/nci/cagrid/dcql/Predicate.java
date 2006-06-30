/*
 * XML Type:  Predicate
 * Namespace: http://nci.nih.gov/cagrid/DCQL
 * Java type: gov.nih.nci.cagrid.dcql.Predicate
 *
 * Automatically generated - do not modify.
 */
package gov.nih.nci.cagrid.dcql;


/**
 * An XML Predicate(@http://nci.nih.gov/cagrid/DCQL).
 *
 * This is an atomic type that is a restriction of gov.nih.nci.cagrid.dcql.Predicate.
 */
public interface Predicate extends org.apache.xmlbeans.XmlString
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Predicate.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sB13676C6E423A5FB4A50598EB177036F").resolveHandle("predicate02c9type");
    
    org.apache.xmlbeans.StringEnumAbstractBase enumValue();
    void set(org.apache.xmlbeans.StringEnumAbstractBase e);
    
    static final Enum EQUAL_TO = Enum.forString("EQUAL_TO");
    static final Enum NOT_EQUAL_TO = Enum.forString("NOT_EQUAL_TO");
    static final Enum LIKE = Enum.forString("LIKE");
    static final Enum IS_NULL = Enum.forString("IS_NULL");
    static final Enum IS_NOT_NULL = Enum.forString("IS_NOT_NULL");
    static final Enum LESS_THAN = Enum.forString("LESS_THAN");
    static final Enum LESS_THAN_EQUAL_TO = Enum.forString("LESS_THAN_EQUAL_TO");
    static final Enum GREATER_THAN = Enum.forString("GREATER_THAN");
    static final Enum GREATER_THAN_EQUAL_TO = Enum.forString("GREATER_THAN_EQUAL_TO");
    
    static final int INT_EQUAL_TO = Enum.INT_EQUAL_TO;
    static final int INT_NOT_EQUAL_TO = Enum.INT_NOT_EQUAL_TO;
    static final int INT_LIKE = Enum.INT_LIKE;
    static final int INT_IS_NULL = Enum.INT_IS_NULL;
    static final int INT_IS_NOT_NULL = Enum.INT_IS_NOT_NULL;
    static final int INT_LESS_THAN = Enum.INT_LESS_THAN;
    static final int INT_LESS_THAN_EQUAL_TO = Enum.INT_LESS_THAN_EQUAL_TO;
    static final int INT_GREATER_THAN = Enum.INT_GREATER_THAN;
    static final int INT_GREATER_THAN_EQUAL_TO = Enum.INT_GREATER_THAN_EQUAL_TO;
    
    /**
     * Enumeration value class for gov.nih.nci.cagrid.dcql.Predicate.
     * These enum values can be used as follows:
     * <pre>
     * enum.toString(); // returns the string value of the enum
     * enum.intValue(); // returns an int value, useful for switches
     * // e.g., case Enum.INT_EQUAL_TO
     * Enum.forString(s); // returns the enum value for a string
     * Enum.forInt(i); // returns the enum value for an int
     * </pre>
     * Enumeration objects are immutable singleton objects that
     * can be compared using == object equality. They have no
     * public constructor. See the constants defined within this
     * class for all the valid values.
     */
    static final class Enum extends org.apache.xmlbeans.StringEnumAbstractBase
    {
        /**
         * Returns the enum value for a string, or null if none.
         */
        public static Enum forString(java.lang.String s)
            { return (Enum)table.forString(s); }
        /**
         * Returns the enum value corresponding to an int, or null if none.
         */
        public static Enum forInt(int i)
            { return (Enum)table.forInt(i); }
        
        private Enum(java.lang.String s, int i)
            { super(s, i); }
        
        static final int INT_EQUAL_TO = 1;
        static final int INT_NOT_EQUAL_TO = 2;
        static final int INT_LIKE = 3;
        static final int INT_IS_NULL = 4;
        static final int INT_IS_NOT_NULL = 5;
        static final int INT_LESS_THAN = 6;
        static final int INT_LESS_THAN_EQUAL_TO = 7;
        static final int INT_GREATER_THAN = 8;
        static final int INT_GREATER_THAN_EQUAL_TO = 9;
        
        public static final org.apache.xmlbeans.StringEnumAbstractBase.Table table =
            new org.apache.xmlbeans.StringEnumAbstractBase.Table
        (
            new Enum[]
            {
                new Enum("EQUAL_TO", INT_EQUAL_TO),
                new Enum("NOT_EQUAL_TO", INT_NOT_EQUAL_TO),
                new Enum("LIKE", INT_LIKE),
                new Enum("IS_NULL", INT_IS_NULL),
                new Enum("IS_NOT_NULL", INT_IS_NOT_NULL),
                new Enum("LESS_THAN", INT_LESS_THAN),
                new Enum("LESS_THAN_EQUAL_TO", INT_LESS_THAN_EQUAL_TO),
                new Enum("GREATER_THAN", INT_GREATER_THAN),
                new Enum("GREATER_THAN_EQUAL_TO", INT_GREATER_THAN_EQUAL_TO),
            }
        );
        private static final long serialVersionUID = 1L;
        private java.lang.Object readResolve() { return forInt(intValue()); } 
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static gov.nih.nci.cagrid.dcql.Predicate newValue(java.lang.Object obj) {
          return (gov.nih.nci.cagrid.dcql.Predicate) type.newValue( obj ); }
        
        public static gov.nih.nci.cagrid.dcql.Predicate newInstance() {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.Predicate newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static gov.nih.nci.cagrid.dcql.Predicate parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.Predicate parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static gov.nih.nci.cagrid.dcql.Predicate parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.Predicate parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.Predicate parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.Predicate parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.Predicate parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.Predicate parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.Predicate parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.Predicate parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.Predicate parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.Predicate parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static gov.nih.nci.cagrid.dcql.Predicate parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static gov.nih.nci.cagrid.dcql.Predicate parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static gov.nih.nci.cagrid.dcql.Predicate parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static gov.nih.nci.cagrid.dcql.Predicate parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (gov.nih.nci.cagrid.dcql.Predicate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
