// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;


// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            SpecimenRequirement

public interface CellSpecimenRequirement
    extends SpecimenRequirement
{

    public abstract Integer getQuantityInCellCount();

    public abstract void setQuantityInCellCount(Integer integer);
}
