
package edu.duke.cabig.catrip.gui.query;

import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.common.ForeignAssociationBean;
import edu.duke.cabig.catrip.gui.dnd.ClassNode;
import java.util.ArrayList;


// DCQL XML imports..
import gov.nih.nci.catrip.cqlquery.Attribute;
import gov.nih.nci.catrip.cqlquery.LogicalOperator;
import gov.nih.nci.catrip.dcql.Association;
import gov.nih.nci.catrip.dcql.DCQLQueryDocument;
import gov.nih.nci.catrip.dcql.DCQLQueryDocument.DCQLQuery;
import gov.nih.nci.catrip.dcql.ForeignAssociation;
import gov.nih.nci.catrip.dcql.Group;
import gov.nih.nci.catrip.dcql.Join;
import gov.nih.nci.catrip.dcql.JoinCondition;
import gov.nih.nci.catrip.dcql.TargetObject;
import org.apache.xmlbeans.XmlOptions;
// DCQL XML imports..


/**
 * This class generates the DCQL object and XML based on the nodes on the Graph and DCQLRegistry.
 *
 * @author Sanjeev Agarwal
 */
public class DCQLGenerator {
    
    /** Creates a new instance of DCQLGenerator */
    public DCQLGenerator() {
    }
    
    /** Creates a DCQLDocument object. */
    public static DCQLQueryDocument getDCQLDocument(){
        DCQLQueryDocument dcqlDocument = null;
        try{
            
            ClassNode targetNode = DCQLRegistry.getTargetNode();
            if (targetNode == null){
                return null;
            }
            ClassBean targetObjectBean= targetNode.getAssociatedClassObject();
            
            dcqlDocument = DCQLQueryDocument.Factory.newInstance();
            DCQLQuery dcqlQuery = DCQLQuery.Factory.newInstance();
            TargetObject dcqlTargetObject = TargetObject.Factory.newInstance();
            dcqlTargetObject.setName(targetObjectBean.getFullyQualifiedName());
            dcqlTargetObject.setServiceURL(targetObjectBean.getServiceUrl());
            
            buildAssociationGroup(dcqlTargetObject, targetObjectBean);
            
            dcqlQuery.setTargetObject(dcqlTargetObject);
            dcqlDocument.setDCQLQuery(dcqlQuery);
            
        } catch (Exception e){
            e.printStackTrace();
        }
        return dcqlDocument;
    }
    
    /** get the DCQL as XML text. */
    public static String getDCQLText(){
        String txt = "";
        try{
            txt = getDCQLDocument().xmlText();
        } catch (Exception e){
            txt = "Please create a query first.";
        }
        return txt;
    }
    
    /** get the DCQL as formatted XML text. */
    public static String getDCQLText(XmlOptions xmlOptions){
        String txt = "";
        try{
            txt = getDCQLDocument().xmlText(xmlOptions);
        } catch (Exception e){
            txt = "Please create a query first.";
        }
        return txt;
    }
    
    
    
    private static void buildAssociationGroup(gov.nih.nci.catrip.dcql.Object dcqlOuterObject, ClassBean outerObjectBean){//Association
        
        boolean targetObjectHasAttributes = outerObjectBean.hasNotNullAttributes();
        boolean targetObjectHasAssociations = outerObjectBean.hasAssociations();
        boolean targetObjectHasForeignAssociations = outerObjectBean.hasForeignAssociations();
        
        Group dcqlGroup = null;
        if (targetObjectHasAttributes && !(targetObjectHasAssociations || targetObjectHasForeignAssociations)){
            // <editor-fold>   // only attributes are there
            
            ArrayList targetObjectAttributeList = outerObjectBean.getNonNullAttributes();
            // if more than one attribute.. create an internal group...
            if (targetObjectAttributeList.size() > 1){
                // has multiple attcibutes..
                dcqlGroup = dcqlOuterObject.addNewGroup();
                dcqlGroup.setLogicRelation(LogicalOperator.AND);
                
                createAttributesGroup(dcqlGroup, targetObjectAttributeList);
                
            }else {
                // has only 1 attribute
                Attribute dcqlAttribute = dcqlOuterObject.addNewAttribute();
                AttributeBean aBean = (AttributeBean)targetObjectAttributeList.get(0);
                dcqlAttribute.setName(aBean.getAttributeName());
                dcqlAttribute.setPredicate(gov.nih.nci.catrip.cqlquery.Predicate.Enum.forString(aBean.getPredicate()));
                boolean likePredicate = aBean.getPredicate().equalsIgnoreCase("LIKE");
                String attributeValue = aBean.getAttributeValue();
                boolean hasChar = attributeValue.endsWith("%");
                if (likePredicate && !hasChar){
                    dcqlAttribute.setValue(aBean.getAttributeValue()+"%");
                } else {
                    dcqlAttribute.setValue(aBean.getAttributeValue());
                }
            }
            // </editor-fold>  // only attributes are there
            
        } else if (targetObjectHasAttributes && (targetObjectHasAssociations || targetObjectHasForeignAssociations)){
            // <editor-fold>   // attriibutes and associations both are there
            ArrayList targetObjectAttributeList = outerObjectBean.getNonNullAttributes();
            
            dcqlGroup = dcqlOuterObject.addNewGroup();
            dcqlGroup.setLogicRelation(LogicalOperator.AND);
            if (targetObjectAttributeList.size() > 1){
                
                // has multiple attributes.. create an internal group...
                Group gp2 = dcqlGroup.addNewGroup();
                gp2.setLogicRelation(LogicalOperator.AND);
                
                createAttributesGroup(gp2, targetObjectAttributeList);
                
                createAssociations(dcqlGroup, outerObjectBean);
                
            }else {
                // has only 1 attribute
                Attribute dcqlAttribute = dcqlGroup.addNewAttribute();
                AttributeBean aBean = (AttributeBean)targetObjectAttributeList.get(0);
                dcqlAttribute.setName(aBean.getAttributeName());
                dcqlAttribute.setPredicate(gov.nih.nci.catrip.cqlquery.Predicate.Enum.forString(aBean.getPredicate()));
                boolean likePredicate = aBean.getPredicate().equalsIgnoreCase("LIKE");
                String attributeValue = aBean.getAttributeValue();
                boolean hasChar = attributeValue.endsWith("%");
                if (likePredicate && !hasChar){
                    dcqlAttribute.setValue(aBean.getAttributeValue()+"%");
                } else {
                    dcqlAttribute.setValue(aBean.getAttributeValue());
                }
                
                createAssociations(dcqlGroup, outerObjectBean);
                
            }
            // </editor-fold>   // attriibutes and associations both are there
            
            
        }else if (!targetObjectHasAttributes && (targetObjectHasAssociations || targetObjectHasForeignAssociations)){
            // check if the associations are more than 1 than create a AND Group and than add these to the Group.
            int numOfForeignAssociations = outerObjectBean.getForeignAssociations().size();
            int numOfAssociations = outerObjectBean.getAssociations().size();
            int numTotalAssociations = numOfForeignAssociations+numOfAssociations;
            if (numTotalAssociations>1){
                dcqlGroup = dcqlOuterObject.addNewGroup();
                dcqlGroup.setLogicRelation(LogicalOperator.AND);
                createAssociations(dcqlGroup, outerObjectBean);
            }else{
                createAssociations(dcqlOuterObject,outerObjectBean );
            }
        }
        
    }
    
    
    private static void createAttributesGroup(Group outerDcqlGroup, ArrayList targetObjectAttributeList){
        Attribute[] dcqlAttributes = new Attribute[targetObjectAttributeList.size()];
        for (int i = 0; i < targetObjectAttributeList.size(); i++) {
            AttributeBean aBean = (AttributeBean)targetObjectAttributeList.get(i);
            dcqlAttributes[i] = Attribute.Factory.newInstance();
            dcqlAttributes[i].setName(aBean.getAttributeName());
            dcqlAttributes[i].setPredicate(gov.nih.nci.catrip.cqlquery.Predicate.Enum.forString(aBean.getPredicate()));
            boolean likePredicate = aBean.getPredicate().equalsIgnoreCase("LIKE");
            String attributeValue = aBean.getAttributeValue();
            boolean hasChar = attributeValue.endsWith("%");
            if (likePredicate && !hasChar){
                dcqlAttributes[i].setValue(aBean.getAttributeValue()+"%");
            } else {
                dcqlAttributes[i].setValue(aBean.getAttributeValue());
            }
            
        }
        outerDcqlGroup.setAttributeArray(dcqlAttributes);
    }
    
    
    private static void createAssociations(Group outerObject, ClassBean outerObjectBean){
        
        boolean targetObjectHasAssociations = outerObjectBean.hasAssociations();
        boolean targetObjectHasForeignAssociations = outerObjectBean.hasForeignAssociations();
        
        Group outerDcqlGroup = outerObject;
        
        if(targetObjectHasAssociations){
            //- iterate the local associations... recursively.. and create the DCQL.
            ArrayList associationList = outerObjectBean.getAssociations();
            for (int i = 0;i<associationList.size() ;i++){
                
                Association dcqlAssociation = outerDcqlGroup.addNewAssociation(); // adding a local association..
                ClassBean localAssociation = (ClassBean)associationList.get(i);
                dcqlAssociation.setName(localAssociation.getFullyQualifiedName());
                dcqlAssociation.setRoleName( outerObjectBean.getAssociationRoleName(localAssociation.getId()) );
                
                buildAssociationGroup(dcqlAssociation, localAssociation);
                
            }
        }
        
        if(targetObjectHasForeignAssociations){
            //- iterate the foreign associations... recursively.. and create the DCQL.
            ArrayList foreignAssociationList = outerObjectBean.getForeignAssociations();
            for (int i = 0;i<foreignAssociationList.size() ;i++){
                
                ClassBean foreignAssociationLeftBean = ((ForeignAssociationBean)foreignAssociationList.get(i)).getLeftObj();
                ClassBean foreignAssociationRightBean = ((ForeignAssociationBean)foreignAssociationList.get(i)).getRighObj();
                String leftProperty = ((ForeignAssociationBean)foreignAssociationList.get(i)).getLeftProperty();
                String rightProperty = ((ForeignAssociationBean)foreignAssociationList.get(i)).getRightProperty();
                
                ForeignAssociation dcqlForeignAssociation = outerDcqlGroup.addNewForeignAssociation(); // adding a foreign association..
                
                TargetObject dcqlForeignObject = dcqlForeignAssociation.addNewForeignObject();//TargetObject.Factory.newInstance(); // foreign object  //foreignAssociationRightBean
                dcqlForeignObject.setName(foreignAssociationRightBean.getFullyQualifiedName());
                dcqlForeignObject.setServiceURL(foreignAssociationRightBean.getServiceUrl());
                
                JoinCondition dcqlJoinCondition = JoinCondition.Factory.newInstance();
                Join leftJoin = Join.Factory.newInstance();
                leftJoin.setObject(foreignAssociationLeftBean.getFullyQualifiedName());
                leftJoin.setProperty(leftProperty);
                Join rightJoin = Join.Factory.newInstance();
                rightJoin.setObject(foreignAssociationRightBean.getFullyQualifiedName());
                rightJoin.setProperty(rightProperty);
                dcqlJoinCondition.setLeftJoin(leftJoin);dcqlJoinCondition.setRightJoin(rightJoin);
                dcqlForeignAssociation.setJoinCondition(dcqlJoinCondition);
                
                buildAssociationGroup(dcqlForeignObject, foreignAssociationRightBean);
                
            }
            
        }
    }
    
    
    
    private static void createAssociations(gov.nih.nci.catrip.dcql.Object outerObject, ClassBean outerObjectBean){
        
        boolean targetObjectHasAssociations = outerObjectBean.hasAssociations();
        boolean targetObjectHasForeignAssociations = outerObjectBean.hasForeignAssociations();
        
        gov.nih.nci.catrip.dcql.Object outerDcqlObject = outerObject;
        
        if(targetObjectHasAssociations){
            //- iterate the local associations... recursively.. and create the DCQL.
            ArrayList associationList = outerObjectBean.getAssociations();
            for (int i = 0;i<associationList.size() ;i++){
                
                Association dcqlAssociation = outerDcqlObject.addNewAssociation(); // adding a local association..
                ClassBean localAssociation = (ClassBean)associationList.get(i);
                dcqlAssociation.setName(localAssociation.getFullyQualifiedName());
                dcqlAssociation.setRoleName( outerObjectBean.getAssociationRoleName(localAssociation.getId()) );
//                dcqlAssociation.setRoleName("localAssociationRoleName");
                
                buildAssociationGroup(dcqlAssociation, localAssociation);
                
            }
        }
        
        if(targetObjectHasForeignAssociations){
            //- iterate the foreign associations... recursively.. and create the DCQL.
            ArrayList foreignAssociationList = outerObjectBean.getForeignAssociations();
            for (int i = 0;i<foreignAssociationList.size() ;i++){
                
                ClassBean foreignAssociationLeftBean = ((ForeignAssociationBean)foreignAssociationList.get(i)).getLeftObj();
                ClassBean foreignAssociationRightBean = ((ForeignAssociationBean)foreignAssociationList.get(i)).getRighObj();
                String leftProperty = ((ForeignAssociationBean)foreignAssociationList.get(i)).getLeftProperty();
                String rightProperty = ((ForeignAssociationBean)foreignAssociationList.get(i)).getRightProperty();
                
                ForeignAssociation dcqlForeignAssociation = outerDcqlObject.addNewForeignAssociation(); // adding a foreign association..
                
                TargetObject dcqlForeignObject = dcqlForeignAssociation.addNewForeignObject();//TargetObject.Factory.newInstance(); // foreign object  //foreignAssociationRightBean
                dcqlForeignObject.setName(foreignAssociationRightBean.getFullyQualifiedName());
                dcqlForeignObject.setServiceURL(foreignAssociationRightBean.getServiceUrl());
                
                JoinCondition dcqlJoinCondition = JoinCondition.Factory.newInstance();
                Join leftJoin = Join.Factory.newInstance();
                leftJoin.setObject(foreignAssociationLeftBean.getFullyQualifiedName());
                leftJoin.setProperty(leftProperty);
                Join rightJoin = Join.Factory.newInstance();
                rightJoin.setObject(foreignAssociationRightBean.getFullyQualifiedName());
                rightJoin.setProperty(rightProperty);
                dcqlJoinCondition.setLeftJoin(leftJoin);dcqlJoinCondition.setRightJoin(rightJoin);
                dcqlForeignAssociation.setJoinCondition(dcqlJoinCondition);
                
                buildAssociationGroup(dcqlForeignObject, foreignAssociationRightBean);
                
            }
            
            
            
            
        }
    }
    
    
    
    
    
    
}
