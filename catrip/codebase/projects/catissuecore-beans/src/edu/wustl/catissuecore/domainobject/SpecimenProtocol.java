// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;

import java.util.Date;

// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            User

public interface SpecimenProtocol
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract String getTitle();

    public abstract void setTitle(String s);

    public abstract String getShortTitle();

    public abstract void setShortTitle(String s);

    public abstract String getIrbIdentifier();

    public abstract void setIrbIdentifier(String s);

    public abstract Date getStartDate();

    public abstract void setStartDate(Date date);

    public abstract Date getEndDate();

    public abstract void setEndDate(Date date);

    public abstract Integer getEnrollment();

    public abstract void setEnrollment(Integer integer);

    public abstract String getDescriptionURL();

    public abstract void setDescriptionURL(String s);

    public abstract String getActivityStatus();

    public abstract void setActivityStatus(String s);

    public abstract User getPrincipalInvestigator();

    public abstract void setPrincipalInvestigator(User user);
}
