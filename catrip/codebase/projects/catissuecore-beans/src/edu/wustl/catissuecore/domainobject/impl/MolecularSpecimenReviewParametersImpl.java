// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.MolecularSpecimenReviewParameters;
import java.io.Serializable;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            ReviewEventParametersImpl

public class MolecularSpecimenReviewParametersImpl extends ReviewEventParametersImpl
    implements Serializable, MolecularSpecimenReviewParameters
{

    public MolecularSpecimenReviewParametersImpl()
    {
    }

    public String getGelImageURL()
    {
        return gelImageURL;
    }

    public void setGelImageURL(String s)
    {
        gelImageURL = s;
    }

    public String getQualityIndex()
    {
        return qualityIndex;
    }

    public void setQualityIndex(String s)
    {
        qualityIndex = s;
    }

    public String getLaneNumber()
    {
        return laneNumber;
    }

    public void setLaneNumber(String s)
    {
        laneNumber = s;
    }

    public Integer getGelNumber()
    {
        return gelNumber;
    }

    public void setGelNumber(Integer integer)
    {
        gelNumber = integer;
    }

    public Double getAbsorbanceAt260()
    {
        return absorbanceAt260;
    }

    public void setAbsorbanceAt260(Double double1)
    {
        absorbanceAt260 = double1;
    }

    public Double getAbsorbanceAt280()
    {
        return absorbanceAt280;
    }

    public void setAbsorbanceAt280(Double double1)
    {
        absorbanceAt280 = double1;
    }

    public Double getRatio28STo18S()
    {
        return ratio28STo18S;
    }

    public void setRatio28STo18S(Double double1)
    {
        ratio28STo18S = double1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof MolecularSpecimenReviewParameters)
        {
            MolecularSpecimenReviewParameters molecularspecimenreviewparameters = (MolecularSpecimenReviewParameters)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(molecularspecimenreviewparameters.getId()))
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
    protected String gelImageURL;
    protected String qualityIndex;
    protected String laneNumber;
    protected Integer gelNumber;
    protected Double absorbanceAt260;
    protected Double absorbanceAt280;
    protected Double ratio28STo18S;
}
