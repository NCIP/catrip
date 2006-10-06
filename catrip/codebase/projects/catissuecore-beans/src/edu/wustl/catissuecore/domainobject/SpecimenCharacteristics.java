// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;


public interface SpecimenCharacteristics
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract String getTissueSite();

    public abstract void setTissueSite(String s);

    public abstract String getTissueSide();

    public abstract void setTissueSide(String s);

    public abstract String getPathologicalStatus();

    public abstract void setPathologicalStatus(String s);
}
