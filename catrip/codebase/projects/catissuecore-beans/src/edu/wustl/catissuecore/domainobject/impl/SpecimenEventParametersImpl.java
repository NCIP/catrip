// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.Specimen;
import edu.wustl.catissuecore.domainobject.SpecimenEventParameters;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            EventParametersImpl

public class SpecimenEventParametersImpl extends EventParametersImpl
    implements Serializable, SpecimenEventParameters
{

    public SpecimenEventParametersImpl()
    {
    }

    public Specimen getSpecimen()
    {

        return specimen;
    }

    public void setSpecimen(Specimen specimen1)
    {
        specimen = specimen1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof SpecimenEventParameters)
        {
            SpecimenEventParameters specimeneventparameters = (SpecimenEventParameters)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(specimeneventparameters.getId()))
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
    private Specimen specimen;
}
