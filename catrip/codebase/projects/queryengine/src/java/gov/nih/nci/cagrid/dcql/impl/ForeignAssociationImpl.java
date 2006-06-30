/*
 * XML Type:  ForeignAssociation
 * Namespace: http://nci.nih.gov/cagrid/DCQL
 * Java type: gov.nih.nci.cagrid.dcql.ForeignAssociation
 *
 * Automatically generated - do not modify.
 */
package gov.nih.nci.cagrid.dcql.impl;
/**
 * An XML ForeignAssociation(@http://nci.nih.gov/cagrid/DCQL).
 *
 * This is a complex type.
 */
public class ForeignAssociationImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gov.nih.nci.cagrid.dcql.ForeignAssociation
{
    
    public ForeignAssociationImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName JOINCONDITION$0 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "JoinCondition");
    private static final javax.xml.namespace.QName FOREIGNOBJECT$2 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "ForeignObject");
    private static final javax.xml.namespace.QName ROLENAME$4 = 
        new javax.xml.namespace.QName("", "roleName");
    
    
    /**
     * Gets the "JoinCondition" element
     */
    public gov.nih.nci.cagrid.dcql.JoinCondition getJoinCondition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.JoinCondition target = null;
            target = (gov.nih.nci.cagrid.dcql.JoinCondition)get_store().find_element_user(JOINCONDITION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "JoinCondition" element
     */
    public void setJoinCondition(gov.nih.nci.cagrid.dcql.JoinCondition joinCondition)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.JoinCondition target = null;
            target = (gov.nih.nci.cagrid.dcql.JoinCondition)get_store().find_element_user(JOINCONDITION$0, 0);
            if (target == null)
            {
                target = (gov.nih.nci.cagrid.dcql.JoinCondition)get_store().add_element_user(JOINCONDITION$0);
            }
            target.set(joinCondition);
        }
    }
    
    /**
     * Appends and returns a new empty "JoinCondition" element
     */
    public gov.nih.nci.cagrid.dcql.JoinCondition addNewJoinCondition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.JoinCondition target = null;
            target = (gov.nih.nci.cagrid.dcql.JoinCondition)get_store().add_element_user(JOINCONDITION$0);
            return target;
        }
    }
    
    /**
     * Gets the "ForeignObject" element
     */
    public gov.nih.nci.cagrid.dcql.Object getForeignObject()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Object target = null;
            target = (gov.nih.nci.cagrid.dcql.Object)get_store().find_element_user(FOREIGNOBJECT$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ForeignObject" element
     */
    public void setForeignObject(gov.nih.nci.cagrid.dcql.Object foreignObject)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Object target = null;
            target = (gov.nih.nci.cagrid.dcql.Object)get_store().find_element_user(FOREIGNOBJECT$2, 0);
            if (target == null)
            {
                target = (gov.nih.nci.cagrid.dcql.Object)get_store().add_element_user(FOREIGNOBJECT$2);
            }
            target.set(foreignObject);
        }
    }
    
    /**
     * Appends and returns a new empty "ForeignObject" element
     */
    public gov.nih.nci.cagrid.dcql.Object addNewForeignObject()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Object target = null;
            target = (gov.nih.nci.cagrid.dcql.Object)get_store().add_element_user(FOREIGNOBJECT$2);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ROLENAME$4);
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
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(ROLENAME$4);
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
            return get_store().find_attribute_user(ROLENAME$4) != null;
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ROLENAME$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ROLENAME$4);
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
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(ROLENAME$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(ROLENAME$4);
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
            get_store().remove_attribute(ROLENAME$4);
        }
    }
}
