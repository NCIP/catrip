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

import edu.wustl.catissuecore.domainobject.SpecimenProtocol;
import edu.wustl.catissuecore.domainobject.User;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SpecimenProtocolImpl
    implements Serializable, SpecimenProtocol
{

    public SpecimenProtocolImpl()
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

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public String getShortTitle()
    {
        return shortTitle;
    }

    public void setShortTitle(String s)
    {
        shortTitle = s;
    }

    public String getIrbIdentifier()
    {
        return irbIdentifier;
    }

    public void setIrbIdentifier(String s)
    {
        irbIdentifier = s;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date date)
    {
        startDate = date;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date date)
    {
        endDate = date;
    }

    public Integer getEnrollment()
    {
        return enrollment;
    }

    public void setEnrollment(Integer integer)
    {
        enrollment = integer;
    }

    public String getDescriptionURL()
    {
        return descriptionURL;
    }

    public void setDescriptionURL(String s)
    {
        descriptionURL = s;
    }

    public String getActivityStatus()
    {
        return activityStatus;
    }

    public void setActivityStatus(String s)
    {
        activityStatus = s;
    }

    public User getPrincipalInvestigator()
    {

        return principalInvestigator;
    }

    public void setPrincipalInvestigator(User user)
    {
        principalInvestigator = user;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof SpecimenProtocol)
        {
            SpecimenProtocol specimenprotocol = (SpecimenProtocol)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(specimenprotocol.getId()))
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
    protected String title;
    protected String shortTitle;
    protected String irbIdentifier;
    protected Date startDate;
    protected Date endDate;
    protected Integer enrollment;
    protected String descriptionURL;
    protected String activityStatus;
    private User principalInvestigator;
}
