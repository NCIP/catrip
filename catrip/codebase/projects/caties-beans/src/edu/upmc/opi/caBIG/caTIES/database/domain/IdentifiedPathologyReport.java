package edu.upmc.opi.caBIG.caTIES.database.domain;



public class IdentifiedPathologyReport implements java.io.Serializable
         {
    private static final long serialVersionUID = 1234567890L;

    protected java.lang.Long id;

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }
    
    protected java.lang.Long version;

    public java.lang.Long getVersion() {
        return version;
    }

    public void setVersion(java.lang.Long version) {
        this.version = version;
    }


    protected java.lang.String uuid;

    public java.lang.String getUuid() {
        return uuid;
    }

    public void setUuid(java.lang.String uuid) {
        this.uuid = uuid;
    }

    protected java.lang.String deidentifiedId;

    public java.lang.String getDeidentifiedId() {
        return deidentifiedId;
    }

    public void setDeidentifiedId(java.lang.String deidentifiedId) {
        this.deidentifiedId = deidentifiedId;
    }

    protected java.lang.String documentText;

    public java.lang.String getDocumentText() {
        return documentText;
    }

    public void setDocumentText(java.lang.String documentText) {
        this.documentText = documentText;
    }

    protected java.lang.String accessionNumber;

    public java.lang.String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(java.lang.String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    protected java.util.Date collectionDateTime;

    public java.util.Date getCollectionDateTime() {
        return collectionDateTime;
    }

    public void setCollectionDateTime(java.util.Date collectionDateTime) {
        this.collectionDateTime = collectionDateTime;
    }

    protected java.lang.String orderingPhysicianId;

    public java.lang.String getOrderingPhysicianId() {
        return orderingPhysicianId;
    }

    public void setOrderingPhysicianId(java.lang.String orderingPhysicianId) {
        this.orderingPhysicianId = orderingPhysicianId;
    }

    protected java.lang.String honestBrokerComment;

    public java.lang.String getHonestBrokerComment() {
        return honestBrokerComment;
    }

    public void setHonestBrokerComment(java.lang.String honestBrokerComment) {
        this.honestBrokerComment = honestBrokerComment;
    }

    private java.util.Collection executionCollection = new java.util.HashSet();

    public java.util.Collection getExecutionCollection() {
        return executionCollection;
    }

 
    private java.util.Collection identifiedSectionCollection = new java.util.HashSet();

    public java.util.Collection getIdentifiedSectionCollection() {
        return identifiedSectionCollection;
    }


    private edu.upmc.opi.caBIG.caTIES.database.domain.IdentifiedPatient identifiedPatient;

    public edu.upmc.opi.caBIG.caTIES.database.domain.IdentifiedPatient getIdentifiedPatient() {
        return identifiedPatient;
    }

    public void setIdentifiedPatient(
            edu.upmc.opi.caBIG.caTIES.database.domain.IdentifiedPatient identifiedPatient) {
        this.identifiedPatient = identifiedPatient;
    }


 
    public int hashCode() {
        int h = 0;
        if (getUuid() != null) {
            h += getUuid().hashCode();
        }
        return h;
    }
        
           

}