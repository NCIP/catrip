/*
 * IdentifiedPathologyReportDocument.java
 *
 * Created on May 14, 2007, 11:24 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.upmc.opi.caBIG.caTIES.database.domain;

/**
 *
 * @author sakkala
 */
public class IdentifiedPathologyReportDocument {
    
    /** Creates a new instance of IdentifiedPathologyReportDocument */
    public IdentifiedPathologyReportDocument() {
    }
    
    private static final long serialVersionUID = 1234567890L;

    protected java.lang.Long id;

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }
    

    protected java.lang.String documentText;

    public java.lang.String getDocumentText() {
        return documentText;
    }

    public void setDocumentText(java.lang.String documentText) {
        this.documentText = documentText;
    }

    protected java.lang.String documentId;

    public java.lang.String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(java.lang.String documentId) {
        this.documentId = documentId;
    }
    
    
    private IdentifiedPathologyReport identifiedPathologyReport;
    
    public IdentifiedPathologyReport getIdentifiedPathologyReport() {
        return identifiedPathologyReport;
    }

    public void setIdentifiedPathologyReport(
            IdentifiedPathologyReport identifiedPathologyReport) {
        this.identifiedPathologyReport = identifiedPathologyReport;
    } 
    
}
