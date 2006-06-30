/*
 * XML Type:  Object
 * Namespace: http://nci.nih.gov/cagrid/DCQL
 * Java type: gov.nih.nci.cagrid.dcql.Object
 *
 * Automatically generated - do not modify.
 */
package gov.nih.nci.cagrid.dcql.impl;
/**
 * An XML Object(@http://nci.nih.gov/cagrid/DCQL).
 *
 * This is a complex type.
 */
public class ObjectImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gov.nih.nci.cagrid.dcql.Object
{
    
    public ObjectImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTE$0 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "Attribute");
    private static final javax.xml.namespace.QName ASSOCIATION$2 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "Association");
    private static final javax.xml.namespace.QName FOREIGNASSOCIATION$4 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "ForeignAssociation");
    private static final javax.xml.namespace.QName GROUP$6 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "Group");
    private static final javax.xml.namespace.QName NAME$8 = 
        new javax.xml.namespace.QName("", "name");
    
    
    /**
     * Gets the "Attribute" element
     */
    public gov.nih.nci.cagrid.dcql.Attribute getAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Attribute target = null;
            target = (gov.nih.nci.cagrid.dcql.Attribute)get_store().find_element_user(ATTRIBUTE$0, 0);
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
    public void setAttribute(gov.nih.nci.cagrid.dcql.Attribute attribute)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Attribute target = null;
            target = (gov.nih.nci.cagrid.dcql.Attribute)get_store().find_element_user(ATTRIBUTE$0, 0);
            if (target == null)
            {
                target = (gov.nih.nci.cagrid.dcql.Attribute)get_store().add_element_user(ATTRIBUTE$0);
            }
            target.set(attribute);
        }
    }
    
    /**
     * Appends and returns a new empty "Attribute" element
     */
    public gov.nih.nci.cagrid.dcql.Attribute addNewAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Attribute target = null;
            target = (gov.nih.nci.cagrid.dcql.Attribute)get_store().add_element_user(ATTRIBUTE$0);
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
    public gov.nih.nci.cagrid.dcql.Association getAssociation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Association target = null;
            target = (gov.nih.nci.cagrid.dcql.Association)get_store().find_element_user(ASSOCIATION$2, 0);
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
    public void setAssociation(gov.nih.nci.cagrid.dcql.Association association)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Association target = null;
            target = (gov.nih.nci.cagrid.dcql.Association)get_store().find_element_user(ASSOCIATION$2, 0);
            if (target == null)
            {
                target = (gov.nih.nci.cagrid.dcql.Association)get_store().add_element_user(ASSOCIATION$2);
            }
            target.set(association);
        }
    }
    
    /**
     * Appends and returns a new empty "Association" element
     */
    public gov.nih.nci.cagrid.dcql.Association addNewAssociation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Association target = null;
            target = (gov.nih.nci.cagrid.dcql.Association)get_store().add_element_user(ASSOCIATION$2);
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
     * Gets the "ForeignAssociation" element
     */
    public gov.nih.nci.cagrid.dcql.ForeignAssociation getForeignAssociation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.ForeignAssociation target = null;
            target = (gov.nih.nci.cagrid.dcql.ForeignAssociation)get_store().find_element_user(FOREIGNASSOCIATION$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "ForeignAssociation" element
     */
    public boolean isSetForeignAssociation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(FOREIGNASSOCIATION$4) != 0;
        }
    }
    
    /**
     * Sets the "ForeignAssociation" element
     */
    public void setForeignAssociation(gov.nih.nci.cagrid.dcql.ForeignAssociation foreignAssociation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.ForeignAssociation target = null;
            target = (gov.nih.nci.cagrid.dcql.ForeignAssociation)get_store().find_element_user(FOREIGNASSOCIATION$4, 0);
            if (target == null)
            {
                target = (gov.nih.nci.cagrid.dcql.ForeignAssociation)get_store().add_element_user(FOREIGNASSOCIATION$4);
            }
            target.set(foreignAssociation);
        }
    }
    
    /**
     * Appends and returns a new empty "ForeignAssociation" element
     */
    public gov.nih.nci.cagrid.dcql.ForeignAssociation addNewForeignAssociation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.ForeignAssociation target = null;
            target = (gov.nih.nci.cagrid.dcql.ForeignAssociation)get_store().add_element_user(FOREIGNASSOCIATION$4);
            return target;
        }
    }
    
    /**
     * Unsets the "ForeignAssociation" element
     */
    public void unsetForeignAssociation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(FOREIGNASSOCIATION$4, 0);
        }
    }
    
    /**
     * Gets the "Group" element
     */
    public gov.nih.nci.cagrid.dcql.Group getGroup()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Group target = null;
            target = (gov.nih.nci.cagrid.dcql.Group)get_store().find_element_user(GROUP$6, 0);
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
            return get_store().count_elements(GROUP$6) != 0;
        }
    }
    
    /**
     * Sets the "Group" element
     */
    public void setGroup(gov.nih.nci.cagrid.dcql.Group group)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Group target = null;
            target = (gov.nih.nci.cagrid.dcql.Group)get_store().find_element_user(GROUP$6, 0);
            if (target == null)
            {
                target = (gov.nih.nci.cagrid.dcql.Group)get_store().add_element_user(GROUP$6);
            }
            target.set(group);
        }
    }
    
    /**
     * Appends and returns a new empty "Group" element
     */
    public gov.nih.nci.cagrid.dcql.Group addNewGroup()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Group target = null;
            target = (gov.nih.nci.cagrid.dcql.Group)get_store().add_element_user(GROUP$6);
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
            get_store().remove_element(GROUP$6, 0);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$8);
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
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$8);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$8);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(NAME$8);
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
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$8);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(NAME$8);
            }
            target.set(name);
        }
    }
}
