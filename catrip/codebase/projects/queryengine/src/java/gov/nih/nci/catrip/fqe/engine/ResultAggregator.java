package gov.nih.nci.catrip.fqe.engine;

import caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute;
import caBIG.cql.x1.govNihNciCagridCQLQuery.Group;
import caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator;
import caBIG.cql.x1.govNihNciCagridCQLQuery.Predicate;
import caBIG.cql.x1.govNihNciCagridCQLResultSet.CQLObjectResult;
import caBIG.cql.x1.govNihNciCagridCQLResultSet.CQLQueryResults;

import gov.nih.nci.cagrid.dcql.Join;
import gov.nih.nci.cagrid.dcql.JoinCondition;

import java.io.Reader;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import java.util.Set;

import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;


public class ResultAggregator {
    
    private JoinCondition joinCondition;
    
    public ResultAggregator(JoinCondition joinCondition) {
        this.joinCondition = joinCondition;
    }
    public List processResults(CQLQueryResults results){
        
        CQLObjectResult[] resultObjectArry = results.getObjectResultArray();
        
        SAXBuilder saxBuilder=new SAXBuilder("org.apache.xerces.parsers.SAXParser");
        Reader stringReader;
        org.jdom.Document jdomDocument;
        CQLObjectResult obj;
        List resultList = new ArrayList(); 
        
        Join rightJoin = joinCondition.getRightJoin();
        String xpathStr = "/Object[@name='"+rightJoin.getObject()+"']/Attribute[@name='"+rightJoin.getAttribute()+"']";

        try {
            XPath xpath = XPath.newInstance(xpathStr);

            for (int i=0;i<resultObjectArry.length;i++) {
                obj = resultObjectArry[i];

                stringReader = new StringReader(obj.xmlText());
                jdomDocument = saxBuilder.build(stringReader);
                Element myEl = (Element) xpath.selectSingleNode(jdomDocument);
                
                resultList.add(myEl.getAttribute("value").getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return resultList;

    }
    

    public Group buildGroup(List list){
    
        caBIG.cql.x1.govNihNciCagridCQLQuery.Group cqlGroup = caBIG.cql.x1.govNihNciCagridCQLQuery.Group.Factory.newInstance();
        
        Attribute[] attrArray = new Attribute[list.size()];
        for(int i=0;i<list.size();i++) {
            Attribute attr = Attribute.Factory.newInstance();
            attr.setName(joinCondition.getLeftJoin().getAttribute());
            attr.setValue(list.get(i).toString());
            attr.setPredicate(Predicate.EQUAL_TO);
            attrArray[i]=attr;
        }
        cqlGroup.setLogicRelation(LogicalOperator.OR);
        cqlGroup.setAttributeArray(attrArray);
        
        return cqlGroup;
    }
    
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

}
