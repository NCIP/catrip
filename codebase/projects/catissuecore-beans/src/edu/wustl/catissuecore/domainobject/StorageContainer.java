/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;

import java.util.Collection;

// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            StorageContainerCapacity, Site, StorageType

public interface StorageContainer
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract Integer getNumber();

    public abstract void setNumber(Integer integer);

    public abstract Double getTempratureInCentigrade();

    public abstract void setTempratureInCentigrade(Double double1);

    public abstract Boolean getIsFull();

    public abstract void setIsFull(Boolean boolean1);

    public abstract String getBarcode();

    public abstract void setBarcode(String s);

    public abstract String getActivityStatus();

    public abstract void setActivityStatus(String s);

    public abstract Integer getPositionDimensionOne();

    public abstract void setPositionDimensionOne(Integer integer);

    public abstract Integer getPositionDimensionTwo();

    public abstract void setPositionDimensionTwo(Integer integer);

    public abstract StorageContainerCapacity getStorageContainerCapacity();

    public abstract void setStorageContainerCapacity(StorageContainerCapacity storagecontainercapacity);

    public abstract StorageContainer getParentContainer();

    public abstract void setParentContainer(StorageContainer storagecontainer);

    public abstract Collection getChildrenContainerCollection();

    public abstract void setChildrenContainerCollection(Collection collection);

    public abstract Site getSite();

    public abstract void setSite(Site site);

    public abstract StorageType getStorageType();

    public abstract void setStorageType(StorageType storagetype);

    public abstract Collection getStorageContainerDetailsCollection();

    public abstract void setStorageContainerDetailsCollection(Collection collection);
}
