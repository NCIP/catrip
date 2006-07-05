package gov.nih.nci.catrip.fqe.engine;

import caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute;
import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;
import caBIG.cql.x1.govNihNciCagridCQLQuery.Group;

import caBIG.cql.x1.govNihNciCagridCQLQuery.LogicalOperator;
import caBIG.cql.x1.govNihNciCagridCQLQuery.Predicate;

import gov.nih.nci.catrip.fqe.data.DummyService1;

import java.util.List;

public class FederatedQueryExecutor {
    public FederatedQueryExecutor() {
    }
    
    public Group executeQry(CQLQueryDocument cqlQry) {
        DummyService1 ds = new DummyService1();
        return buildGroup(ds.gerResultList(cqlQry));
    }
    
    public Group buildGroup(List list){
    
        caBIG.cql.x1.govNihNciCagridCQLQuery.Group cqlGroup = caBIG.cql.x1.govNihNciCagridCQLQuery.Group.Factory.newInstance();
        Attribute[] attrArray = new Attribute[list.size()];
        for(int i=0;i<list.size();i++) {
            Attribute attr = Attribute.Factory.newInstance();
            attr.setName("identifier");
            attr.setValue(list.get(i).toString());
            attr.setPredicate(Predicate.EQUAL_TO);
            attrArray[i]=attr;
        }
        cqlGroup.setLogicRelation(LogicalOperator.OR);
        cqlGroup.setAttributeArray(attrArray);
        
        return cqlGroup;
    }
}
