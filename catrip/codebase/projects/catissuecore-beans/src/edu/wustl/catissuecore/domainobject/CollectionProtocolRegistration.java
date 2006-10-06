// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;

import java.util.Date;

// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            CollectionProtocol, Participant

public interface CollectionProtocolRegistration
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract Date getRegistrationDate();

    public abstract void setRegistrationDate(Date date);

    public abstract String getActivityStatus();

    public abstract void setActivityStatus(String s);

    public abstract String getProtocolParticipantIdentifier();

    public abstract void setProtocolParticipantIdentifier(String s);

    public abstract CollectionProtocol getCollectionProtocol();

    public abstract void setCollectionProtocol(CollectionProtocol collectionprotocol);

    public abstract Participant getParticipant();

    public abstract void setParticipant(Participant participant);
}
