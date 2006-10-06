// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;


// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            ReviewEventParameters

public interface MolecularSpecimenReviewParameters
    extends ReviewEventParameters
{

    public abstract String getGelImageURL();

    public abstract void setGelImageURL(String s);

    public abstract String getQualityIndex();

    public abstract void setQualityIndex(String s);

    public abstract String getLaneNumber();

    public abstract void setLaneNumber(String s);

    public abstract Integer getGelNumber();

    public abstract void setGelNumber(Integer integer);

    public abstract Double getAbsorbanceAt260();

    public abstract void setAbsorbanceAt260(Double double1);

    public abstract Double getAbsorbanceAt280();

    public abstract void setAbsorbanceAt280(Double double1);

    public abstract Double getRatio28STo18S();

    public abstract void setRatio28STo18S(Double double1);
}
