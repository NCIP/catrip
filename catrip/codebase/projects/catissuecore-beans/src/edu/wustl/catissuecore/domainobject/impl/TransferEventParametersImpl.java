// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.StorageContainer;
import edu.wustl.catissuecore.domainobject.TransferEventParameters;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            SpecimenEventParametersImpl

public class TransferEventParametersImpl extends SpecimenEventParametersImpl
    implements Serializable, TransferEventParameters
{

    public TransferEventParametersImpl()
    {
    }

    public Integer getFromPositionDimensionOne()
    {
        return fromPositionDimensionOne;
    }

    public void setFromPositionDimensionOne(Integer integer)
    {
        fromPositionDimensionOne = integer;
    }

    public Integer getFromPositionDimensionTwo()
    {
        return fromPositionDimensionTwo;
    }

    public void setFromPositionDimensionTwo(Integer integer)
    {
        fromPositionDimensionTwo = integer;
    }

    public Integer getToPositionDimensionOne()
    {
        return toPositionDimensionOne;
    }

    public void setToPositionDimensionOne(Integer integer)
    {
        toPositionDimensionOne = integer;
    }

    public Integer getToPositionDimensionTwo()
    {
        return toPositionDimensionTwo;
    }

    public void setToPositionDimensionTwo(Integer integer)
    {
        toPositionDimensionTwo = integer;
    }

    public StorageContainer getToStorageContainer()
    {

        return toStorageContainer;
    }

    public void setToStorageContainer(StorageContainer storagecontainer)
    {
        toStorageContainer = storagecontainer;
    }

    public StorageContainer getFromStorageContainer()
    {

        return fromStorageContainer;
    }

    public void setFromStorageContainer(StorageContainer storagecontainer)
    {
        fromStorageContainer = storagecontainer;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof TransferEventParameters)
        {
            TransferEventParameters transfereventparameters = (TransferEventParameters)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(transfereventparameters.getId()))
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
    protected Integer fromPositionDimensionOne;
    protected Integer fromPositionDimensionTwo;
    protected Integer toPositionDimensionOne;
    protected Integer toPositionDimensionTwo;
    private StorageContainer toStorageContainer;
    private StorageContainer fromStorageContainer;
}
