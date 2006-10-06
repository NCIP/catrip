// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;


// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            SpecimenEventParameters

public interface CheckInCheckOutEventParameter
    extends SpecimenEventParameters
{

    public abstract String getStorageStatus();

    public abstract void setStorageStatus(String s);
}
