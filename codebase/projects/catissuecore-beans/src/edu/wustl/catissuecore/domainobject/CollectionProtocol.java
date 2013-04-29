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

import java.util.Collection;

// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            SpecimenProtocol

public interface CollectionProtocol
    extends SpecimenProtocol
{

    public abstract Collection getDistributionProtocolCollection();

    public abstract void setDistributionProtocolCollection(Collection collection);

    public abstract Collection getUserCollection();

    public abstract void setUserCollection(Collection collection);

    public abstract Collection getCollectionProtocolEventCollection();

    public abstract void setCollectionProtocolEventCollection(Collection collection);
}
