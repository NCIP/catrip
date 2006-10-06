// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.TissueSpecimenRequirement;
import java.io.Serializable;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            SpecimenRequirementImpl

public class TissueSpecimenRequirementImpl extends SpecimenRequirementImpl
    implements Serializable, TissueSpecimenRequirement
{

    public TissueSpecimenRequirementImpl()
    {
    }

    public Double getQuantityInGram()
    {
        return quantityInGram;
    }

    public void setQuantityInGram(Double double1)
    {
        quantityInGram = double1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof TissueSpecimenRequirement)
        {
            TissueSpecimenRequirement tissuespecimenrequirement = (TissueSpecimenRequirement)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(tissuespecimenrequirement.getId()))
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
    protected Double quantityInGram;
}
