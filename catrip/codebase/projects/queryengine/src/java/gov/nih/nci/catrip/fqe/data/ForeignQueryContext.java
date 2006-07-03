package gov.nih.nci.catrip.fqe.data;

import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;
import caBIG.cql.x1.govNihNciCagridCQLQuery.Object;


public class ForeignQueryContext {
    
    private CQLQueryDocument cqlQryDoc ;
    private Integer sequence ; 
    private String typeOfAggregation ; 
    // more ...
    
    
    public ForeignQueryContext() {
    }


    public void setCqlQryDoc(CQLQueryDocument cqlQryDoc) {
        this.cqlQryDoc = cqlQryDoc;
    }

    public CQLQueryDocument getCqlQryDoc() {
        return cqlQryDoc;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setTypeOfAggregation(String typeOfAggregation) {
        this.typeOfAggregation = typeOfAggregation;
    }

    public String getTypeOfAggregation() {
        return typeOfAggregation;
    }

}
