// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.*;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

public class StorageContainerImpl
    implements Serializable, StorageContainer
{

    public StorageContainerImpl()
    {
        childrenContainerCollection = new HashSet();
        storageContainerDetailsCollection = new HashSet();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public Integer getNumber()
    {
        return number;
    }

    public void setNumber(Integer integer)
    {
        number = integer;
    }

    public Double getTempratureInCentigrade()
    {
        return tempratureInCentigrade;
    }

    public void setTempratureInCentigrade(Double double1)
    {
        tempratureInCentigrade = double1;
    }

    public Boolean getIsFull()
    {
        return isFull;
    }

    public void setIsFull(Boolean boolean1)
    {
        isFull = boolean1;
    }

    public String getBarcode()
    {
        return barcode;
    }

    public void setBarcode(String s)
    {
        barcode = s;
    }

    public String getActivityStatus()
    {
        return activityStatus;
    }

    public void setActivityStatus(String s)
    {
        activityStatus = s;
    }

    public Integer getPositionDimensionOne()
    {
        return positionDimensionOne;
    }

    public void setPositionDimensionOne(Integer integer)
    {
        positionDimensionOne = integer;
    }

    public Integer getPositionDimensionTwo()
    {
        return positionDimensionTwo;
    }

    public void setPositionDimensionTwo(Integer integer)
    {
        positionDimensionTwo = integer;
    }

    public StorageContainerCapacity getStorageContainerCapacity()
    {

        return storageContainerCapacity;
    }

    public void setStorageContainerCapacity(StorageContainerCapacity storagecontainercapacity)
    {
        storageContainerCapacity = storagecontainercapacity;
    }

    public StorageContainer getParentContainer()
    {

        return parentContainer;
    }

    public void setParentContainer(StorageContainer storagecontainer)
    {
        parentContainer = storagecontainer;
    }

    public Collection getChildrenContainerCollection()
    {

        return childrenContainerCollection;
    }

    public void setChildrenContainerCollection(Collection collection)
    {
        childrenContainerCollection = collection;
    }

    public Site getSite()
    {

        return site;
    }

    public void setSite(Site site1)
    {
        site = site1;
    }

    public StorageType getStorageType()
    {

        return storageType;
    }

    public void setStorageType(StorageType storagetype)
    {
        storageType = storagetype;
    }

    public Collection getStorageContainerDetailsCollection()
    {

        return storageContainerDetailsCollection;
    }

    public void setStorageContainerDetailsCollection(Collection collection)
    {
        storageContainerDetailsCollection = collection;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof StorageContainer)
        {
            StorageContainer storagecontainer = (StorageContainer)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(storagecontainer.getId()))
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
    protected Integer number;
    protected Double tempratureInCentigrade;
    protected Boolean isFull;
    protected String barcode;
    protected String activityStatus;
    protected Integer positionDimensionOne;
    protected Integer positionDimensionTwo;
    private StorageContainerCapacity storageContainerCapacity;
    private StorageContainer parentContainer;
    private Collection childrenContainerCollection;
    private Site site;
    private StorageType storageType;
    private Collection storageContainerDetailsCollection;
}
