// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;


// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            ReviewEventParameters

public interface CellSpecimenReviewParameters
    extends ReviewEventParameters
{

    public abstract Double getNeoplasticCellularityPercentage();

    public abstract void setNeoplasticCellularityPercentage(Double double1);

    public abstract Double getViableCellPercentage();

    public abstract void setViableCellPercentage(Double double1);
}
