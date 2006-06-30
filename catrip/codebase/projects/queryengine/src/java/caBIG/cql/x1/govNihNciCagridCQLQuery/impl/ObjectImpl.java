/*
 * XML Type:  Object
 * Namespace: http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery
 * Java type: caBIG.cql.x1.govNihNciCagridCQLQuery.Object
 *
 * Automatically generated - do not modify.
 */
package caBIG.cql.x1.govNihNciCagridCQLQuery.impl;
/**
 * An XML Object(@http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery).
 *
 * This is a complex type.
 */
public class ObjectImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements caBIG.cql.x1.govNihNciCagridCQLQuery.Object
{
    
    public ObjectImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTE$0 = 
        new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery", "Attribute");
    private static final javax.xml.namespace.QName ASSOCIATION$2 = 
        new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery", "Association");
    private static final javax.xml.namespace.QName GROUP$4 = 
        new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery", "Group");
    private static final javax.xml.namespace.QName NAME$6 = 
        new javax.xml.namespace.QName("", "name");
    
    
    /**
     * Gets the "Attribute" element
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute getAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute)get_store().find_element_user(ATTRIBUTE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Attribute" element
     */
    public boolean isSetAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ATTRIBUTE$0) != 0;
        }
    }
    
    /**
     * Sets the "Attribute" element
     */
    public void setAttribute(caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute attribute)
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute)get_store().find_element_user(ATTRIBUTE$0, 0);
            if (target == null)
            {
                target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute)get_store().add_element_user(ATTRIBUTE$0);
            }
            target.set(attribute);
        }
    }
    
    /**
     * Appends and returns a new empty "Attribute" element
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute addNewAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute)get_store().add_element_user(ATTRIBUTE$0);
            return target;
        }
    }
    
    /**
     * Unsets the "Attribute" element
     */
    public void unsetAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ATTRIBUTE$0, 0);
        }
    }
    
    /**
     * Gets the "Association" element
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Association getAssociation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Association target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Association)get_store().find_element_user(ASSOCIATION$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Association" element
     */
    public boolean isSetAssociation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ASSOCIATION$2) != 0;
        }
    }
    
    /**
     * Sets the "Association" element
     */
    public void setAssociation(caBIG.cql.x1.govNihNciCagridCQLQuery.Association association)
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Association target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Association)get_store().find_element_user(ASSOCIATION$2, 0);
            if (target == null)
            {
                target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Association)get_store().add_element_user(ASSOCIATION$2);
            }
            target.set(association);
        }
    }
    
    /**
     * Appends and returns a new empty "Association" element
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Association addNewAssociation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Association target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Association)get_store().add_element_user(ASSOCIATION$2);
            return target;
        }
    }
    
    /**
     * Unsets the "Association" element
     */
    public void unsetAssociation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ASSOCIATION$2, 0);
        }
    }
    
    /**
     * Gets the "Group" element
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Group getGroup()
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Group target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Group)get_store().find_element_user(GROUP$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Group" element
     */
    public boolean isSetGroup()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(GROUP$4) != 0;
        }
    }
    
    /**
     * Sets the "Group" element
     */
    public void setGroup(caBIG.cql.x1.govNihNciCagridCQLQuery.Group group)
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Group target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Group)get_store().find_element_user(GROUP$4, 0);
            if (target == null)
            {
                target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Group)get_store().add_element_user(GROUP$4);
            }
            target.set(group);
        }
    }
    
    /**
     * Appends and returns a new empty "Group" element
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Group addNewGroup()
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Group target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Group)get_store().add_element_user(GROUP$4);
            return target;
        }
    }
    
    /**
     * Unsets the "Group" element
     */
    public void unsetGroup()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(GROUP$4, 0);
        }
    }
    
    /**
     * Gets the "name" attribute
     */
    public java.lang.String getName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$6);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "name" attribute
     */
    public org.apache.xmlbeans.XmlString xgetName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$6);
            return target;
        }
    }
    
    /**
     * Sets the "name" attribute
     */
    public void setName(java.lang.String name)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(NAME$6);
            }
            target.setStringValue(name);
        }
    }
    
    /**
     * Sets (as xml) the "name" attribute
     */
    public void xsetName(org.apache.xmlbeans.XmlString name)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(NAME$6);
            }
            target.set(name);
        }
    }
}
