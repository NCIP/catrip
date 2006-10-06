// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.FluidSpecimen;
import java.io.Serializable;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            SpecimenImpl

public class FluidSpecimenImpl extends SpecimenImpl
    implements Serializable, FluidSpecimen
{

    public FluidSpecimenImpl()
    {
    }

    public Double getQuantityInMilliliter()
    {
        return quantityInMilliliter;
    }

    public void setQuantityInMilliliter(Double double1)
    {
        quantityInMilliliter = double1;
    }

    public Double getAvailableQuantityInMilliliter()
    {
        return availableQuantityInMilliliter;
    }

    public void setAvailableQuantityInMilliliter(Double double1)
    {
        availableQuantityInMilliliter = double1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof FluidSpecimen)
        {
            FluidSpecimen fluidspecimen = (FluidSpecimen)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(fluidspecimen.getId()))
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
    protected Double quantityInMilliliter;
    protected Double availableQuantityInMilliliter;
}
