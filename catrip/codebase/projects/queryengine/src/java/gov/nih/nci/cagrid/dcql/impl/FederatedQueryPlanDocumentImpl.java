/*
 * An XML document type.
 * Localname: FederatedQueryPlan
 * Namespace: http://nci.nih.gov/cagrid/DCQL
 * Java type: gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument
 *
 * Automatically generated - do not modify.
 */
package gov.nih.nci.cagrid.dcql.impl;
/**
 * A document containing one FederatedQueryPlan(@http://nci.nih.gov/cagrid/DCQL) element.
 *
 * This is a complex type.
 */
public class FederatedQueryPlanDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument
{
    
    public FederatedQueryPlanDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FEDERATEDQUERYPLAN$0 = 
        new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "FederatedQueryPlan");
    
    
    /**
     * Gets the "FederatedQueryPlan" element
     */
    public gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan getFederatedQueryPlan()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan target = null;
            target = (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan)get_store().find_element_user(FEDERATEDQUERYPLAN$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "FederatedQueryPlan" element
     */
    public void setFederatedQueryPlan(gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan federatedQueryPlan)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan target = null;
            target = (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan)get_store().find_element_user(FEDERATEDQUERYPLAN$0, 0);
            if (target == null)
            {
                target = (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan)get_store().add_element_user(FEDERATEDQUERYPLAN$0);
            }
            target.set(federatedQueryPlan);
        }
    }
    
    /**
     * Appends and returns a new empty "FederatedQueryPlan" element
     */
    public gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan addNewFederatedQueryPlan()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan target = null;
            target = (gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan)get_store().add_element_user(FEDERATEDQUERYPLAN$0);
            return target;
        }
    }
    /**
     * An XML FederatedQueryPlan(@http://nci.nih.gov/cagrid/DCQL).
     *
     * This is a complex type.
     */
    public static class FederatedQueryPlanImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument.FederatedQueryPlan
    {
        
        public FederatedQueryPlanImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName TARGETOBJECT$0 = 
            new javax.xml.namespace.QName("http://nci.nih.gov/cagrid/DCQL", "TargetObject");
        
        
        /**
         * Gets the "TargetObject" element
         */
        public gov.nih.nci.cagrid.dcql.Object getTargetObject()
        {
            synchronized (monitor())
            {
                check_orphaned();
                gov.nih.nci.cagrid.dcql.Object target = null;
                target = (gov.nih.nci.cagrid.dcql.Object)get_store().find_element_user(TARGETOBJECT$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "TargetObject" element
         */
        public void setTargetObject(gov.nih.nci.cagrid.dcql.Object targetObject)
        {
            synchronized (monitor())
            {
                check_orphaned();
                gov.nih.nci.cagrid.dcql.Object target = null;
                target = (gov.nih.nci.cagrid.dcql.Object)get_store().find_element_user(TARGETOBJECT$0, 0);
                if (target == null)
                {
                    target = (gov.nih.nci.cagrid.dcql.Object)get_store().add_element_user(TARGETOBJECT$0);
                }
                target.set(targetObject);
            }
        }
        
        /**
         * Appends and returns a new empty "TargetObject" element
         */
        public gov.nih.nci.cagrid.dcql.Object addNewTargetObject()
        {
            synchronized (monitor())
            {
                check_orphaned();
                gov.nih.nci.cagrid.dcql.Object target = null;
                target = (gov.nih.nci.cagrid.dcql.Object)get_store().add_element_user(TARGETOBJECT$0);
                return target;
            }
        }
    }
}
