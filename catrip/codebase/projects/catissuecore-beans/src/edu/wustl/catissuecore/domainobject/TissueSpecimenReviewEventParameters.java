// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;


// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            ReviewEventParameters

public interface TissueSpecimenReviewEventParameters
    extends ReviewEventParameters
{

    public abstract Double getNeoplasticCellularityPercentage();

    public abstract void setNeoplasticCellularityPercentage(Double double1);

    public abstract Double getNecrosisPercentage();

    public abstract void setNecrosisPercentage(Double double1);

    public abstract Double getLymphocyticPercentage();

    public abstract void setLymphocyticPercentage(Double double1);

    public abstract Double getTotalCellularityPercentage();

    public abstract void setTotalCellularityPercentage(Double double1);

    public abstract String getHistologicalQuality();

    public abstract void setHistologicalQuality(String s);
}
