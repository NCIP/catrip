// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.CellSpecimenReviewParameters;
import java.io.Serializable;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            ReviewEventParametersImpl

public class CellSpecimenReviewParametersImpl extends ReviewEventParametersImpl
    implements Serializable, CellSpecimenReviewParameters
{

    public CellSpecimenReviewParametersImpl()
    {
    }

    public Double getNeoplasticCellularityPercentage()
    {
        return neoplasticCellularityPercentage;
    }

    public void setNeoplasticCellularityPercentage(Double double1)
    {
        neoplasticCellularityPercentage = double1;
    }

    public Double getViableCellPercentage()
    {
        return viableCellPercentage;
    }

    public void setViableCellPercentage(Double double1)
    {
        viableCellPercentage = double1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof CellSpecimenReviewParameters)
        {
            CellSpecimenReviewParameters cellspecimenreviewparameters = (CellSpecimenReviewParameters)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(cellspecimenreviewparameters.getId()))
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
    protected Double neoplasticCellularityPercentage;
    protected Double viableCellPercentage;
}
