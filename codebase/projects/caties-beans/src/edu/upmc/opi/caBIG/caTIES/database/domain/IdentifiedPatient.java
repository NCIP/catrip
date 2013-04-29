/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.upmc.opi.caBIG.caTIES.database.domain;


import java.util.Iterator;

import edu.upmc.opi.caBIG.caTIES.database.domain.*;


public class IdentifiedPatient implements java.io.Serializable        {
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

    protected java.lang.String medicalRecordNumber;

    public java.lang.String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(java.lang.String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    protected java.lang.String firstName;

    public java.lang.String getFirstName() {
        return firstName;
    }

    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }

    protected java.lang.String lastName;

    public java.lang.String getLastName() {
        return lastName;
    }

    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }

    protected java.lang.String middleName;

    public java.lang.String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(java.lang.String middleName) {
        this.middleName = middleName;
    }

    protected java.util.Date birthDate;

    public java.util.Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(java.util.Date birthDate) {
        this.birthDate = birthDate;
    }

    protected java.lang.String socialSecurityNumber;

    public java.lang.String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(java.lang.String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    protected java.lang.String gender;

    public java.lang.String getGender() {
        return gender;
    }

    public void setGender(java.lang.String gender) {
        this.gender = gender;
    }

    protected java.lang.String race;

    public java.lang.String getRace() {
        return race;
    }

    public void setRace(java.lang.String race) {
        this.race = race;
    }

    protected java.lang.String ethnicity;

    public java.lang.String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(java.lang.String ethnicity) {
        this.ethnicity = ethnicity;
    }

    protected java.lang.String maritalStatus;

    public java.lang.String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(java.lang.String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    private java.util.Collection identifiedPathologyReportCollection = new java.util.HashSet();

    public java.util.Collection getIdentifiedPathologyReportCollection() {
        return identifiedPathologyReportCollection;
    }

    public void setIdentifiedPathologyReportCollection(
            java.util.Collection pathologyReportCollection) {
        this.identifiedPathologyReportCollection = pathologyReportCollection;
        
        for(Iterator i = pathologyReportCollection.iterator(); i.hasNext();)
        {
            IdentifiedPathologyReport report = (IdentifiedPathologyReport)i.next();
            report.setIdentifiedPatient(this);
        }
    }

    public void addPathologyReport(
            edu.upmc.opi.caBIG.caTIES.database.domain.IdentifiedPathologyReport pathologyReport) {
        this.identifiedPathologyReportCollection.add(pathologyReport);

        pathologyReport.setIdentifiedPatient(this);
    }

 
    public boolean equals(Object obj) {
        boolean eq = false;
        if (obj instanceof IdentifiedPatient) {
            IdentifiedPatient c = (IdentifiedPatient) obj;
            String thisUUID = getUuid() ;
            if (this.uuid != null && thisUUID.equals(c.getUuid())) {
            	eq = true ;
            }
        }
        return eq;
    }

    public int hashCode() {
        int h = 0;
        if (getUuid() != null) {
            h += getUuid().hashCode();
        }
        return h;
    }
         
}