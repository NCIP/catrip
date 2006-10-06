// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.AuditEventDetails;
import edu.wustl.catissuecore.domainobject.AuditEventLog;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;

public class AuditEventDetailsImpl
    implements Serializable, AuditEventDetails
{

    public AuditEventDetailsImpl()
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

    public String getElementName()
    {
        return elementName;
    }

    public void setElementName(String s)
    {
        elementName = s;
    }

    public String getPreviousValue()
    {
        return previousValue;
    }

    public void setPreviousValue(String s)
    {
        previousValue = s;
    }

    public String getCurrentValue()
    {
        return currentValue;
    }

    public void setCurrentValue(String s)
    {
        currentValue = s;
    }

    public AuditEventLog getAuditEventLog()
    {

        return auditEventLog;
    }

    public void setAuditEventLog(AuditEventLog auditeventlog)
    {
        auditEventLog = auditeventlog;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof AuditEventDetails)
        {
            AuditEventDetails auditeventdetails = (AuditEventDetails)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(auditeventdetails.getId()))
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
    protected String elementName;
    protected String previousValue;
    protected String currentValue;
    private AuditEventLog auditEventLog;
}
