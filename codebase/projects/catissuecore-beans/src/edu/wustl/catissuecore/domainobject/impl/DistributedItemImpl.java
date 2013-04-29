/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.*;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;

public class DistributedItemImpl
    implements Serializable, DistributedItem
{

    public DistributedItemImpl()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public Double getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Double double1)
    {
        quantity = double1;
    }

    public Distribution getDistribution()
    {

        return distribution;
    }

    public void setDistribution(Distribution distribution1)
    {
        distribution = distribution1;
    }

    public Specimen getSpecimen()
    {

        return specimen;
    }

    public void setSpecimen(Specimen specimen1)
    {
        specimen = specimen1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof DistributedItem)
        {
            DistributedItem distributeditem = (DistributedItem)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(distributeditem.getId()))
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
    protected Long id;
    protected Double quantity;
    private Distribution distribution;
    private Specimen specimen;
}
