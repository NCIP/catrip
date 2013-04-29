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

import edu.wustl.catissuecore.domainobject.CollectionProtocol;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

// Referenced classes of package edu.wustl.catissuecore.domainobject.impl:
//            SpecimenProtocolImpl

public class CollectionProtocolImpl extends SpecimenProtocolImpl
    implements Serializable, CollectionProtocol
{

    public CollectionProtocolImpl()
    {
        distributionProtocolCollection = new HashSet();
        userCollection = new HashSet();
        collectionProtocolEventCollection = new HashSet();
    }

    public Collection getDistributionProtocolCollection()
    {

        return distributionProtocolCollection;
    }

    public void setDistributionProtocolCollection(Collection collection)
    {
        distributionProtocolCollection = collection;
    }

    public Collection getUserCollection()
    {

        return userCollection;
    }

    public void setUserCollection(Collection collection)
    {
        userCollection = collection;
    }

    public Collection getCollectionProtocolEventCollection()
    {

        return collectionProtocolEventCollection;
    }

    public void setCollectionProtocolEventCollection(Collection collection)
    {
        collectionProtocolEventCollection = collection;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof CollectionProtocol)
        {
            CollectionProtocol collectionprotocol = (CollectionProtocol)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(collectionprotocol.getId()))
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
    private Collection distributionProtocolCollection;
    private Collection userCollection;
    private Collection collectionProtocolEventCollection;
}
