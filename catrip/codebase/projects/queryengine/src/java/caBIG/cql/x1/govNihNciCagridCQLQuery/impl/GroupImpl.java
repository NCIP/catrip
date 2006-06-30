/*
 * XML Type:  Group
 * Namespace: http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery
 * Java type: caBIG.cql.x1.govNihNciCagridCQLQuery.Group
 *
 * Automatically generated - do not modify.
 */
package caBIG.cql.x1.govNihNciCagridCQLQuery.impl;
/**
 * An XML Group(@http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery).
 *
 * This is a complex type.
 */
public class GroupImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements caBIG.cql.x1.govNihNciCagridCQLQuery.Group
{
    
    public GroupImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ASSOCIATION$0 = 
        new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery", "Association");
    private static final javax.xml.namespace.QName ATTRIBUTE$2 = 
        new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery", "Attribute");
    private static final javax.xml.namespace.QName GROUP$4 = 
        new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery", "Group");
    private static final javax.xml.namespace.QName LOGICRELATION$6 = 
        new javax.xml.namespace.QName("", "logicRelation");
    
    
    /**
     * Gets array of all "Association" elements
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Association[] getAssociationArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ASSOCIATION$0, targetList);
            caBIG.cql.x1.govNihNciCagridCQLQuery.Association[] result = new caBIG.cql.x1.govNihNciCagridCQLQuery.Association[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Association" element
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Association getAssociationArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Association target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Association)get_store().find_element_user(ASSOCIATION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Association" element
     */
    public int sizeOfAssociationArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ASSOCIATION$0);
        }
    }
    
    /**
     * Sets array of all "Association" element
     */
    public void setAssociationArray(caBIG.cql.x1.govNihNciCagridCQLQuery.Association[] associationArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(associationArray, ASSOCIATION$0);
        }
    }
    
    /**
     * Sets ith "Association" element
     */
    public void setAssociationArray(int i, caBIG.cql.x1.govNihNciCagridCQLQuery.Association association)
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Association target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Association)get_store().find_element_user(ASSOCIATION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(association);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Association" element
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Association insertNewAssociation(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Association target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Association)get_store().insert_element_user(ASSOCIATION$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Association" element
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Association addNewAssociation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Association target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Association)get_store().add_element_user(ASSOCIATION$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Association" element
     */
    public void removeAssociation(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ASSOCIATION$0, i);
        }
    }
    
    /**
     * Gets array of all "Attribute" elements
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute[] getAttributeArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ATTRIBUTE$2, targetList);
            caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute[] result = new caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Attribute" element
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute getAttributeArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute)get_store().find_element_user(ATTRIBUTE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Attribute" element
     */
    public int sizeOfAttributeArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ATTRIBUTE$2);
        }
    }
    
    /**
     * Sets array of all "Attribute" element
     */
    public void setAttributeArray(caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute[] attributeArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(attributeArray, ATTRIBUTE$2);
        }
    }
    
    /**
     * Sets ith "Attribute" element
     */
    public void setAttributeArray(int i, caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute attribute)
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute)get_store().find_element_user(ATTRIBUTE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(attribute);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Attribute" element
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute insertNewAttribute(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute)get_store().insert_element_user(ATTRIBUTE$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Attribute" element
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute addNewAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute)get_store().add_element_user(ATTRIBUTE$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "Attribute" element
     */
    public void removeAttribute(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ATTRIBUTE$2, i);
        }
    }
    
    /**
     * Gets array of all "Group" elements
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Group[] getGroupArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(GROUP$4, targetList);
            caBIG.cql.x1.govNihNciCagridCQLQuery.Group[] result = new caBIG.cql.x1.govNihNciCagridCQLQuery.Group[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Group" element
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Group getGroupArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Group target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Group)get_store().find_element_user(GROUP$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Group" element
     */
    public int sizeOfGroupArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(GROUP$4);
        }
    }
    
    /**
     * Sets array of all "Group" element
     */
    public void setGroupArray(caBIG.cql.x1.govNihNciCagridCQLQuery.Group[] groupArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(groupArray, GROUP$4);
        }
    }
    
    /**
     * Sets ith "Group" element
     */
    public void setGroupArray(int i, caBIG.cql.x1.govNihNciCagridCQLQuery.Group group)
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Group target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Group)get_store().find_element_user(GROUP$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(group);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Group" element
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Group insertNewGroup(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Group target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Group)get_store().insert_element_user(GROUP$4, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Group" element
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
     * Removes the ith "Group" element
     */
    public void removeGroup(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(GROUP$4, i);
        }
    }
    
    /**
     * Gets the "logicRelation" attribute
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator.Enum getLogicRelation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(LOGICRELATION$6);
            if (target == null)
            {
                return null;
            }
            return (caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "logicRelation" attribute
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator xgetLogicRelation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator)get_store().find_attribute_user(LOGICRELATION$6);
            return target;
        }
    }
    
    /**
     * Sets the "logicRelation" attribute
     */
    public void setLogicRelation(caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator.Enum logicRelation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(LOGICRELATION$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(LOGICRELATION$6);
            }
            target.setEnumValue(logicRelation);
        }
    }
    
    /**
     * Sets (as xml) the "logicRelation" attribute
     */
    public void xsetLogicRelation(caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator logicRelation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator)get_store().find_attribute_user(LOGICRELATION$6);
            if (target == null)
            {
                target = (caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator)get_store().add_attribute_user(LOGICRELATION$6);
            }
            target.set(logicRelation);
        }
    }
}
