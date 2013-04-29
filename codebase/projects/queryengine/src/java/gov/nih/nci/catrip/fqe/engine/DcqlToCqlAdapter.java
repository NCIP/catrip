/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.catrip.fqe.engine;



public class DcqlToCqlAdapter {
    public DcqlToCqlAdapter() {
    }
    /*
     * updated
    public static gov.nih.nci.cagrid.cqlquery.Attribute getCQLAttribute(caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute dcqlAttribute){
        gov.nih.nci.cagrid.cqlquery.Attribute cqlAttribute = new gov.nih.nci.cagrid.cqlquery.Attribute();
        cqlAttribute.setName(dcqlAttribute.getName());
        cqlAttribute.setValue(dcqlAttribute.getValue());
        cqlAttribute.setPredicate(gov.nih.nci.cagrid.cqlquery.Predicate.fromValue(dcqlAttribute.getPredicate().toString()));
        
        return cqlAttribute;
        //gov.nih.nci.cagrid.cqlquery.Predicate cqlPredicate = gov.nih.nci.cagrid.cqlquery.Predicate.        
    }
    
     public static gov.nih.nci.cagrid.cqlquery.Attribute[] getAttributeArray(caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute[] dcqlAttributeArray ) {
     
         
         gov.nih.nci.cagrid.cqlquery.Attribute[] cqlAttributeArray = new gov.nih.nci.cagrid.cqlquery.Attribute[dcqlAttributeArray.length];

         for (int i=0;i<dcqlAttributeArray.length;i++){
              gov.nih.nci.cagrid.cqlquery.Attribute cqlAttribute = getCQLAttribute((caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute)dcqlAttributeArray[i]);
              cqlAttributeArray[i]=cqlAttribute;
         }
         //cqlGroup.setAssociationArray(cqlAssociationArray);    
         return cqlAttributeArray;
         
     }
     */
}
