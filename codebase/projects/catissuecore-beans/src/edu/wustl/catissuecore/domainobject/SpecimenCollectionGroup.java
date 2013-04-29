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
//            Site, CollectionProtocolEvent, CollectionProtocolRegistration, ClinicalReport

public interface SpecimenCollectionGroup
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract String getClinicalDiagnosis();

    public abstract void setClinicalDiagnosis(String s);

    public abstract String getClinicalStatus();

    public abstract void setClinicalStatus(String s);

    public abstract String getActivityStatus();

    public abstract void setActivityStatus(String s);

    public abstract Site getSite();

    public abstract void setSite(Site site);

    public abstract CollectionProtocolEvent getCollectionProtocolEvent();

    public abstract void setCollectionProtocolEvent(CollectionProtocolEvent collectionprotocolevent);

    public abstract Collection getSpecimenCollection();

    public abstract void setSpecimenCollection(Collection collection);

    public abstract CollectionProtocolRegistration getCollectionProtocolRegistration();

    public abstract void setCollectionProtocolRegistration(CollectionProtocolRegistration collectionprotocolregistration);

    public abstract ClinicalReport getClinicalReport();

    public abstract void setClinicalReport(ClinicalReport clinicalreport);
}
