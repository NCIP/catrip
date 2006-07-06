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
    private static final javax.xml.namespace.QName FOREIGNASSOCIATION$2 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "ForeignAssociation");
    private static final javax.xml.namespace.QName ATTRIBUTE$4 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "Attribute");
    private static final javax.xml.namespace.QName GROUP$6 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "Group");
    private static final javax.xml.namespace.QName LOGICRELATION$8 = 
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
     * Gets array of all "ForeignAssociation" elements
     */
    public gov.nih.nci.cagrid.dcql.ForeignAssociation[] getForeignAssociationArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(FOREIGNASSOCIATION$2, targetList);
            gov.nih.nci.cagrid.dcql.ForeignAssociation[] result = new gov.nih.nci.cagrid.dcql.ForeignAssociation[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ForeignAssociation" element
     */
    public gov.nih.nci.cagrid.dcql.ForeignAssociation getForeignAssociationArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.ForeignAssociation target = null;
            target = (gov.nih.nci.cagrid.dcql.ForeignAssociation)get_store().find_element_user(FOREIGNASSOCIATION$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ForeignAssociation" element
     */
    public int sizeOfForeignAssociationArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(FOREIGNASSOCIATION$2);
        }
    }
    
    /**
     * Sets array of all "ForeignAssociation" element
     */
    public void setForeignAssociationArray(gov.nih.nci.cagrid.dcql.ForeignAssociation[] foreignAssociationArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(foreignAssociationArray, FOREIGNASSOCIATION$2);
        }
    }
    
    /**
     * Sets ith "ForeignAssociation" element
     */
    public void setForeignAssociationArray(int i, gov.nih.nci.cagrid.dcql.ForeignAssociation foreignAssociation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.ForeignAssociation target = null;
            target = (gov.nih.nci.cagrid.dcql.ForeignAssociation)get_store().find_element_user(FOREIGNASSOCIATION$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(foreignAssociation);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ForeignAssociation" element
     */
    public gov.nih.nci.cagrid.dcql.ForeignAssociation insertNewForeignAssociation(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.ForeignAssociation target = null;
            target = (gov.nih.nci.cagrid.dcql.ForeignAssociation)get_store().insert_element_user(FOREIGNASSOCIATION$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ForeignAssociation" element
     */
    public gov.nih.nci.cagrid.dcql.ForeignAssociation addNewForeignAssociation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.ForeignAssociation target = null;
            target = (gov.nih.nci.cagrid.dcql.ForeignAssociation)get_store().add_element_user(FOREIGNASSOCIATION$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "ForeignAssociation" element
     */
    public void removeForeignAssociation(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(FOREIGNASSOCIATION$2, i);
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
            get_store().find_all_element_users(ATTRIBUTE$4, targetList);
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
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute)get_store().find_element_user(ATTRIBUTE$4, i);
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
            return get_store().count_elements(ATTRIBUTE$4);
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
            arraySetterHelper(attributeArray, ATTRIBUTE$4);
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
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute)get_store().find_element_user(ATTRIBUTE$4, i);
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
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute)get_store().insert_element_user(ATTRIBUTE$4, i);
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
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute)get_store().add_element_user(ATTRIBUTE$4);
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
            get_store().remove_element(ATTRIBUTE$4, i);
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
            get_store().find_all_element_users(GROUP$6, targetList);
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
            target = (gov.nih.nci.cagrid.dcql.Group)get_store().find_element_user(GROUP$6, i);
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
            return get_store().count_elements(GROUP$6);
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
            arraySetterHelper(groupArray, GROUP$6);
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
            target = (gov.nih.nci.cagrid.dcql.Group)get_store().find_element_user(GROUP$6, i);
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
            target = (gov.nih.nci.cagrid.dcql.Group)get_store().insert_element_user(GROUP$6, i);
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
            target = (gov.nih.nci.cagrid.dcql.Group)get_store().add_element_user(GROUP$6);
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
            get_store().remove_element(GROUP$6, i);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(LOGICRELATION$8);
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
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator)get_store().find_attribute_user(LOGICRELATION$8);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(LOGICRELATION$8);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(LOGICRELATION$8);
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
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator)get_store().find_attribute_user(LOGICRELATION$8);
            if (target == null)
            {
                target = (caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator)get_store().add_attribute_user(LOGICRELATION$8);
            }
            target.set(logicRelation);
        }
    }
}
