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
//            Participant, Site

public interface ParticipantMedicalIdentifier
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract String getMedicalRecordNumber();

    public abstract void setMedicalRecordNumber(String s);

    public abstract Participant getParticipant();

    public abstract void setParticipant(Participant participant);

    public abstract Site getSite();

    public abstract void setSite(Site site);
}
