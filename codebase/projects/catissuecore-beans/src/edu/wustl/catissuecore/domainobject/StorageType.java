/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;


// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            StorageContainerCapacity

public interface StorageType
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract String getType();

    public abstract void setType(String s);

    public abstract Double getDefaultTempratureInCentigrade();

    public abstract void setDefaultTempratureInCentigrade(Double double1);

    public abstract String getOneDimensionLabel();

    public abstract void setOneDimensionLabel(String s);

    public abstract String getTwoDimensionLabel();

    public abstract void setTwoDimensionLabel(String s);

    public abstract StorageContainerCapacity getDefaultStorageCapacity();

    public abstract void setDefaultStorageCapacity(StorageContainerCapacity storagecontainercapacity);
}
