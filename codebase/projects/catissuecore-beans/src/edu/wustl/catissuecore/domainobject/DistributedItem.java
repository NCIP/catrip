/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;


// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            Distribution, Specimen

public interface DistributedItem
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract Double getQuantity();

    public abstract void setQuantity(Double double1);

    public abstract Distribution getDistribution();

    public abstract void setDistribution(Distribution distribution);

    public abstract Specimen getSpecimen();

    public abstract void setSpecimen(Specimen specimen);
}
