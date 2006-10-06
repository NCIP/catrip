// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.Participant;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

public class ParticipantImpl
    implements Serializable, Participant
{

    public ParticipantImpl()
    {
        participantMedicalIdentifierCollection = new HashSet();
        collectionProtocolRegistrationCollection = new HashSet();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String s)
    {
        lastName = s;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String s)
    {
        firstName = s;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String s)
    {
        middleName = s;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Date date)
    {
        birthDate = date;
    }

    public String getSexGenotype()
    {
        return sexGenotype;
    }

    public void setSexGenotype(String s)
    {
        sexGenotype = s;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String s)
    {
        gender = s;
    }

    public String getRace()
    {
        return race;
    }

    public void setRace(String s)
    {
        race = s;
    }

    public String getEthnicity()
    {
        return ethnicity;
    }

    public void setEthnicity(String s)
    {
        ethnicity = s;
    }

    public String getSocialSecurityNumber()
    {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String s)
    {
        socialSecurityNumber = s;
    }

    public String getActivityStatus()
    {
        return activityStatus;
    }

    public void setActivityStatus(String s)
    {
        activityStatus = s;
    }

    public Collection getParticipantMedicalIdentifierCollection()
    {

        return participantMedicalIdentifierCollection;
    }

    public void setParticipantMedicalIdentifierCollection(Collection collection)
    {
        participantMedicalIdentifierCollection = collection;
    }

    public Collection getCollectionProtocolRegistrationCollection()
    {

        return collectionProtocolRegistrationCollection;
    }

    public void setCollectionProtocolRegistrationCollection(Collection collection)
    {
        collectionProtocolRegistrationCollection = collection;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof Participant)
        {
            Participant participant = (Participant)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(participant.getId()))
                flag = true;
        }
        return flag;
    }

    public int hashCode()
    {
        int i = 0;
        if(getId() != null)
            i += getId().hashCode();
        return i;
    }

    private static final long serialVersionUID = 0x499602d2L;
    protected Long id;
    protected String lastName;
    protected String firstName;
    protected String middleName;
    protected Date birthDate;
    protected String sexGenotype;
    protected String gender;
    protected String race;
    protected String ethnicity;
    protected String socialSecurityNumber;
    protected String activityStatus;
    private Collection participantMedicalIdentifierCollection;
    private Collection collectionProtocolRegistrationCollection;
}
