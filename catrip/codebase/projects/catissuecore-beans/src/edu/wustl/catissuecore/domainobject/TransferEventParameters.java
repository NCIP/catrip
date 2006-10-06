// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;


// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            SpecimenEventParameters, StorageContainer

public interface TransferEventParameters
    extends SpecimenEventParameters
{

    public abstract Integer getFromPositionDimensionOne();

    public abstract void setFromPositionDimensionOne(Integer integer);

    public abstract Integer getFromPositionDimensionTwo();

    public abstract void setFromPositionDimensionTwo(Integer integer);

    public abstract Integer getToPositionDimensionOne();

    public abstract void setToPositionDimensionOne(Integer integer);

    public abstract Integer getToPositionDimensionTwo();

    public abstract void setToPositionDimensionTwo(Integer integer);

    public abstract StorageContainer getToStorageContainer();

    public abstract void setToStorageContainer(StorageContainer storagecontainer);

    public abstract StorageContainer getFromStorageContainer();

    public abstract void setFromStorageContainer(StorageContainer storagecontainer);
}
