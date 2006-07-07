package gov.nih.nci.catrip.fqe.engine;

import caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute;
import caBIG.cql.x1.govNihNciCagridCQLQuery.Group;
import caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator;
import caBIG.cql.x1.govNihNciCagridCQLQuery.Predicate;

import caBIG.cql.x1.govNihNciCagridCQLResultSet.CQLObjectResult;
import caBIG.cql.x1.govNihNciCagridCQLResultSet.CQLQueryResults;

import gov.nih.nci.cagrid.dcql.Join;

import gov.nih.nci.cagrid.dcql.JoinCondition;

import java.io.File;

import java.io.IOException;

import java.io.Reader;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlException;

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

}
