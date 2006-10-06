// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.StorageContainerCapacity;
import java.io.Serializable;

public class StorageContainerCapacityImpl
    implements Serializable, StorageContainerCapacity
{

    public StorageContainerCapacityImpl()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public Integer getOneDimensionCapacity()
    {
        return oneDimensionCapacity;
    }

    public void setOneDimensionCapacity(Integer integer)
    {
        oneDimensionCapacity = integer;
    }

    public Integer getTwoDimensionCapacity()
    {
        return twoDimensionCapacity;
    }

    public void setTwoDimensionCapacity(Integer integer)
    {
        twoDimensionCapacity = integer;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof StorageContainerCapacity)
        {
            StorageContainerCapacity storagecontainercapacity = (StorageContainerCapacity)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(storagecontainercapacity.getId()))
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
    protected Integer oneDimensionCapacity;
    protected Integer twoDimensionCapacity;
}
