package gov.nih.nci.catrip.fqe.engine;

import caBIG.caGrid.x10.govNihNciCagridDcql.Join;
import caBIG.caGrid.x10.govNihNciCagridDcql.JoinCondition;

import gov.nih.nci.cagrid.cqlquery.LogicalOperator;
import gov.nih.nci.cagrid.cqlquery.Predicate;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;

import java.io.File;
import java.io.FileInputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;


class ResultAggregator {

    private JoinCondition joinCondition;

    ResultAggregator(JoinCondition joinCondition) {
        this.joinCondition = joinCondition;
    }
    /**
     *
     * @param results
     * @return
     */
    List processResults(CQLQueryResults results){
        Join rightJoin = joinCondition.getRightJoin();

        String classNameStr = rightJoin.getObject();
        String cde = rightJoin.getProperty();
        List resultList = new ArrayList();

         try {
             if (results != null) {
                 CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("src/gov/nih/nci/cagrid/client/client-config.wsdd")));
                 // CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("C:\\MyWork\\CaBIG\\caTissueCoreLocal\\src\\services\\catissuecore-client-config.wsdd")));
                 Class className = Class.forName(classNameStr);
                 Method methodName = className.getMethod("get"+cde.substring(0,1).toUpperCase()+cde.substring(1,cde.length()));
                 while (iter.hasNext()) {
                       Object o = methodName.invoke(iter.next());
                       resultList.add(o.toString());
                 }
             }

         } catch (Exception e ) {
             e.printStackTrace();
         }
       return resultList;
    }

    /**
     *
     * @param list
     * @return
     */
    gov.nih.nci.cagrid.cqlquery.Group buildGroup(List list){

        gov.nih.nci.cagrid.cqlquery.Group cqlGroup = new gov.nih.nci.cagrid.cqlquery.Group();

        gov.nih.nci.cagrid.cqlquery.Attribute[] attrArray = new gov.nih.nci.cagrid.cqlquery.Attribute[list.size()];
        gov.nih.nci.cagrid.cqlquery.Attribute attr = null;
        String property = joinCondition.getLeftJoin().getProperty();
        for(int i=0;i<list.size();i++) {
            attr = new gov.nih.nci.cagrid.cqlquery.Attribute();
            attr.setName(property);
            attr.setValue(list.get(i).toString());
            attr.setPredicate(gov.nih.nci.cagrid.cqlquery.Predicate.EQUAL_TO);
            attrArray[i]=attr;
        }
        if (list.size() == 0) {
            attr = new gov.nih.nci.cagrid.cqlquery.Attribute();
            attr.setName(property);
            attr.setPredicate(Predicate.IS_NULL);
            attrArray = new gov.nih.nci.cagrid.cqlquery.Attribute[1];
            attrArray[0]=attr;
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
