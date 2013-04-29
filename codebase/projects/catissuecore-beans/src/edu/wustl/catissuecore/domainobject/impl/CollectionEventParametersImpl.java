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

import edu.wustl.catissuecore.domainobject.CollectionEventParameters;
import java.io.Serializable;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            SpecimenEventParametersImpl

public class CollectionEventParametersImpl extends SpecimenEventParametersImpl
    implements Serializable, CollectionEventParameters
{

    public CollectionEventParametersImpl()
    {
    }

    public String getCollectionProcedure()
    {
        return collectionProcedure;
    }

    public void setCollectionProcedure(String s)
    {
        collectionProcedure = s;
    }

    public String getContainer()
    {
        return container;
    }

    public void setContainer(String s)
    {
        container = s;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof CollectionEventParameters)
        {
            CollectionEventParameters collectioneventparameters = (CollectionEventParameters)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(collectioneventparameters.getId()))
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
    protected String collectionProcedure;
    protected String container;
}
