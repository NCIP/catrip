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

import edu.wustl.catissuecore.domainobject.AuditEvent;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            EventParametersImpl

public class AuditEventImpl extends EventParametersImpl
    implements Serializable, AuditEvent
{

    public AuditEventImpl()
    {
        auditEventLogCollection = new HashSet();
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String s)
    {
        ipAddress = s;
    }

    public Collection getAuditEventLogCollection()
    {

        return auditEventLogCollection;
    }

    public void setAuditEventLogCollection(Collection collection)
    {
        auditEventLogCollection = collection;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof AuditEvent)
        {
            AuditEvent auditevent = (AuditEvent)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(auditevent.getId()))
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
    protected String ipAddress;
    private Collection auditEventLogCollection;
}
