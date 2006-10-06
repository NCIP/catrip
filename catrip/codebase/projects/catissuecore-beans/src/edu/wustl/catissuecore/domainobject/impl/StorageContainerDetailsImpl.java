// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.StorageContainer;
import edu.wustl.catissuecore.domainobject.StorageContainerDetails;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;

public class StorageContainerDetailsImpl
    implements Serializable, StorageContainerDetails
{

    public StorageContainerDetailsImpl()
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

    public String getParameterName()
    {
        return parameterName;
    }

    public void setParameterName(String s)
    {
        parameterName = s;
    }

    public String getParameterValue()
    {
        return parameterValue;
    }

    public void setParameterValue(String s)
    {
        parameterValue = s;
    }

    public StorageContainer getStorageContainer()
    {

        return storageContainer;
    }

    public void setStorageContainer(StorageContainer storagecontainer)
    {
        storageContainer = storagecontainer;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof StorageContainerDetails)
        {
            StorageContainerDetails storagecontainerdetails = (StorageContainerDetails)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(storagecontainerdetails.getId()))
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
    protected String parameterName;
    protected String parameterValue;
    private StorageContainer storageContainer;
}
