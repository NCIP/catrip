
package edu.duke.cabig.catrip.gui.query;

import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.common.ClassBeanGroup;
import edu.duke.cabig.catrip.gui.common.ForeignAssociationBean;
import edu.duke.cabig.catrip.gui.dnd.ClassNode;
import edu.duke.cabig.catrip.gui.simplegui.SimpleGuiRegistry;
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
import java.util.List;
import javax.xml.XMLConstants;

import org.apache.xmlbeans.XmlOptions;
import org.globus.wsrf.encoding.ObjectSerializer;
import javax.xml.namespace.QName;



/**
 * This class generates the DCQL object and XML based on the nodes on the Graph and DCQLRegistry.
 *
 * @author Sanjeev Agarwal
 */
public class GroupDCQLGenerator {
    
    /** Creates a new instance of DCQLGenerator */
    public GroupDCQLGenerator() {
    }
    
    /** Creates a DCQLDocument object. */
    public static DCQLQuery getDCQLDocument(){
        
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
            dcqlQuery.setTargetObject(dcqlTargetObject);
            
            
        } catch (Exception e){
            e.printStackTrace();
        }
        return dcqlQuery;
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
        
        // check the groups in target object..
        boolean hasGroups = outerObjectBean.hasGroups();
        
        
        if (hasGroups){
            int groupNums = outerObjectBean.getGroups().size();
            
            // if there are more than 1 groups that means than check for
            // ClassBeanGroup.needOuterAttributeGroup() or ClassBeanGroup.needOuterClassGroup()
            
            for (int i = 0; i < groupNums; i++) {
                ClassBeanGroup group = (ClassBeanGroup)outerObjectBean.getGroups().get(i);
                
                // only attributes..  check for more than 1 attribute.. if more than 1 attribute than only create group otherwise not..
                if (group.isAttributeOnlyGroup()){
                    ArrayList attList = group.getAttributeList();
                    if(attList.size() > 1){
                        // create a dcql group with attributes only..
                        Group dcqlGroupTmp = new Group();
                        dcqlGroupTmp.setLogicRelation(group.isAND()?LogicalOperator.AND:LogicalOperator.OR);
                        dcqlOuterObject.setGroup(dcqlGroupTmp);
                        
                        createAttributesGroup(dcqlGroupTmp, group.getAttributeList());
                    } else {
                        // just add the attribute normally..
                        createAttributesGroup(dcqlOuterObject, group.getAttributeList());
                    }
                }
                
                
                // only associations..
                
                else if (group.isClassOnlyGroup()){
                    // check how many class in there.. if more than one.. than create a group.. otherwise not..
                    int numClass = group.getClassList().size();
                    
                    if (numClass > 1){
                        Group dcqlGroupTmp = new Group();
                        dcqlGroupTmp.setLogicRelation(group.isAND()?LogicalOperator.AND:LogicalOperator.OR);
                        dcqlOuterObject.setGroup(dcqlGroupTmp);
                        createAssociations(dcqlGroupTmp, group);
                    }else{
                        createAssociations(dcqlOuterObject, group);
                    }
                    
                }
                
                
                // that means both attribute and association are there..
                else if (group.isMixGroup()){
                    // create a group of these two... cos there is atleast 1 attribute and 1 association..
                    
                    ArrayList attList = group.getAttributeList();
                    ArrayList classList = group.getClassList();
                    
                    Group dcqlGroupTmp = new Group();
                    dcqlGroupTmp.setLogicRelation(group.isAND()?LogicalOperator.AND:LogicalOperator.OR);
                    dcqlOuterObject.setGroup(dcqlGroupTmp);
                    
                    ArrayList dcqlInnerGroups = new ArrayList();
                    
                    // check for the multiple attributes..
                    if (attList.size() > 1){
                        // add an internal group.. and add that group to the group outside..
                        Group dcqlGroupTmpInner = new Group();
                        dcqlGroupTmpInner.setLogicRelation(group.isAND()?LogicalOperator.AND:LogicalOperator.OR);
                        dcqlInnerGroups.add(dcqlGroupTmpInner);
                        createAttributesGroup(dcqlGroupTmpInner, group.getAttributeList());
                        
                    } else {
                        // add only one attribute so
                        createAttributesGroup(dcqlGroupTmp, group.getAttributeList());
                    }
                    
                    // check for the multiple associations..
                    if (classList.size() > 1){
                        // add an internal group.. and add that group to the group outside..
                        Group dcqlGroupTmpInner = new Group();
                        dcqlGroupTmpInner.setLogicRelation(group.isAND()?LogicalOperator.AND:LogicalOperator.OR);
                        dcqlInnerGroups.add(dcqlGroupTmpInner);
                        createAssociations(dcqlGroupTmpInner, group);
                        
                    } else {
                        // add only one attribute so
                        createAssociations(dcqlGroupTmp, group);
                    }
                    
                    // now add the inner groups to the outer group if the size is more than one..
                    if (dcqlInnerGroups.size() > 1){
                        dcqlGroupTmp.setGroup((Group[])dcqlInnerGroups.toArray());
                    }
                    
                    
                    
                }
                
                
                
                
                
            }
            
        }
        
        
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
                
                // set any returned attributes if available..
//                setReturnedAttributes(dcqlOuterObject);
                
            }else {
                // sanjeev: has only 1 attribute
                Attribute dcqlAttribute = new Attribute(); //   Attribute dcqlAttriqbute = dcqlOuterObject.setAttribute();
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
                
                // set any returned attributes if available..
//                setReturnedAttributes(dcqlOuterObject);
                
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
        
        // set any returned attributes if available..
        setReturnedAttributes(dcqlOuterObject);
        
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
    
    
    private static void createAttributesGroup(Object outerObject, ArrayList targetObjectAttributeList){
        Object outerDcqlObject = outerObject;
        
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
        outerDcqlObject.setAttribute(dcqlAttributes[0]);
    }
    
    
    
    private static void createAssociations(Group outerObject, ClassBeanGroup group){
        
        Group outerDcqlGroup = outerObject;
        ArrayList associationList = group.getClassList();
        Association[]   dcqlAssociationArray = new Association[associationList.size()];
        
        for (int i = 0;i<associationList.size() ;i++){
            
            Association dcqlAssociation = new Association();
            dcqlAssociationArray[i] = dcqlAssociation;
            
            ClassBean localAssociation = (ClassBean)associationList.get(i);
            dcqlAssociation.setName(localAssociation.getFullyQualifiedName());
            dcqlAssociation.setRoleName( group.getClassRole(localAssociation.getId()) );
            
            buildAssociationGroup(dcqlAssociation, localAssociation); // this guy also need to use groups.. for association..
        }
        
        outerDcqlGroup.setAssociation(dcqlAssociationArray);
        
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
    
    
    
    private static void createAssociations(Object outerObject, ClassBeanGroup group){
        Object outerDcqlObject = outerObject;
        
        ArrayList associationList = group.getClassList();
        Association[]   dcqlAssociationArray = new Association[associationList.size()];
        
        for (int i = 0;i<associationList.size() ;i++){
            
            Association dcqlAssociation = new Association();
            dcqlAssociationArray[i] = dcqlAssociation;
            
            ClassBean localAssociation = (ClassBean)associationList.get(i);
            dcqlAssociation.setName(localAssociation.getFullyQualifiedName());
            dcqlAssociation.setRoleName( group.getClassRole(localAssociation.getId()) );
            
            buildAssociationGroup(dcqlAssociation, localAssociation); // this guy also need to use groups.. for association..
        }
        outerObject.setAssociation(dcqlAssociationArray[0]);
        
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
    
    
    
    
    
    private static void setReturnedAttributes(Object dcqlOuterObject){
        // set any returned attributes if available..
        boolean  available = SimpleGuiRegistry.hasReturnedAttributesForClass(dcqlOuterObject.getName());
        if (available){
            List atts = SimpleGuiRegistry.getReturnedAttributesForClass(dcqlOuterObject.getName());
            String[] attributes = new String[atts.size()];
            ReturnAttributes rtAtt = new ReturnAttributes((String[]) atts.toArray(attributes));
            dcqlOuterObject.setReturnAttributes(rtAtt);
        }
    }
    
    
    
    
    
}
