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
//            Address, User

public interface Site
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract String getName();

    public abstract void setName(String s);

    public abstract String getType();

    public abstract void setType(String s);

    public abstract String getEmailAddress();

    public abstract void setEmailAddress(String s);

    public abstract String getActivityStatus();

    public abstract void setActivityStatus(String s);

    public abstract Address getAddress();

    public abstract void setAddress(Address address);

    public abstract User getCoordinator();

    public abstract void setCoordinator(User user);
}
