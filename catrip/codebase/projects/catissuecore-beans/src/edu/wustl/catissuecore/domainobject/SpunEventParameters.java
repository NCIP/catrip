// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;


// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            SpecimenEventParameters

public interface SpunEventParameters
    extends SpecimenEventParameters
{

    public abstract Double getGForce();

    public abstract void setGForce(Double double1);

    public abstract Integer getDurationInMinutes();

    public abstract void setDurationInMinutes(Integer integer);
}
