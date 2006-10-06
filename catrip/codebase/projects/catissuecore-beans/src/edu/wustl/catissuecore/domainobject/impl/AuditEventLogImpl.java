// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.AuditEvent;
import edu.wustl.catissuecore.domainobject.AuditEventLog;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

public class AuditEventLogImpl
    implements Serializable, AuditEventLog
{

    public AuditEventLogImpl()
    {
        auditEventDetailsCollcetion = new HashSet();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public Long getObjectIdentifier()
    {
        return objectIdentifier;
    }

    public void setObjectIdentifier(Long long1)
    {
        objectIdentifier = long1;
    }

    public String getObjectName()
    {
        return ObjectName;
    }

    public void setObjectName(String s)
    {
        ObjectName = s;
    }

    public String getEventType()
    {
        return eventType;
    }

    public void setEventType(String s)
    {
        eventType = s;
    }

    public Collection getAuditEventDetailsCollcetion()
    {

        return auditEventDetailsCollcetion;
    }

    public void setAuditEventDetailsCollcetion(Collection collection)
    {
        auditEventDetailsCollcetion = collection;
    }

    public AuditEvent getAuditEvent()
    {

        return auditEvent;
    }

    public void setAuditEvent(AuditEvent auditevent)
    {
        auditEvent = auditevent;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof AuditEventLog)
        {
            AuditEventLog auditeventlog = (AuditEventLog)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(auditeventlog.getId()))
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
    protected Long objectIdentifier;
    protected String ObjectName;
    protected String eventType;
    private Collection auditEventDetailsCollcetion;
    private AuditEvent auditEvent;
}
