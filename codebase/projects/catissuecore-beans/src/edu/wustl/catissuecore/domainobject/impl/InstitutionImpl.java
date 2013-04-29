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

import edu.wustl.catissuecore.domainobject.Institution;
import java.io.Serializable;

public class InstitutionImpl
    implements Serializable, Institution
{

    public InstitutionImpl()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String s)
    {
        name = s;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof Institution)
        {
            Institution institution = (Institution)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(institution.getId()))
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
    protected Long id;
    protected String name;
}
