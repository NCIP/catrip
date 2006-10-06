// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.FluidSpecimenReviewEventParameters;
import java.io.Serializable;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            ReviewEventParametersImpl

public class FluidSpecimenReviewEventParametersImpl extends ReviewEventParametersImpl
    implements Serializable, FluidSpecimenReviewEventParameters
{

    public FluidSpecimenReviewEventParametersImpl()
    {
    }

    public Double getCellCount()
    {
        return cellCount;
    }

    public void setCellCount(Double double1)
    {
        cellCount = double1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof FluidSpecimenReviewEventParameters)
        {
            FluidSpecimenReviewEventParameters fluidspecimenrevieweventparameters = (FluidSpecimenReviewEventParameters)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(fluidspecimenrevieweventparameters.getId()))
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
    protected Double cellCount;
}
