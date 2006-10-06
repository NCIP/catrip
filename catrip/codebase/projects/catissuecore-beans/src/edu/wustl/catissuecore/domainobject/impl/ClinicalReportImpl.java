// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.ClinicalReport;
import edu.wustl.catissuecore.domainobject.ParticipantMedicalIdentifier;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;

public class ClinicalReportImpl
    implements Serializable, ClinicalReport
{

    public ClinicalReportImpl()
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

    public String getSurgicalPathologyNumber()
    {
        return surgicalPathologyNumber;
    }

    public void setSurgicalPathologyNumber(String s)
    {
        surgicalPathologyNumber = s;
    }

    public ParticipantMedicalIdentifier getParticipantMedicalIdentifier()
    {

        return participantMedicalIdentifier;
    }

    public void setParticipantMedicalIdentifier(ParticipantMedicalIdentifier participantmedicalidentifier)
    {
        participantMedicalIdentifier = participantmedicalidentifier;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof ClinicalReport)
        {
            ClinicalReport clinicalreport = (ClinicalReport)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(clinicalreport.getId()))
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
    protected String surgicalPathologyNumber;
    private ParticipantMedicalIdentifier participantMedicalIdentifier;
}
