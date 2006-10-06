// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;

import java.util.Collection;

// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            AuditEvent

public interface AuditEventLog
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract Long getObjectIdentifier();

    public abstract void setObjectIdentifier(Long long1);

    public abstract String getObjectName();

    public abstract void setObjectName(String s);

    public abstract String getEventType();

    public abstract void setEventType(String s);

    public abstract Collection getAuditEventDetailsCollcetion();

    public abstract void setAuditEventDetailsCollcetion(Collection collection);

    public abstract AuditEvent getAuditEvent();

    public abstract void setAuditEvent(AuditEvent auditevent);
}
