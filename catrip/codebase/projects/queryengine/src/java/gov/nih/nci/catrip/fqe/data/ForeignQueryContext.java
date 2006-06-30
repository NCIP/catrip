package gov.nih.nci.catrip.fqe.data;

import caBIG.cql.x1.govNihNciCagridCQLQuery.Object;

public class ForeignQueryContext {
    
    private caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject ;
    private Integer sequence ; 
    private String typeOfAggregation ; 
    // more ...
    
    
    public ForeignQueryContext() {
    }

    public void setCqlObject(Object cqlObject) {
        this.cqlObject = cqlObject;
    }

    public Object getCqlObject() {
        return cqlObject;
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
