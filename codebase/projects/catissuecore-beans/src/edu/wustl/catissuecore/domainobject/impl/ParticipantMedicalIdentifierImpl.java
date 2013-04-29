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

public class ParticipantMedicalIdentifierImpl
    implements Serializable, ParticipantMedicalIdentifier
{

    public ParticipantMedicalIdentifierImpl()
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

    public String getMedicalRecordNumber()
    {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String s)
    {
        medicalRecordNumber = s;
    }

    public Participant getParticipant()
    {

        return participant;
    }

    public void setParticipant(Participant participant1)
    {
        participant = participant1;
    }

    public Site getSite()
    {

        return site;
    }

    public void setSite(Site site1)
    {
        site = site1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof ParticipantMedicalIdentifier)
        {
            ParticipantMedicalIdentifier participantmedicalidentifier = (ParticipantMedicalIdentifier)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(participantmedicalidentifier.getId()))
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
    protected String medicalRecordNumber;
    private Participant participant;
    private Site site;
}
