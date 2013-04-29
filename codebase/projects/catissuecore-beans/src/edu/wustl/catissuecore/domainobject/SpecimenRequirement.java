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

import java.util.Collection;

public interface SpecimenRequirement
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract String getSpecimenType();

    public abstract void setSpecimenType(String s);

    public abstract String getTissueSite();

    public abstract void setTissueSite(String s);

    public abstract String getPathologyStatus();

    public abstract void setPathologyStatus(String s);

    public abstract Collection getDistributionProtocolCollection();

    public abstract void setDistributionProtocolCollection(Collection collection);

    public abstract Collection getCollectionProtocolEventCollection();

    public abstract void setCollectionProtocolEventCollection(Collection collection);
}
