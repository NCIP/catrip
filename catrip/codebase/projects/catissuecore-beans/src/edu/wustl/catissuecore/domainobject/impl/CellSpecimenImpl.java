// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.CellSpecimen;
import java.io.Serializable;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            SpecimenImpl

public class CellSpecimenImpl extends SpecimenImpl
    implements Serializable, CellSpecimen
{

    public CellSpecimenImpl()
    {
    }

    public Integer getQuantityInCellCount()
    {
        return quantityInCellCount;
    }

    public void setQuantityInCellCount(Integer integer)
    {
        quantityInCellCount = integer;
    }

    public Integer getAvailableQuantityInCellCount()
    {
        return availableQuantityInCellCount;
    }

    public void setAvailableQuantityInCellCount(Integer integer)
    {
        availableQuantityInCellCount = integer;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof CellSpecimen)
        {
            CellSpecimen cellspecimen = (CellSpecimen)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(cellspecimen.getId()))
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
    protected Integer quantityInCellCount;
    protected Integer availableQuantityInCellCount;
}