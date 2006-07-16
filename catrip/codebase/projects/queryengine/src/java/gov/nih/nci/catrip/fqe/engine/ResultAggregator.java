package gov.nih.nci.catrip.fqe.engine;

import gov.nih.nci.cadsr.domain.DataElement;
import gov.nih.nci.cagrid.cqlquery.LogicalOperator;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;
import gov.nih.nci.cagrid.dcql.Join;
import gov.nih.nci.cagrid.dcql.JoinCondition;

import java.io.File;
import java.io.FileInputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;


public class ResultAggregator {
    
    private JoinCondition joinCondition;
    
    public ResultAggregator(JoinCondition joinCondition) {
        this.joinCondition = joinCondition;
    }
    public List processResults(CQLQueryResults results){
        Join rightJoin = joinCondition.getRightJoin();
        String classNameStr = rightJoin.getObject();
        String cde = rightJoin.getAttribute();
        List resultList = new ArrayList(); 

         try {
             CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("src/gov/nih/nci/cagrid/client/client-config.wsdd")));
             Class className = Class.forName(classNameStr);
             Method methodName = className.getMethod("get"+cde.substring(0,1).toUpperCase()+cde.substring(1,cde.length()));
             while (iter.hasNext()) {
                   Object o = methodName.invoke(iter.next());
                   resultList.add(o.toString());
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
