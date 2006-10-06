// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.DistributionProtocol;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            SpecimenProtocolImpl

public class DistributionProtocolImpl extends SpecimenProtocolImpl
    implements Serializable, DistributionProtocol
{

    public DistributionProtocolImpl()
    {
        specimenRequirementCollection = new HashSet();
        collectionProtocolCollection = new HashSet();
    }

    public Collection getSpecimenRequirementCollection()
    {

        return specimenRequirementCollection;
    }

    public void setSpecimenRequirementCollection(Collection collection)
    {
        specimenRequirementCollection = collection;
    }

    public Collection getCollectionProtocolCollection()
    {

        return collectionProtocolCollection;
    }

    public void setCollectionProtocolCollection(Collection collection)
    {
        collectionProtocolCollection = collection;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof DistributionProtocol)
        {
            DistributionProtocol distributionprotocol = (DistributionProtocol)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(distributionprotocol.getId()))
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
    private Collection specimenRequirementCollection;
    private Collection collectionProtocolCollection;
}
