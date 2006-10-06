// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;

import java.util.Collection;

// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            SpecimenCollectionGroup, StorageContainer, SpecimenCharacteristics

public interface Specimen
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract String getType();

    public abstract void setType(String s);

    public abstract Boolean getAvailable();

    public abstract void setAvailable(Boolean boolean1);

    public abstract Integer getPositionDimensionOne();

    public abstract void setPositionDimensionOne(Integer integer);

    public abstract Integer getPositionDimensionTwo();

    public abstract void setPositionDimensionTwo(Integer integer);

    public abstract String getBarcode();

    public abstract void setBarcode(String s);

    public abstract String getComments();

    public abstract void setComments(String s);

    public abstract String getActivityStatus();

    public abstract void setActivityStatus(String s);

    public abstract Collection getSpecimenEventCollection();

    public abstract void setSpecimenEventCollection(Collection collection);

    public abstract Specimen getParentSpecimen();

    public abstract void setParentSpecimen(Specimen specimen);

    public abstract SpecimenCollectionGroup getSpecimenCollectionGroup();

    public abstract void setSpecimenCollectionGroup(SpecimenCollectionGroup specimencollectiongroup);

    public abstract Collection getChildrenSpecimen();

    public abstract void setChildrenSpecimen(Collection collection);

    public abstract StorageContainer getStorageContainer();

    public abstract void setStorageContainer(StorageContainer storagecontainer);

    public abstract SpecimenCharacteristics getSpecimenCharacteristics();

    public abstract void setSpecimenCharacteristics(SpecimenCharacteristics specimencharacteristics);

    public abstract Collection getExternalIdentifierCollection();

    public abstract void setExternalIdentifierCollection(Collection collection);

    public abstract Collection getBiohazardCollection();

    public abstract void setBiohazardCollection(Collection collection);
}
