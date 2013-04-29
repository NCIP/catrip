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
import java.util.*;

public class SpecimenCollectionGroupImpl
    implements Serializable, SpecimenCollectionGroup
{

    public SpecimenCollectionGroupImpl()
    {
        specimenCollection = new HashSet();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public String getClinicalDiagnosis()
    {
        return clinicalDiagnosis;
    }

    public void setClinicalDiagnosis(String s)
    {
        clinicalDiagnosis = s;
    }

    public String getClinicalStatus()
    {
        return clinicalStatus;
    }

    public void setClinicalStatus(String s)
    {
        clinicalStatus = s;
    }

    public String getActivityStatus()
    {
        return activityStatus;
    }

    public void setActivityStatus(String s)
    {
        activityStatus = s;
    }

    public Site getSite()
    {

        return site;
    }

    public void setSite(Site site1)
    {
        site = site1;
    }

    public CollectionProtocolEvent getCollectionProtocolEvent()
    {
      return collectionProtocolEvent;
    }

    public void setCollectionProtocolEvent(CollectionProtocolEvent collectionprotocolevent)
    {
        collectionProtocolEvent = collectionprotocolevent;
    }

    public Collection getSpecimenCollection()
    {
         return specimenCollection;
    }

    public void setSpecimenCollection(Collection collection)
    {
        specimenCollection = collection;
    }

    public CollectionProtocolRegistration getCollectionProtocolRegistration()
    {
       return collectionProtocolRegistration;
    }

    public void setCollectionProtocolRegistration(CollectionProtocolRegistration collectionprotocolregistration)
    {
        collectionProtocolRegistration = collectionprotocolregistration;
    }

    public ClinicalReport getClinicalReport()
    {
       return clinicalReport;
    }

    public void setClinicalReport(ClinicalReport clinicalreport)
    {
        clinicalReport = clinicalreport;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof SpecimenCollectionGroup)
        {
            SpecimenCollectionGroup specimencollectiongroup = (SpecimenCollectionGroup)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(specimencollectiongroup.getId()))
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
    protected String clinicalDiagnosis;
    protected String clinicalStatus;
    protected String activityStatus;
    private Site site;
    private CollectionProtocolEvent collectionProtocolEvent;
    private Collection specimenCollection;
    private CollectionProtocolRegistration collectionProtocolRegistration;
    private ClinicalReport clinicalReport;
}
