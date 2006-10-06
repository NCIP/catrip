// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.SpecimenRequirement;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

public class SpecimenRequirementImpl
    implements Serializable, SpecimenRequirement
{

    public SpecimenRequirementImpl()
    {
        distributionProtocolCollection = new HashSet();
        collectionProtocolEventCollection = new HashSet();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public String getSpecimenType()
    {
        return specimenType;
    }

    public void setSpecimenType(String s)
    {
        specimenType = s;
    }

    public String getTissueSite()
    {
        return tissueSite;
    }

    public void setTissueSite(String s)
    {
        tissueSite = s;
    }

    public String getPathologyStatus()
    {
        return pathologyStatus;
    }

    public void setPathologyStatus(String s)
    {
        pathologyStatus = s;
    }

    public Collection getDistributionProtocolCollection()
    {

        return distributionProtocolCollection;
    }

    public void setDistributionProtocolCollection(Collection collection)
    {
        distributionProtocolCollection = collection;
    }

    public Collection getCollectionProtocolEventCollection()
    {

        return collectionProtocolEventCollection;
    }

    public void setCollectionProtocolEventCollection(Collection collection)
    {
        collectionProtocolEventCollection = collection;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof SpecimenRequirement)
        {
            SpecimenRequirement specimenrequirement = (SpecimenRequirement)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(specimenrequirement.getId()))
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
    protected String specimenType;
    protected String tissueSite;
    protected String pathologyStatus;
    private Collection distributionProtocolCollection;
    private Collection collectionProtocolEventCollection;
}
