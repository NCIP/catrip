/*
 * XML Type:  JoinCondition
 * Namespace: http://nci.nih.gov/cagrid/DCQL
 * Java type: gov.nih.nci.cagrid.dcql.JoinCondition
 *
 * Automatically generated - do not modify.
 */
package gov.nih.nci.cagrid.dcql.impl;
/**
 * An XML JoinCondition(@http://nci.nih.gov/cagrid/DCQL).
 *
 * This is a complex type.
 */
public class JoinConditionImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gov.nih.nci.cagrid.dcql.JoinCondition
{
    
    public JoinConditionImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName LEFTJOIN$0 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "LeftJoin");
    private static final javax.xml.namespace.QName RIGHTJOIN$2 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "RightJoin");
    
    
    /**
     * Gets the "LeftJoin" element
     */
    public gov.nih.nci.cagrid.dcql.Join getLeftJoin()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Join target = null;
            target = (gov.nih.nci.cagrid.dcql.Join)get_store().find_element_user(LEFTJOIN$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "LeftJoin" element
     */
    public void setLeftJoin(gov.nih.nci.cagrid.dcql.Join leftJoin)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Join target = null;
            target = (gov.nih.nci.cagrid.dcql.Join)get_store().find_element_user(LEFTJOIN$0, 0);
            if (target == null)
            {
                target = (gov.nih.nci.cagrid.dcql.Join)get_store().add_element_user(LEFTJOIN$0);
            }
            target.set(leftJoin);
        }
    }
    
    /**
     * Appends and returns a new empty "LeftJoin" element
     */
    public gov.nih.nci.cagrid.dcql.Join addNewLeftJoin()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Join target = null;
            target = (gov.nih.nci.cagrid.dcql.Join)get_store().add_element_user(LEFTJOIN$0);
            return target;
        }
    }
    
    /**
     * Gets the "RightJoin" element
     */
    public gov.nih.nci.cagrid.dcql.Join getRightJoin()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Join target = null;
            target = (gov.nih.nci.cagrid.dcql.Join)get_store().find_element_user(RIGHTJOIN$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "RightJoin" element
     */
    public void setRightJoin(gov.nih.nci.cagrid.dcql.Join rightJoin)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Join target = null;
            target = (gov.nih.nci.cagrid.dcql.Join)get_store().find_element_user(RIGHTJOIN$2, 0);
            if (target == null)
            {
                target = (gov.nih.nci.cagrid.dcql.Join)get_store().add_element_user(RIGHTJOIN$2);
            }
            target.set(rightJoin);
        }
    }
    
    /**
     * Appends and returns a new empty "RightJoin" element
     */
    public gov.nih.nci.cagrid.dcql.Join addNewRightJoin()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.Join target = null;
            target = (gov.nih.nci.cagrid.dcql.Join)get_store().add_element_user(RIGHTJOIN$2);
            return target;
        }
    }
}
