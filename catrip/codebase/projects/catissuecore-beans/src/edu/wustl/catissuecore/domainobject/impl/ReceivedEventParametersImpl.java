// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.ReceivedEventParameters;
import java.io.Serializable;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            SpecimenEventParametersImpl

public class ReceivedEventParametersImpl extends SpecimenEventParametersImpl
    implements Serializable, ReceivedEventParameters
{

    public ReceivedEventParametersImpl()
    {
    }

    public String getReceivedQuality()
    {
        return receivedQuality;
    }

    public void setReceivedQuality(String s)
    {
        receivedQuality = s;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof ReceivedEventParameters)
        {
            ReceivedEventParameters receivedeventparameters = (ReceivedEventParameters)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(receivedeventparameters.getId()))
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
    protected String receivedQuality;
}
