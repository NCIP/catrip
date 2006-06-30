/*
 * XML Type:  Join
 * Namespace: http://nci.nih.gov/cagrid/DCQL
 * Java type: gov.nih.nci.cagrid.dcql.Join
 *
 * Automatically generated - do not modify.
 */
package gov.nih.nci.cagrid.dcql.impl;
/**
 * An XML Join(@http://nci.nih.gov/cagrid/DCQL).
 *
 * This is a complex type.
 */
public class JoinImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gov.nih.nci.cagrid.dcql.Join
{
    
    public JoinImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName OBJECT$0 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "Object");
    private static final javax.xml.namespace.QName ATTRIBUTE$2 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "Attribute");
    
    
    /**
     * Gets the "Object" element
     */
    public java.lang.String getObject()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(OBJECT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Object" element
     */
    public org.apache.xmlbeans.XmlString xgetObject()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(OBJECT$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Object" element
     */
    public void setObject(java.lang.String object)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(OBJECT$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(OBJECT$0);
            }
            target.setStringValue(object);
        }
    }
    
    /**
     * Sets (as xml) the "Object" element
     */
    public void xsetObject(org.apache.xmlbeans.XmlString object)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(OBJECT$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(OBJECT$0);
            }
            target.set(object);
        }
    }
    
    /**
     * Gets the "Attribute" element
     */
    public java.lang.String getAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ATTRIBUTE$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Attribute" element
     */
    public org.apache.xmlbeans.XmlString xgetAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ATTRIBUTE$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Attribute" element
     */
    public void setAttribute(java.lang.String attribute)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ATTRIBUTE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ATTRIBUTE$2);
            }
            target.setStringValue(attribute);
        }
    }
    
    /**
     * Sets (as xml) the "Attribute" element
     */
    public void xsetAttribute(org.apache.xmlbeans.XmlString attribute)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ATTRIBUTE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ATTRIBUTE$2);
            }
            target.set(attribute);
        }
    }
}
