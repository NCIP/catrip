/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * DCQLParseHelper.java
 *
 * Created on February 23, 2007, 9:33 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gov.nih.nci.cagrid.fqp.tools;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.dcql.DCQLQuery;
import gov.nih.nci.cagrid.dcql.ForeignAssociation;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author srini
 */
public class DCQLParseHelper {
    private static Map roleClassMap = new HashMap();
    
    /** Creates a new instance of DCQLParseHelper */
    public DCQLParseHelper() {
    }
    
    /**
     *  Load the class names for all the role names . Need to look up for class names based on role names 
     *  Result XML is based on role names so , we need class names . 
    **/
    
    public static Map loadRoleClassMap(DCQLQuery dcql){
        gov.nih.nci.cagrid.dcql.Object targetObj = dcql.getTargetObject();
        processTragetObject(targetObj);
        return roleClassMap;
        
    }
    private static void processTragetObject(gov.nih.nci.cagrid.dcql.Object targetObj) {
        
        if ( targetObj.getForeignAssociation() != null ) {
            processForeignAssociation(targetObj.getForeignAssociation());
        }
        
        if (targetObj.getGroup() != null ) {
            processGroup(targetObj.getGroup());
            
        }
        if (targetObj.getAssociation() != null ) {
            processAssociation(targetObj.getAssociation());
        }
    }
    
    private static void processGroup(gov.nih.nci.cagrid.dcql.Group group){
        
        if ( group.getForeignAssociation() != null ) {
            gov.nih.nci.cagrid.dcql.ForeignAssociation[]  faList = group.getForeignAssociation();
            for (int i=0;i<faList.length;i++) {
                processForeignAssociation(faList[i]);
            }
        }
        
        if (group.getGroup() != null) {
            gov.nih.nci.cagrid.dcql.Group[]  groupList = group.getGroup();
            for (int i=0;i<groupList.length;i++) {
                processGroup(groupList[i]);
            }
        }
        
        
        if (group.getAssociation() != null) {
            gov.nih.nci.cagrid.dcql.Association[]  assocList = group.getAssociation();
            for (int i=0;i<assocList.length;i++) {
                processAssociation(assocList[i]);
            }
            
        }
        
    }
    
    private static void processAssociation(gov.nih.nci.cagrid.dcql.Association  association) {
        roleClassMap.put(association.getRoleName(),association.getName());
        
        if ( association.getForeignAssociation() != null ) {
            
            processForeignAssociation(association.getForeignAssociation());
        }
        
        if (association.getAssociation() != null ) {
            processAssociation(association.getAssociation());
        }
        if (association.getGroup() != null) {
            processGroup(association.getGroup());
        }
    }
    
    private static void processForeignAssociation(ForeignAssociation fa){
        
        gov.nih.nci.cagrid.dcql.Object fObject = fa.getForeignObject();
        
        processTragetObject(fObject);
        
    }
    
    /**
     * Check if the return attributes are requested in CQL query . 
     **/
    
    public static boolean checkReturnAttributes(CQLQuery query) {
        boolean returnAttributes  = false;
        gov.nih.nci.cagrid.cqlquery.Object targetObj = query.getTarget();
        
        
        if (targetObj.getGroup() != null ) {
            returnAttributes  = inspectGroupForReturnAttributes(targetObj.getGroup());            
        }
        
        if (targetObj.getAssociation() != null ) {
            returnAttributes = inspectAssociationForReturnAttributes(targetObj.getAssociation());
        }
        return returnAttributes;
    }
    
    private static boolean inspectGroupForReturnAttributes(gov.nih.nci.cagrid.cqlquery.Group group){
        boolean returnAttributes = false;
        
        if (group.getGroup() != null) {
            gov.nih.nci.cagrid.cqlquery.Group[]  groupList = group.getGroup();
            for (int i=0;i<groupList.length;i++) {
                returnAttributes = inspectGroupForReturnAttributes(groupList[i]);
                if (returnAttributes) return returnAttributes;
            }
        }
        
        
        if (group.getAssociation() != null) {
            gov.nih.nci.cagrid.cqlquery.Association[]  assocList = group.getAssociation();
            for (int i=0;i<assocList.length;i++) {
                returnAttributes = inspectAssociationForReturnAttributes(assocList[i]);
                if (returnAttributes) return returnAttributes;
            }            
        }    
        
        return returnAttributes;
    }
    
    private static boolean inspectAssociationForReturnAttributes(gov.nih.nci.cagrid.cqlquery.Association  association) {
        boolean returnAttributes = false;
        if (association.getReturnAttributes() != null ) {
            return true;
        }
        
        if (association.getAssociation() != null ) {
            returnAttributes = inspectAssociationForReturnAttributes(association.getAssociation());
            if (returnAttributes) return returnAttributes;
        }
        if (association.getGroup() != null) {
            returnAttributes = inspectGroupForReturnAttributes(association.getGroup());
            if (returnAttributes) return returnAttributes;
        }
        
        return returnAttributes;
    }
    
 
}
