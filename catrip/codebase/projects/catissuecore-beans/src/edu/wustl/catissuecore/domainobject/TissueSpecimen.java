// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;


// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            Specimen

public interface TissueSpecimen
    extends Specimen
{

    public abstract Double getQuantityInGram();

    public abstract void setQuantityInGram(Double double1);

    public abstract Double getAvailableQuantityInGram();

    public abstract void setAvailableQuantityInGram(Double double1);
}
