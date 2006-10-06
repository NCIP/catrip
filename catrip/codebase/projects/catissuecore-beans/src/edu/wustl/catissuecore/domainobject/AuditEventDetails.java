// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;


// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            AuditEventLog

public interface AuditEventDetails
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract String getElementName();

    public abstract void setElementName(String s);

    public abstract String getPreviousValue();

    public abstract void setPreviousValue(String s);

    public abstract String getCurrentValue();

    public abstract void setCurrentValue(String s);

    public abstract AuditEventLog getAuditEventLog();

    public abstract void setAuditEventLog(AuditEventLog auditeventlog);
}
