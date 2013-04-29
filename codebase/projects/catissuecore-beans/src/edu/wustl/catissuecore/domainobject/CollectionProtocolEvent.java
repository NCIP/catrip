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

// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            CollectionProtocol

public interface CollectionProtocolEvent
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract String getClinicalStatus();

    public abstract void setClinicalStatus(String s);

    public abstract Double getStudyCalendarEventPoint();

    public abstract void setStudyCalendarEventPoint(Double double1);

    public abstract CollectionProtocol getCollectionProtocol();

    public abstract void setCollectionProtocol(CollectionProtocol collectionprotocol);

    public abstract Collection getSpecimenRequirementCollection();

    public abstract void setSpecimenRequirementCollection(Collection collection);
}
