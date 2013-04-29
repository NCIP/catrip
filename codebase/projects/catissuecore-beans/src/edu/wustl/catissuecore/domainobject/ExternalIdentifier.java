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
//            Specimen

public interface ExternalIdentifier
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract String getName();

    public abstract void setName(String s);

    public abstract String getValue();

    public abstract void setValue(String s);

    public abstract Specimen getSpecimen();

    public abstract void setSpecimen(Specimen specimen);
}
