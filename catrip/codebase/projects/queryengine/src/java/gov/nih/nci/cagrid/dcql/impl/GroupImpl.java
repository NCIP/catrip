/*
 * XML Type:  Group
 * Namespace: http://nci.nih.gov/cagrid/DCQL
 * Java type: gov.nih.nci.cagrid.dcql.Group
 *
 * Automatically generated - do not modify.
 */
package gov.nih.nci.cagrid.dcql.impl;
/**
 * An XML Group(@http://nci.nih.gov/cagrid/DCQL).
 *
 * This is a complex type.
 */
public class GroupImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gov.nih.nci.cagrid.dcql.Group
{
    
    public GroupImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ASSOCIATION$0 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "Association");
    private static final javax.xml.namespace.QName ATTRIBUTE$2 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "Attribute");
    private static final javax.xml.namespace.QName GROUP$4 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "Group");
    private static final javax.xml.namespace.QName LOGICRELATION$6 = 
        new javax.xml.namespace.QName("", "logicRelation");
    
    
    /**
     * Gets array of all "Association" elements
     */
    public gov.nih.nci.cagrid.dcql.Association[] getAssociationArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ASSOCIATION$0, targetList);
            gov.nih.nci.cagrid.dcql.Association[] result = new gov.nih.nci.cagrid.dcql.Association[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Association" element
     */
    public gov.nih.nci.cagrid.dcql.Association getAssociationArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Association target = null;
            target = (gov.nih.nci.cagrid.dcql.Association)get_store().find_element_user(ASSOCIATION$0, i);
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
    public void setAssociationArray(gov.nih.nci.cagrid.dcql.Association[] associationArray)
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
    public void setAssociationArray(int i, gov.nih.nci.cagrid.dcql.Association association)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Association target = null;
            target = (gov.nih.nci.cagrid.dcql.Association)get_store().find_element_user(ASSOCIATION$0, i);
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
    public gov.nih.nci.cagrid.dcql.Association insertNewAssociation(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Association target = null;
            target = (gov.nih.nci.cagrid.dcql.Association)get_store().insert_element_user(ASSOCIATION$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Association" element
     */
    public gov.nih.nci.cagrid.dcql.Association addNewAssociation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Association target = null;
            target = (gov.nih.nci.cagrid.dcql.Association)get_store().add_element_user(ASSOCIATION$0);
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
    public gov.nih.nci.cagrid.dcql.Attribute[] getAttributeArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ATTRIBUTE$2, targetList);
            gov.nih.nci.cagrid.dcql.Attribute[] result = new gov.nih.nci.cagrid.dcql.Attribute[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Attribute" element
     */
    public gov.nih.nci.cagrid.dcql.Attribute getAttributeArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Attribute target = null;
            target = (gov.nih.nci.cagrid.dcql.Attribute)get_store().find_element_user(ATTRIBUTE$2, i);
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
    public void setAttributeArray(gov.nih.nci.cagrid.dcql.Attribute[] attributeArray)
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
    public void setAttributeArray(int i, gov.nih.nci.cagrid.dcql.Attribute attribute)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Attribute target = null;
            target = (gov.nih.nci.cagrid.dcql.Attribute)get_store().find_element_user(ATTRIBUTE$2, i);
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
    public gov.nih.nci.cagrid.dcql.Attribute insertNewAttribute(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Attribute target = null;
            target = (gov.nih.nci.cagrid.dcql.Attribute)get_store().insert_element_user(ATTRIBUTE$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Attribute" element
     */
    public gov.nih.nci.cagrid.dcql.Attribute addNewAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Attribute target = null;
            target = (gov.nih.nci.cagrid.dcql.Attribute)get_store().add_element_user(ATTRIBUTE$2);
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
    public gov.nih.nci.cagrid.dcql.Group[] getGroupArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(GROUP$4, targetList);
            gov.nih.nci.cagrid.dcql.Group[] result = new gov.nih.nci.cagrid.dcql.Group[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Group" element
     */
    public gov.nih.nci.cagrid.dcql.Group getGroupArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Group target = null;
            target = (gov.nih.nci.cagrid.dcql.Group)get_store().find_element_user(GROUP$4, i);
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
    public void setGroupArray(gov.nih.nci.cagrid.dcql.Group[] groupArray)
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
    public void setGroupArray(int i, gov.nih.nci.cagrid.dcql.Group group)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Group target = null;
            target = (gov.nih.nci.cagrid.dcql.Group)get_store().find_element_user(GROUP$4, i);
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
    public gov.nih.nci.cagrid.dcql.Group insertNewGroup(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Group target = null;
            target = (gov.nih.nci.cagrid.dcql.Group)get_store().insert_element_user(GROUP$4, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Group" element
     */
    public gov.nih.nci.cagrid.dcql.Group addNewGroup()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Group target = null;
            target = (gov.nih.nci.cagrid.dcql.Group)get_store().add_element_user(GROUP$4);
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
    public gov.nih.nci.cagrid.dcql.LogicalOperator.Enum getLogicRelation()
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
            return (gov.nih.nci.cagrid.dcql.LogicalOperator.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "logicRelation" attribute
     */
    public gov.nih.nci.cagrid.dcql.LogicalOperator xgetLogicRelation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.LogicalOperator target = null;
            target = (gov.nih.nci.cagrid.dcql.LogicalOperator)get_store().find_attribute_user(LOGICRELATION$6);
            return target;
        }
    }
    
    /**
     * Sets the "logicRelation" attribute
     */
    public void setLogicRelation(gov.nih.nci.cagrid.dcql.LogicalOperator.Enum logicRelation)
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
    public void xsetLogicRelation(gov.nih.nci.cagrid.dcql.LogicalOperator logicRelation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.LogicalOperator target = null;
            target = (gov.nih.nci.cagrid.dcql.LogicalOperator)get_store().find_attribute_user(LOGICRELATION$6);
            if (target == null)
            {
                target = (gov.nih.nci.cagrid.dcql.LogicalOperator)get_store().add_attribute_user(LOGICRELATION$6);
            }
            target.set(logicRelation);
        }
    }
}
