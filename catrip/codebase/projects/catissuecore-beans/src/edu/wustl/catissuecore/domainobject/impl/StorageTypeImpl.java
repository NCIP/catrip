// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.StorageContainerCapacity;
import edu.wustl.catissuecore.domainobject.StorageType;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;

public class StorageTypeImpl
    implements Serializable, StorageType
{

    public StorageTypeImpl()
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

    public String getType()
    {
        return type;
    }

    public void setType(String s)
    {
        type = s;
    }

    public Double getDefaultTempratureInCentigrade()
    {
        return defaultTempratureInCentigrade;
    }

    public void setDefaultTempratureInCentigrade(Double double1)
    {
        defaultTempratureInCentigrade = double1;
    }

    public String getOneDimensionLabel()
    {
        return oneDimensionLabel;
    }

    public void setOneDimensionLabel(String s)
    {
        oneDimensionLabel = s;
    }

    public String getTwoDimensionLabel()
    {
        return twoDimensionLabel;
    }

    public void setTwoDimensionLabel(String s)
    {
        twoDimensionLabel = s;
    }

    public StorageContainerCapacity getDefaultStorageCapacity()
    {
           return defaultStorageCapacity;
    }

    public void setDefaultStorageCapacity(StorageContainerCapacity storagecontainercapacity)
    {
        defaultStorageCapacity = storagecontainercapacity;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof StorageType)
        {
            StorageType storagetype = (StorageType)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(storagetype.getId()))
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
    protected String type;
    protected Double defaultTempratureInCentigrade;
    protected String oneDimensionLabel;
    protected String twoDimensionLabel;
    private StorageContainerCapacity defaultStorageCapacity;
}
