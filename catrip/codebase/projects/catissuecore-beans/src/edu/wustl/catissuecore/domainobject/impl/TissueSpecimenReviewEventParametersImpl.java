// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.TissueSpecimenReviewEventParameters;
import java.io.Serializable;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            ReviewEventParametersImpl

public class TissueSpecimenReviewEventParametersImpl extends ReviewEventParametersImpl
    implements Serializable, TissueSpecimenReviewEventParameters
{

    public TissueSpecimenReviewEventParametersImpl()
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

    public Double getNecrosisPercentage()
    {
        return necrosisPercentage;
    }

    public void setNecrosisPercentage(Double double1)
    {
        necrosisPercentage = double1;
    }

    public Double getLymphocyticPercentage()
    {
        return lymphocyticPercentage;
    }

    public void setLymphocyticPercentage(Double double1)
    {
        lymphocyticPercentage = double1;
    }

    public Double getTotalCellularityPercentage()
    {
        return totalCellularityPercentage;
    }

    public void setTotalCellularityPercentage(Double double1)
    {
        totalCellularityPercentage = double1;
    }

    public String getHistologicalQuality()
    {
        return histologicalQuality;
    }

    public void setHistologicalQuality(String s)
    {
        histologicalQuality = s;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof TissueSpecimenReviewEventParameters)
        {
            TissueSpecimenReviewEventParameters tissuespecimenrevieweventparameters = (TissueSpecimenReviewEventParameters)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(tissuespecimenrevieweventparameters.getId()))
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
    protected Double necrosisPercentage;
    protected Double lymphocyticPercentage;
    protected Double totalCellularityPercentage;
    protected String histologicalQuality;
}
