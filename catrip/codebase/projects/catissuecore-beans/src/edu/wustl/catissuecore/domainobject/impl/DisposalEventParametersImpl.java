// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.DisposalEventParameters;
import java.io.Serializable;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            SpecimenEventParametersImpl

public class DisposalEventParametersImpl extends SpecimenEventParametersImpl
    implements Serializable, DisposalEventParameters
{

    public DisposalEventParametersImpl()
    {
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String s)
    {
        reason = s;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof DisposalEventParameters)
        {
            DisposalEventParameters disposaleventparameters = (DisposalEventParameters)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(disposaleventparameters.getId()))
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
    protected String reason;
}