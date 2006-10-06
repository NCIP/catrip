// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.CollectionProtocol;
import edu.wustl.catissuecore.domainobject.CollectionProtocolEvent;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

public class CollectionProtocolEventImpl
    implements Serializable, CollectionProtocolEvent
{

    public CollectionProtocolEventImpl()
    {
        specimenRequirementCollection = new HashSet();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public String getClinicalStatus()
    {
        return clinicalStatus;
    }

    public void setClinicalStatus(String s)
    {
        clinicalStatus = s;
    }

    public Double getStudyCalendarEventPoint()
    {
        return studyCalendarEventPoint;
    }

    public void setStudyCalendarEventPoint(Double double1)
    {
        studyCalendarEventPoint = double1;
    }

    public CollectionProtocol getCollectionProtocol()
    {

        return collectionProtocol;
    }

    public void setCollectionProtocol(CollectionProtocol collectionprotocol)
    {
        collectionProtocol = collectionprotocol;
    }

    public Collection getSpecimenRequirementCollection()
    {

        return specimenRequirementCollection;
    }

    public void setSpecimenRequirementCollection(Collection collection)
    {
        specimenRequirementCollection = collection;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof CollectionProtocolEvent)
        {
            CollectionProtocolEvent collectionprotocolevent = (CollectionProtocolEvent)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(collectionprotocolevent.getId()))
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
    protected String clinicalStatus;
    protected Double studyCalendarEventPoint;
    private CollectionProtocol collectionProtocol;
    private Collection specimenRequirementCollection;
}
