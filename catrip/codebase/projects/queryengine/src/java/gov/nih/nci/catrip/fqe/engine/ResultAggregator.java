package gov.nih.nci.catrip.fqe.engine;

import gov.nih.nci.cadsr.domain.DataElement;
import gov.nih.nci.cagrid.cqlquery.LogicalOperator;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;
import gov.nih.nci.cagrid.dcql.JoinCondition;

import java.io.File;
import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.List;


public class ResultAggregator {
    
    private JoinCondition joinCondition;
    
    public ResultAggregator(JoinCondition joinCondition) {
        this.joinCondition = joinCondition;
    }
    public List processResults(CQLQueryResults results){
/*
        SAXBuilder saxBuilder=new SAXBuilder("org.apache.xerces.parsers.SAXParser");
        Reader stringReader;
        org.jdom.Document jdomDocument;
        List resultList = new ArrayList(); 
        
        Join rightJoin = joinCondition.getRightJoin();
        String xpathStr = "/Object[@name='"+rightJoin.getObject()+"']/Attribute[@name='"+rightJoin.getAttribute()+"']";        
        
  */      
        List resultList = new ArrayList(); 
        
         try {
         //    XPath xpath = XPath.newInstance(xpathStr);
             
         CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("src/gov/nih/nci/cagrid/client/client-config.wsdd")));
         
         while (iter.hasNext()) {
         
               DataElement de = (DataElement) iter.next();
               //System.out.println(de.getBeginDate());
               resultList.add(de.getLongName());// need to get this longname using right join and reflection
         } 
         } catch (Exception e ) {
             e.printStackTrace();
         }
       return resultList;
    }
    

    public gov.nih.nci.cagrid.cqlquery.Group buildGroup(List list){
    
        gov.nih.nci.cagrid.cqlquery.Group cqlGroup = new gov.nih.nci.cagrid.cqlquery.Group();
        
        gov.nih.nci.cagrid.cqlquery.Attribute[] attrArray = new gov.nih.nci.cagrid.cqlquery.Attribute[list.size()];
        for(int i=0;i<list.size();i++) {
            gov.nih.nci.cagrid.cqlquery.Attribute attr = new gov.nih.nci.cagrid.cqlquery.Attribute();
            attr.setName(joinCondition.getLeftJoin().getAttribute());
            attr.setValue(list.get(i).toString());
            attr.setPredicate(gov.nih.nci.cagrid.cqlquery.Predicate.EQUAL_TO);
            attrArray[i]=attr;
        }
        cqlGroup.setLogicRelation(LogicalOperator.fromString("OR"));
        cqlGroup.setAttribute(attrArray);
        
        return cqlGroup;
    }
/*    
    public static Group aggregateGroups(caBIG.cql.x1.govNihNciCagridCQLQuery.Group[] groupsTomerge){
        caBIG.cql.x1.govNihNciCagridCQLQuery.Group aggregatedGroup = caBIG.cql.x1.govNihNciCagridCQLQuery.Group.Factory.newInstance();
        List s = new ArrayList();
    
        for (int i=0;i<groupsTomerge.length;i++){
            caBIG.cql.x1.govNihNciCagridCQLQuery.Group cqlGroup1 = groupsTomerge[i];
            Attribute[] attrArray = cqlGroup1.getAttributeArray();
            
            for (int j=0;j<attrArray.length;j++) {
                Attribute a = (Attribute)attrArray[i];
                s.add(a);
            }

        }
        
        
        Attribute[] attrArray1 = new Attribute[s.size()];
        System.out.println(s.size());
        Iterator sitr = s.iterator();
        int c=0;
        while (sitr.hasNext()) {
            Attribute a  = (Attribute)sitr.next();
            attrArray1[c] = a;
            System.out.println(c);
            c++;
        }
        System.out.println(attrArray1.length);
        aggregatedGroup.setLogicRelation(LogicalOperator.OR);
        aggregatedGroup.addNewAttribute();
        aggregatedGroup.setAttributeArray(attrArray1);
        return aggregatedGroup;
    }
*/
}
