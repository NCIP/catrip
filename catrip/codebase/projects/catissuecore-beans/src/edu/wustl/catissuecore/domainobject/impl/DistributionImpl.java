// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.*;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            SpecimenEventParametersImpl

public class DistributionImpl extends SpecimenEventParametersImpl
    implements Serializable, Distribution
{

    public DistributionImpl()
    {
        distributedItemCollection = new HashSet();
    }

    public String getActivityStatus()
    {
        return activityStatus;
    }

    public void setActivityStatus(String s)
    {
        activityStatus = s;
    }

    public Collection getDistributedItemCollection()
    {

        return distributedItemCollection;
    }

    public void setDistributedItemCollection(Collection collection)
    {
        distributedItemCollection = collection;
    }

    public DistributionProtocol getDistributionProtocol()
    {

        return distributionProtocol;
    }

    public void setDistributionProtocol(DistributionProtocol distributionprotocol)
    {
        distributionProtocol = distributionprotocol;
    }

    public Site getToSite()
    {

        return toSite;
    }

    public void setToSite(Site site)
    {
        toSite = site;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof Distribution)
        {
            Distribution distribution = (Distribution)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(distribution.getId()))
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
    protected String activityStatus;
    private Collection distributedItemCollection;
    private DistributionProtocol distributionProtocol;
    private Site toSite;
}
