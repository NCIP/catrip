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

import edu.wustl.catissuecore.domainobject.SpecimenCharacteristics;
import java.io.Serializable;

public class SpecimenCharacteristicsImpl
    implements Serializable, SpecimenCharacteristics
{

    public SpecimenCharacteristicsImpl()
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

    public String getTissueSite()
    {
        return tissueSite;
    }

    public void setTissueSite(String s)
    {
        tissueSite = s;
    }

    public String getTissueSide()
    {
        return tissueSide;
    }

    public void setTissueSide(String s)
    {
        tissueSide = s;
    }

    public String getPathologicalStatus()
    {
        return pathologicalStatus;
    }

    public void setPathologicalStatus(String s)
    {
        pathologicalStatus = s;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof SpecimenCharacteristics)
        {
            SpecimenCharacteristics specimencharacteristics = (SpecimenCharacteristics)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(specimencharacteristics.getId()))
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
    protected String tissueSite;
    protected String tissueSide;
    protected String pathologicalStatus;
}
