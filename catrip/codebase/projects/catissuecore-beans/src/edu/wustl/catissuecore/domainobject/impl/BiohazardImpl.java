// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.Biohazard;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

public class BiohazardImpl
    implements Serializable, Biohazard
{

    public BiohazardImpl()
    {
        specimenCollection = new HashSet();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String s)
    {
        name = s;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String s)
    {
        comments = s;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String s)
    {
        type = s;
    }

    public Collection getSpecimenCollection()
    {

        return specimenCollection;
    }

    public void setSpecimenCollection(Collection collection)
    {
        specimenCollection = collection;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof Biohazard)
        {
            Biohazard biohazard = (Biohazard)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(biohazard.getId()))
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
    protected String name;
    protected String comments;
    protected String type;
    private Collection specimenCollection;
}
