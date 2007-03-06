
package edu.duke.cabig.catrip.gui.query;

import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.common.ForeignAssociationBean;
import edu.duke.cabig.catrip.gui.dnd.ClassNode;
import gov.nih.nci.cagrid.cqlquery.Attribute;
import gov.nih.nci.cagrid.cqlquery.LogicalOperator;
import gov.nih.nci.cagrid.cqlquery.Predicate;
import gov.nih.nci.cagrid.cqlquery.ReturnAttributes;
import gov.nih.nci.cagrid.dcql.Association;
import gov.nih.nci.cagrid.dcql.DCQLQuery;
import gov.nih.nci.cagrid.dcql.ForeignAssociation;
import gov.nih.nci.cagrid.dcql.ForeignPredicate;
import gov.nih.nci.cagrid.dcql.Group;
import gov.nih.nci.cagrid.dcql.JoinCondition;
import java.util.ArrayList;
import gov.nih.nci.cagrid.dcql.Object;
import javax.xml.XMLConstants;

import org.apache.xmlbeans.XmlOptions;
import org.globus.wsrf.encoding.ObjectSerializer;
import javax.xml.namespace.QName;



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
    public static DCQLQuery getDCQLDocument(){
        // <editor-fold defaultstate="collapsed" desc=" Code ">
        DCQLQuery dcqlQuery = null;
        
        try{
            
            ClassNode targetNode = DCQLRegistry.getTargetNode();
            if (targetNode == null){
                return null;
            }
            ClassBean targetObjectBean= targetNode.getAssociatedClassObject();
            
            dcqlQuery = new DCQLQuery();
            
            Object dcqlTargetObject = new Object();
            dcqlTargetObject.setName(targetObjectBean.getFullyQualifiedName());
            
            dcqlQuery.setTargetServiceURL(new String[]{targetObjectBean.getServiceUrl()});
            
            buildAssociationGroup(dcqlTargetObject, targetObjectBean);
            
            setTargetObjectReturnedAttributes(dcqlTargetObject, targetObjectBean);
            
            dcqlQuery.setTargetObject(dcqlTargetObject);
            
            
        } catch (Exception e){
            e.printStackTrace();
        }
        return dcqlQuery;
        // </editor-fold>
    }
    
    /** get the DCQL as XML text. */
    public static String getDCQLText(){
        String txt = "";
        try{
            txt = ObjectSerializer.toString(getDCQLDocument(),new QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.dcql","DCQLQuery", XMLConstants.NULL_NS_URI));//xmlText();
        } catch (Exception e){
            txt = "Please create a query first.";//     //
            e.printStackTrace();
        }
        return txt;
    }
    
    /** get the DCQL as formatted XML text. */
    public static String getDCQLText(XmlOptions xmlOptions){
        String txt = "";
        try{
            txt = ObjectSerializer.toString(getDCQLDocument(),new QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.dcql","DCQLQuery", XMLConstants.NULL_NS_URI));//xmlText(xmlOptions);
        } catch (Exception e){
            txt = "Please create a query first.";
            e.printStackTrace();
        }
        return txt;
    }
    
    
    
    private static void buildAssociationGroup(Object dcqlOuterObject, ClassBean outerObjectBean){//Association
        
        boolean targetObjectHasAttributes = outerObjectBean.hasNotNullAttributes();
        boolean targetObjectHasAssociations = outerObjectBean.hasAssociations();
        boolean targetObjectHasForeignAssociations = outerObjectBean.hasForeignAssociations();
        
        Group dcqlGroup = null;
        if (targetObjectHasAttributes && !(targetObjectHasAssociations || targetObjectHasForeignAssociations)){
            // <editor-fold>   // only attributes are there
            
            ArrayList targetObjectAttributeList = outerObjectBean.getNonNullAttributes();
            // sanjeev: if more than one attribute.. create an internal group...
            if (targetObjectAttributeList.size() > 1){
                // has multiple attcibutes..
                dcqlGroup = new Group();
//                dcqlGroup = dcqlOuterObject.addNewGroup();
                dcqlOuterObject.setGroup(dcqlGroup);
                
                dcqlGroup.setLogicRelation(LogicalOperator.AND);
                
                createAttributesGroup(dcqlGroup, targetObjectAttributeList);
                
            }else {
                // sanjeev: has only 1 attribute
                Attribute dcqlAttribute = new Attribute(); //   Attribute dcqlAttribute = dcqlOuterObject.setAttribute();
                dcqlOuterObject.setAttribute(dcqlAttribute);
                
                
                AttributeBean aBean = (AttributeBean)targetObjectAttributeList.get(0);
                dcqlAttribute.setName(aBean.getAttributeName());
                
                dcqlAttribute.setPredicate(Predicate.fromString(aBean.getPredicate()));//gov.nih.nci.catrip.cqlquery.Predicate.Enum.forString(aBean.getPredicate()));
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
            
            dcqlGroup = new Group();//dcqlOuterObject.addNewGroup();
            dcqlOuterObject.setGroup(dcqlGroup);
            
            dcqlGroup.setLogicRelation(LogicalOperator.AND);
            if (targetObjectAttributeList.size() > 1){
                
                // sanjeev: has multiple attributes.. create an internal group...
                Group gp2 = new Group();// dcqlGroup.addNewGroup();
                dcqlGroup.setGroup(new Group[]{gp2});
                gp2.setLogicRelation(LogicalOperator.AND);
                
                createAttributesGroup(gp2, targetObjectAttributeList);
                
                createAssociations(dcqlGroup, outerObjectBean);
                
            }else {
                // sanjeev: has only 1 attribute
                
                Attribute dcqlAttribute = new Attribute();//dcqlGroup.addNewAttribute();
                dcqlGroup.setAttribute(new Attribute[]{dcqlAttribute});
                AttributeBean aBean = (AttributeBean)targetObjectAttributeList.get(0);
                dcqlAttribute.setName(aBean.getAttributeName());
                dcqlAttribute.setPredicate(Predicate.fromString(aBean.getPredicate()));//gov.nih.nci.catrip.cqlquery.Predicate.Enum.forString(aBean.getPredicate()));
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
            // sanjeev: check if the associations are more than 1 than create a AND Group and than add these to the Group.
            int numOfForeignAssociations = outerObjectBean.getForeignAssociations().size();
            int numOfAssociations = outerObjectBean.getAssociations().size();
            int numTotalAssociations = numOfForeignAssociations+numOfAssociations;
            if (numTotalAssociations>1){
                dcqlGroup = new Group();//dcqlOuterObject.addNewGroup();
                dcqlOuterObject.setGroup(dcqlGroup);
                dcqlGroup.setLogicRelation(LogicalOperator.AND);
                createAssociations(dcqlGroup, outerObjectBean);
            }else{
                createAssociations(dcqlOuterObject, outerObjectBean);
            }
        }
        
    }
    
    
    private static void createAttributesGroup(Group outerDcqlGroup, ArrayList targetObjectAttributeList){
        Attribute[] dcqlAttributes = new Attribute[targetObjectAttributeList.size()];
        for (int i = 0; i < targetObjectAttributeList.size(); i++) {
            AttributeBean aBean = (AttributeBean)targetObjectAttributeList.get(i);
            dcqlAttributes[i] = new Attribute();//Attribute.Factory.newInstance();
            dcqlAttributes[i].setName(aBean.getAttributeName());
            dcqlAttributes[i].setPredicate(Predicate.fromString(aBean.getPredicate()));//gov.nih.nci.catrip.cqlquery.Predicate.Enum.forString(aBean.getPredicate()));
            boolean likePredicate = aBean.getPredicate().equalsIgnoreCase("LIKE");
            String attributeValue = aBean.getAttributeValue();
            boolean hasChar = attributeValue.endsWith("%");
            if (likePredicate && !hasChar){
                dcqlAttributes[i].setValue(aBean.getAttributeValue()+"%");
            } else {
                dcqlAttributes[i].setValue(aBean.getAttributeValue());
            }
            
        }
        outerDcqlGroup.setAttribute(dcqlAttributes);//setAttributeArray(dcqlAttributes);
    }
    
    
    private static void createAssociations(Group outerObject, ClassBean outerObjectBean){
        
        boolean targetObjectHasAssociations = outerObjectBean.hasAssociations();
        boolean targetObjectHasForeignAssociations = outerObjectBean.hasForeignAssociations();
        
        Group outerDcqlGroup = outerObject;
        
        if(targetObjectHasAssociations){
            //- sanjeev: iterate the local associations... recursively.. and create the DCQL.
            ArrayList associationList = outerObjectBean.getAssociations();
            
            Association[]   dcqlAssociationArray = new Association[associationList.size()];
            
            for (int i = 0;i<associationList.size() ;i++){
                
                Association dcqlAssociation = new Association();//outerDcqlGroup.addNewAssociation(); // adding a local association..
//                outerDcqlGroup.setAssociation(new Association[]{dcqlAssociation});
                dcqlAssociationArray[i] = dcqlAssociation;
                
                ClassBean localAssociation = (ClassBean)associationList.get(i);
                dcqlAssociation.setName(localAssociation.getFullyQualifiedName());
                dcqlAssociation.setRoleName( outerObjectBean.getAssociationRoleName(localAssociation.getId()) );
                
                buildAssociationGroup(dcqlAssociation, localAssociation);
            }
            
            outerDcqlGroup.setAssociation(dcqlAssociationArray);
            
        }
        
        if(targetObjectHasForeignAssociations){
            //- sanjeev: iterate the foreign associations... recursively.. and create the DCQL.
            ArrayList foreignAssociationList = outerObjectBean.getForeignAssociations();
            
            ForeignAssociation[] dcqlForeignAssociationArray = new ForeignAssociation[foreignAssociationList.size()];
            
            for (int i = 0;i<foreignAssociationList.size() ;i++){
                
                ClassBean foreignAssociationLeftBean = ((ForeignAssociationBean)foreignAssociationList.get(i)).getLeftObj();
                ClassBean foreignAssociationRightBean = ((ForeignAssociationBean)foreignAssociationList.get(i)).getRighObj();
                String leftProperty = ((ForeignAssociationBean)foreignAssociationList.get(i)).getLeftProperty();
                String rightProperty = ((ForeignAssociationBean)foreignAssociationList.get(i)).getRightProperty();
                
                ForeignAssociation dcqlForeignAssociation = new ForeignAssociation();//outerDcqlGroup.addNewForeignAssociation(); // adding a foreign association..
//                outerDcqlGroup.setForeignAssociation(new ForeignAssociation[]{dcqlForeignAssociation});
                dcqlForeignAssociationArray[i] = dcqlForeignAssociation;
                
                Object dcqlForeignObject = new Object() ;//dcqlForeignAssociation.addNewForeignObject();//TargetObject.Factory.newInstance(); // foreign object  //foreignAssociationRightBean
                dcqlForeignAssociation.setForeignObject(dcqlForeignObject);
                
                dcqlForeignObject.setName(foreignAssociationRightBean.getFullyQualifiedName());
                //dcqlForeignObject.setServiceURL(foreignAssociationRightBean.getServiceUrl());
                dcqlForeignAssociation.setTargetServiceURL(foreignAssociationRightBean.getServiceUrl());
                
                JoinCondition dcqlJoinCondition = new JoinCondition();//JoinCondition.Factory.newInstance();
                
                dcqlJoinCondition.setLocalAttributeName(leftProperty);
                dcqlJoinCondition.setForeignAttributeName(rightProperty);
                dcqlJoinCondition.setPredicate(ForeignPredicate.EQUAL_TO);
                
                
                /*
                Join leftJoin = Join.Factory.newInstance();
                leftJoin.setObject(foreignAssociationLeftBean.getFullyQualifiedName());
                leftJoin.setProperty(leftProperty);
                Join rightJoin = Join.Factory.newInstance();
                rightJoin.setObject(foreignAssociationRightBean.getFullyQualifiedName());
                rightJoin.setProperty(rightProperty);
                dcqlJoinCondition.setLeftJoin(leftJoin);dcqlJoinCondition.setRightJoin(rightJoin);
                 */
                dcqlForeignAssociation.setJoinCondition(dcqlJoinCondition);
                
                buildAssociationGroup(dcqlForeignObject, foreignAssociationRightBean);
                
            }
            // set all the foreign associations together here...
            outerDcqlGroup.setForeignAssociation(dcqlForeignAssociationArray);
            
        }
    }
    
    
    
    private static void createAssociations(Object outerObject, ClassBean outerObjectBean){
        
        boolean targetObjectHasAssociations = outerObjectBean.hasAssociations();
        boolean targetObjectHasForeignAssociations = outerObjectBean.hasForeignAssociations();
        
        Object outerDcqlObject = outerObject;
        
        if(targetObjectHasAssociations){
            //- sanjeev: iterate the local associations... recursively.. and create the DCQL.
            ArrayList associationList = outerObjectBean.getAssociations();
            for (int i = 0;i<associationList.size() ;i++){
                
                Association dcqlAssociation = new Association();//outerDcqlObject.addNewAssociation(); // adding a local association..
                outerDcqlObject.setAssociation(dcqlAssociation);
                
                ClassBean localAssociation = (ClassBean)associationList.get(i);
                dcqlAssociation.setName(localAssociation.getFullyQualifiedName());
                dcqlAssociation.setRoleName( outerObjectBean.getAssociationRoleName(localAssociation.getId()) );
//                dcqlAssociation.setRoleName("localAssociationRoleName");
                
                buildAssociationGroup(dcqlAssociation, localAssociation);
                
            }
        }
        
        if(targetObjectHasForeignAssociations){
            //- sanjeev: iterate the foreign associations... recursively.. and create the DCQL.
            ArrayList foreignAssociationList = outerObjectBean.getForeignAssociations();
            
            ForeignAssociation[] dcqlForeignAssociationArray = new ForeignAssociation[foreignAssociationList.size()];
            
            for (int i = 0;i<foreignAssociationList.size() ;i++){
                
                ClassBean foreignAssociationLeftBean = ((ForeignAssociationBean)foreignAssociationList.get(i)).getLeftObj();
                ClassBean foreignAssociationRightBean = ((ForeignAssociationBean)foreignAssociationList.get(i)).getRighObj();
                String leftProperty = ((ForeignAssociationBean)foreignAssociationList.get(i)).getLeftProperty();
                String rightProperty = ((ForeignAssociationBean)foreignAssociationList.get(i)).getRightProperty();
                
                ForeignAssociation dcqlForeignAssociation = new ForeignAssociation();//outerDcqlObject.addNewForeignAssociation(); // adding a foreign association..
                outerDcqlObject.setForeignAssociation(dcqlForeignAssociation);
                
                
                Object dcqlForeignObject = new Object();//dcqlForeignAssociation.addNewForeignObject();//TargetObject.Factory.newInstance(); // foreign object  //foreignAssociationRightBean
                dcqlForeignAssociation.setForeignObject(dcqlForeignObject);
                
                dcqlForeignObject.setName(foreignAssociationRightBean.getFullyQualifiedName());
                //dcqlForeignObject.setServiceURL(foreignAssociationRightBean.getServiceUrl());
                dcqlForeignAssociation.setTargetServiceURL(foreignAssociationRightBean.getServiceUrl());
                
                JoinCondition dcqlJoinCondition = new JoinCondition();//JoinCondition.Factory.newInstance();
                
                dcqlJoinCondition.setLocalAttributeName(leftProperty);
                dcqlJoinCondition.setForeignAttributeName(rightProperty);
                dcqlJoinCondition.setPredicate(ForeignPredicate.EQUAL_TO);
                
                /*
                Join leftJoin = Join.Factory.newInstance();
                leftJoin.setObject(foreignAssociationLeftBean.getFullyQualifiedName());
                leftJoin.setProperty(leftProperty);
                Join rightJoin = Join.Factory.newInstance();
                rightJoin.setObject(foreignAssociationRightBean.getFullyQualifiedName());
                rightJoin.setProperty(rightProperty);
                dcqlJoinCondition.setLeftJoin(leftJoin);dcqlJoinCondition.setRightJoin(rightJoin);
                 */
                dcqlForeignAssociation.setJoinCondition(dcqlJoinCondition);
                
                buildAssociationGroup(dcqlForeignObject, foreignAssociationRightBean);
                
            }
            
            
            
            
        }
    }
    
    
    
    
    private static void setTargetObjectReturnedAttributes(Object dcqlOuterObject, ClassBean targetBean){
        
        ArrayList atts = targetBean.getAttributes();
        String[] attributes = new String[atts.size()];
            for (int i = 0; i < atts.size(); i++) {
                attributes[i] = ((AttributeBean)atts.get(i)).getAttributeName();
            }
        ReturnAttributes rtAtt = new ReturnAttributes(attributes); 
        dcqlOuterObject.setReturnAttributes(rtAtt);
        
    }
    
    
    
    
    
    
}
