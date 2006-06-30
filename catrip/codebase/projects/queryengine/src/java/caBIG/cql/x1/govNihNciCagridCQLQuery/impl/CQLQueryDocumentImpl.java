/*
 * An XML document type.
 * Localname: CQLQuery
 * Namespace: http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery
 * Java type: caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument
 *
 * Automatically generated - do not modify.
 */
package caBIG.cql.x1.govNihNciCagridCQLQuery.impl;
/**
 * A document containing one CQLQuery(@http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery) element.
 *
 * This is a complex type.
 */
public class CQLQueryDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument
{
    
    public CQLQueryDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CQLQUERY$0 = 
        new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery", "CQLQuery");
    
    
    /**
     * Gets the "CQLQuery" element
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery getCQLQuery()
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery)get_store().find_element_user(CQLQUERY$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CQLQuery" element
     */
    public void setCQLQuery(caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery cqlQuery)
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery)get_store().find_element_user(CQLQUERY$0, 0);
            if (target == null)
            {
                target = (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery)get_store().add_element_user(CQLQUERY$0);
            }
            target.set(cqlQuery);
        }
    }
    
    /**
     * Appends and returns a new empty "CQLQuery" element
     */
    public caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery addNewCQLQuery()
    {
        synchronized (monitor())
        {
            check_orphaned();
            caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery target = null;
            target = (caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery)get_store().add_element_user(CQLQUERY$0);
            return target;
        }
    }
    /**
     * An XML CQLQuery(@http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery).
     *
     * This is a complex type.
     */
    public static class CQLQueryImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument.CQLQuery
    {
        
        public CQLQueryImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName TARGET$0 = 
            new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery", "Target");
        
        
        /**
         * Gets the "Target" element
         */
        public caBIG.cql.x1.govNihNciCagridCQLQuery.Object getTarget()
        {
            synchronized (monitor())
            {
                check_orphaned();
                caBIG.cql.x1.govNihNciCagridCQLQuery.Object target = null;
                target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Object)get_store().find_element_user(TARGET$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "Target" element
         */
        public void setTarget(caBIG.cql.x1.govNihNciCagridCQLQuery.Object targetValue)
        {
            synchronized (monitor())
            {
                check_orphaned();
                caBIG.cql.x1.govNihNciCagridCQLQuery.Object target = null;
                target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Object)get_store().find_element_user(TARGET$0, 0);
                if (target == null)
                {
                    target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Object)get_store().add_element_user(TARGET$0);
                }
                target.set(targetValue);
            }
        }
        
        /**
         * Appends and returns a new empty "Target" element
         */
        public caBIG.cql.x1.govNihNciCagridCQLQuery.Object addNewTarget()
        {
            synchronized (monitor())
            {
                check_orphaned();
                caBIG.cql.x1.govNihNciCagridCQLQuery.Object target = null;
                target = (caBIG.cql.x1.govNihNciCagridCQLQuery.Object)get_store().add_element_user(TARGET$0);
                return target;
            }
        }
    }
}
