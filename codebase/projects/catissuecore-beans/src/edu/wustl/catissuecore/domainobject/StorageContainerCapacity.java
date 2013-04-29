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


public interface StorageContainerCapacity
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract Integer getOneDimensionCapacity();

    public abstract void setOneDimensionCapacity(Integer integer);

    public abstract Integer getTwoDimensionCapacity();

    public abstract void setTwoDimensionCapacity(Integer integer);
}
