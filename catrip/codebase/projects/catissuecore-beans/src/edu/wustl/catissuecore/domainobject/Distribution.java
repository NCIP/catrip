// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;

import java.util.Collection;

// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            SpecimenEventParameters, DistributionProtocol, Site

public interface Distribution
    extends SpecimenEventParameters
{

    public abstract String getActivityStatus();

    public abstract void setActivityStatus(String s);

    public abstract Collection getDistributedItemCollection();

    public abstract void setDistributedItemCollection(Collection collection);

    public abstract DistributionProtocol getDistributionProtocol();

    public abstract void setDistributionProtocol(DistributionProtocol distributionprotocol);

    public abstract Site getToSite();

    public abstract void setToSite(Site site);
}
