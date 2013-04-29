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

import edu.wustl.catissuecore.domainobject.*;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

public class SpecimenImpl
    implements Serializable, Specimen
{

    public SpecimenImpl()
    {
        specimenEventCollection = new HashSet();
        childrenSpecimen = new HashSet();
        externalIdentifierCollection = new HashSet();
        biohazardCollection = new HashSet();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String s)
    {
        type = s;
    }

    public Boolean getAvailable()
    {
        return available;
    }

    public void setAvailable(Boolean boolean1)
    {
        available = boolean1;
    }

    public Integer getPositionDimensionOne()
    {
        return positionDimensionOne;
    }

    public void setPositionDimensionOne(Integer integer)
    {
        positionDimensionOne = integer;
    }

    public Integer getPositionDimensionTwo()
    {
        return positionDimensionTwo;
    }

    public void setPositionDimensionTwo(Integer integer)
    {
        positionDimensionTwo = integer;
    }

    public String getBarcode()
    {
        return barcode;
    }

    public void setBarcode(String s)
    {
        barcode = s;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String s)
    {
        comments = s;
    }

    public String getActivityStatus()
    {
        return activityStatus;
    }

    public void setActivityStatus(String s)
    {
        activityStatus = s;
    }

    public Collection getSpecimenEventCollection()
    {

        return specimenEventCollection;
    }

    public void setSpecimenEventCollection(Collection collection)
    {
        specimenEventCollection = collection;
    }

    public Specimen getParentSpecimen()
    {

        return parentSpecimen;
    }

    public void setParentSpecimen(Specimen specimen)
    {
        parentSpecimen = specimen;
    }

    public SpecimenCollectionGroup getSpecimenCollectionGroup()
    {

        return specimenCollectionGroup;
    }

    public void setSpecimenCollectionGroup(SpecimenCollectionGroup specimencollectiongroup)
    {
        specimenCollectionGroup = specimencollectiongroup;
    }

    public Collection getChildrenSpecimen()
    {

        return childrenSpecimen;
    }

    public void setChildrenSpecimen(Collection collection)
    {
        childrenSpecimen = collection;
    }

    public StorageContainer getStorageContainer()
    {

        return storageContainer;
    }

    public void setStorageContainer(StorageContainer storagecontainer)
    {
        storageContainer = storagecontainer;
    }

    public SpecimenCharacteristics getSpecimenCharacteristics()
    {

        return specimenCharacteristics;
    }

    public void setSpecimenCharacteristics(SpecimenCharacteristics specimencharacteristics)
    {
        specimenCharacteristics = specimencharacteristics;
    }

    public Collection getExternalIdentifierCollection()
    {

        return externalIdentifierCollection;
    }

    public void setExternalIdentifierCollection(Collection collection)
    {
        externalIdentifierCollection = collection;
    }

    public Collection getBiohazardCollection()
    {

        return biohazardCollection;
    }

    public void setBiohazardCollection(Collection collection)
    {
        biohazardCollection = collection;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof Specimen)
        {
            Specimen specimen = (Specimen)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(specimen.getId()))
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
    protected String type;
    protected Boolean available;
    protected Integer positionDimensionOne;
    protected Integer positionDimensionTwo;
    protected String barcode;
    protected String comments;
    protected String activityStatus;
    private Collection specimenEventCollection;
    private Specimen parentSpecimen;
    private SpecimenCollectionGroup specimenCollectionGroup;
    private Collection childrenSpecimen;
    private StorageContainer storageContainer;
    private SpecimenCharacteristics specimenCharacteristics;
    private Collection externalIdentifierCollection;
    private Collection biohazardCollection;
}
