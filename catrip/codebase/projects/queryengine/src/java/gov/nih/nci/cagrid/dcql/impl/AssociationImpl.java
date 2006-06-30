/*
 * XML Type:  Association
 * Namespace: http://nci.nih.gov/cagrid/DCQL
 * Java type: gov.nih.nci.cagrid.dcql.Association
 *
 * Automatically generated - do not modify.
 */
package gov.nih.nci.cagrid.dcql.impl;
/**
 * An XML Association(@http://nci.nih.gov/cagrid/DCQL).
 *
 * This is a complex type.
 */
public class AssociationImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gov.nih.nci.cagrid.dcql.Association
{
    
    public AssociationImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName OBJECT$0 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "Object");
    private static final javax.xml.namespace.QName ROLENAME$2 = 
        new javax.xml.namespace.QName("", "roleName");
    
    
    /**
     * Gets the "Object" element
     */
    public gov.nih.nci.cagrid.dcql.Object getObject()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Object target = null;
            target = (gov.nih.nci.cagrid.dcql.Object)get_store().find_element_user(OBJECT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Object" element
     */
    public void setObject(gov.nih.nci.cagrid.dcql.Object object)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Object target = null;
            target = (gov.nih.nci.cagrid.dcql.Object)get_store().find_element_user(OBJECT$0, 0);
            if (target == null)
            {
                target = (gov.nih.nci.cagrid.dcql.Object)get_store().add_element_user(OBJECT$0);
            }
            target.set(object);
        }
    }
    
    /**
     * Appends and returns a new empty "Object" element
     */
    public gov.nih.nci.cagrid.dcql.Object addNewObject()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Object target = null;
            target = (gov.nih.nci.cagrid.dcql.Object)get_store().add_element_user(OBJECT$0);
            return target;
        }
    }
    
    /**
     * Gets the "roleName" attribute
     */
    public java.lang.String getRoleName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ROLENAME$2);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "roleName" attribute
     */
    public org.apache.xmlbeans.XmlString xgetRoleName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(ROLENAME$2);
            return target;
        }
    }
    
    /**
     * True if has "roleName" attribute
     */
    public boolean isSetRoleName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(ROLENAME$2) != null;
        }
    }
    
    /**
     * Sets the "roleName" attribute
     */
    public void setRoleName(java.lang.String roleName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ROLENAME$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ROLENAME$2);
            }
            target.setStringValue(roleName);
        }
    }
    
    /**
     * Sets (as xml) the "roleName" attribute
     */
    public void xsetRoleName(org.apache.xmlbeans.XmlString roleName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(ROLENAME$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(ROLENAME$2);
            }
            target.set(roleName);
        }
    }
    
    /**
     * Unsets the "roleName" attribute
     */
    public void unsetRoleName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(ROLENAME$2);
        }
    }
}
