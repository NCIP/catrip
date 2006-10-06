// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.MolecularSpecimenRequirement;
import java.io.Serializable;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            SpecimenRequirementImpl

public class MolecularSpecimenRequirementImpl extends SpecimenRequirementImpl
    implements Serializable, MolecularSpecimenRequirement
{

    public MolecularSpecimenRequirementImpl()
    {
    }

    public Double getQuantityInMicrogram()
    {
        return quantityInMicrogram;
    }

    public void setQuantityInMicrogram(Double double1)
    {
        quantityInMicrogram = double1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof MolecularSpecimenRequirement)
        {
            MolecularSpecimenRequirement molecularspecimenrequirement = (MolecularSpecimenRequirement)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(molecularspecimenrequirement.getId()))
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
    protected Double quantityInMicrogram;
}
