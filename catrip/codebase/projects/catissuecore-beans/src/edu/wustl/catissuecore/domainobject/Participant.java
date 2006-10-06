// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;

import java.util.Collection;
import java.util.Date;

public interface Participant
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract String getLastName();

    public abstract void setLastName(String s);

    public abstract String getFirstName();

    public abstract void setFirstName(String s);

    public abstract String getMiddleName();

    public abstract void setMiddleName(String s);

    public abstract Date getBirthDate();

    public abstract void setBirthDate(Date date);

    public abstract String getSexGenotype();

    public abstract void setSexGenotype(String s);

    public abstract String getGender();

    public abstract void setGender(String s);

    public abstract String getRace();

    public abstract void setRace(String s);

    public abstract String getEthnicity();

    public abstract void setEthnicity(String s);

    public abstract String getSocialSecurityNumber();

    public abstract void setSocialSecurityNumber(String s);

    public abstract String getActivityStatus();

    public abstract void setActivityStatus(String s);

    public abstract Collection getParticipantMedicalIdentifierCollection();

    public abstract void setParticipantMedicalIdentifierCollection(Collection collection);

    public abstract Collection getCollectionProtocolRegistrationCollection();

    public abstract void setCollectionProtocolRegistrationCollection(Collection collection);
}
