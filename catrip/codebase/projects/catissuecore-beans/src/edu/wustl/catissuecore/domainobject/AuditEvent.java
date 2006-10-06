// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;

import java.util.Collection;

// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            EventParameters

public interface AuditEvent
    extends EventParameters
{

    public abstract String getIpAddress();

    public abstract void setIpAddress(String s);

    public abstract Collection getAuditEventLogCollection();

    public abstract void setAuditEventLogCollection(Collection collection);
}
