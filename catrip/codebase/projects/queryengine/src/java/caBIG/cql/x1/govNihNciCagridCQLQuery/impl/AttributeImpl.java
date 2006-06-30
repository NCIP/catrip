/*
 * XML Type:  Attribute
 * Namespace: http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery
 * Java type: caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute
 *
 * Automatically generated - do not modify.
 */
package caBIG.cql.x1.govNihNciCagridCQLQuery.impl;
/**
 * An XML Attribute(@http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery).
 *
 * This is a complex type.
 */
public class AttributeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute
{
    
    public AttributeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NAME$0 = 
        new javax.xml.namespace.QName("", "name");
    private static final javax.xml.namespace.QName PREDICATE$2 = 
        new javax.xml.namespace.QName("", "predicate");
    private static final javax.xml.namespace.QName VALUE$4 = 
        new javax.xml.namespace.QName("", "value");
    
    
    /**
     * Gets the "name" attribute
     */
    public java.lang.String getName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$0);
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
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$0);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(NAME$0);
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
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(NAME$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(NAME$0);
            }
            target.set(name);
        }
    }
    
    /**
     * Gets the "predicate" attribute
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Predicate.Enum getPredicate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(PREDICATE$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_default_attribute_value(PREDICATE$2);
            }
            if (target == null)
            {
                return null;
            }
            return (caBIG.cql.x1.govNihNciCagridCQLQuery.Predicate.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "predicate" attribute
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Predicate xgetPredicate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Predicate target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Predicate)get_store().find_attribute_user(PREDICATE$2);
            if (target == null)
            {
                target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Predicate)get_default_attribute_value(PREDICATE$2);
            }
            return target;
        }
    }
    
    /**
     * True if has "predicate" attribute
     */
    public boolean isSetPredicate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(PREDICATE$2) != null;
        }
    }
    
    /**
     * Sets the "predicate" attribute
     */
    public void setPredicate(caBIG.cql.x1.govNihNciCagridCQLQuery.Predicate.Enum predicate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(PREDICATE$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(PREDICATE$2);
            }
            target.setEnumValue(predicate);
        }
    }
    
    /**
     * Sets (as xml) the "predicate" attribute
     */
    public void xsetPredicate(caBIG.cql.x1.govNihNciCagridCQLQuery.Predicate predicate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Predicate target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Predicate)get_store().find_attribute_user(PREDICATE$2);
            if (target == null)
            {
                target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Predicate)get_store().add_attribute_user(PREDICATE$2);
            }
            target.set(predicate);
        }
    }
    
    /**
     * Unsets the "predicate" attribute
     */
    public void unsetPredicate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(PREDICATE$2);
        }
    }
    
    /**
     * Gets the "value" attribute
     */
    public java.lang.String getValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(VALUE$4);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "value" attribute
     */
    public org.apache.xmlbeans.XmlString xgetValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(VALUE$4);
            return target;
        }
    }
    
    /**
     * Sets the "value" attribute
     */
    public void setValue(java.lang.String value)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(VALUE$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(VALUE$4);
            }
            target.setStringValue(value);
        }
    }
    
    /**
     * Sets (as xml) the "value" attribute
     */
    public void xsetValue(org.apache.xmlbeans.XmlString value)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(VALUE$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(VALUE$4);
            }
            target.set(value);
        }
    }
}
