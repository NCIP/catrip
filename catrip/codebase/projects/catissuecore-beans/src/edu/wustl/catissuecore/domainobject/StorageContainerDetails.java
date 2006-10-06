// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;


// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            StorageContainer

public interface StorageContainerDetails
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract String getParameterName();

    public abstract void setParameterName(String s);

    public abstract String getParameterValue();

    public abstract void setParameterValue(String s);

    public abstract StorageContainer getStorageContainer();

    public abstract void setStorageContainer(StorageContainer storagecontainer);
}
