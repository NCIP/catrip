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

import edu.wustl.catissuecore.domainobject.MolecularSpecimen;
import java.io.Serializable;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            SpecimenImpl

public class MolecularSpecimenImpl extends SpecimenImpl
    implements Serializable, MolecularSpecimen
{

    public MolecularSpecimenImpl()
    {
    }

    public Double getConcentrationInMicrogramPerMicroliter()
    {
        return concentrationInMicrogramPerMicroliter;
    }

    public void setConcentrationInMicrogramPerMicroliter(Double double1)
    {
        concentrationInMicrogramPerMicroliter = double1;
    }

    public Double getQuantityInMicrogram()
    {
        return quantityInMicrogram;
    }

    public void setQuantityInMicrogram(Double double1)
    {
        quantityInMicrogram = double1;
    }

    public Double getAvailableQuantityInMicrogram()
    {
        return availableQuantityInMicrogram;
    }

    public void setAvailableQuantityInMicrogram(Double double1)
    {
        availableQuantityInMicrogram = double1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof MolecularSpecimen)
        {
            MolecularSpecimen molecularspecimen = (MolecularSpecimen)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(molecularspecimen.getId()))
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
    protected Double concentrationInMicrogramPerMicroliter;
    protected Double quantityInMicrogram;
    protected Double availableQuantityInMicrogram;
}
