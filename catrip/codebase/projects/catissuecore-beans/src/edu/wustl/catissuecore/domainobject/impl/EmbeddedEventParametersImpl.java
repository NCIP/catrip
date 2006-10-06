// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.EmbeddedEventParameters;
import java.io.Serializable;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            SpecimenEventParametersImpl

public class EmbeddedEventParametersImpl extends SpecimenEventParametersImpl
    implements Serializable, EmbeddedEventParameters
{

    public EmbeddedEventParametersImpl()
    {
    }

    public String getEmbeddingMedium()
    {
        return embeddingMedium;
    }

    public void setEmbeddingMedium(String s)
    {
        embeddingMedium = s;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof EmbeddedEventParameters)
        {
            EmbeddedEventParameters embeddedeventparameters = (EmbeddedEventParameters)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(embeddedeventparameters.getId()))
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
    protected String embeddingMedium;
}
