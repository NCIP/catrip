// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.SpunEventParameters;
import java.io.Serializable;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            SpecimenEventParametersImpl

public class SpunEventParametersImpl extends SpecimenEventParametersImpl
    implements Serializable, SpunEventParameters
{

    public SpunEventParametersImpl()
    {
    }

    public Double getGForce()
    {
        return gForce;
    }

    public void setGForce(Double double1)
    {
        gForce = double1;
    }

    public Integer getDurationInMinutes()
    {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Integer integer)
    {
        durationInMinutes = integer;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof SpunEventParameters)
        {
            SpunEventParameters spuneventparameters = (SpunEventParameters)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(spuneventparameters.getId()))
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
    protected Double gForce;
    protected Integer durationInMinutes;
}
