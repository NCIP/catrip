/*
 * XML Type:  Association
 * Namespace: http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery
 * Java type: caBIG.cql.x1.govNihNciCagridCQLQuery.Association
 *
 * Automatically generated - do not modify.
 */
package caBIG.cql.x1.govNihNciCagridCQLQuery.impl;
/**
 * An XML Association(@http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery).
 *
 * This is a complex type.
 */
public class AssociationImpl extends caBIG.cql.x1.govNihNciCagridCQLQuery.impl.ObjectImpl implements caBIG.cql.x1.govNihNciCagridCQLQuery.Association
{
    
    public AssociationImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ROLENAME$0 = 
        new javax.xml.namespace.QName("", "roleName");
    
    
    /**
     * Gets the "roleName" attribute
     */
    public java.lang.String getRoleName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ROLENAME$0);
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
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(ROLENAME$0);
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
            return get_store().find_attribute_user(ROLENAME$0) != null;
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ROLENAME$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ROLENAME$0);
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
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(ROLENAME$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(ROLENAME$0);
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
            get_store().remove_attribute(ROLENAME$0);
        }
    }
}
