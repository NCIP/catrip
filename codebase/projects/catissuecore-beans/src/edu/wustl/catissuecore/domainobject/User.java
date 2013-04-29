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
import java.util.Date;

// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            Department, Address, Institution, CancerResearchGroup

public interface User
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract String getLastName();

    public abstract void setLastName(String s);

    public abstract String getFirstName();

    public abstract void setFirstName(String s);

    public abstract String getPassword();

    public abstract void setPassword(String s);

    public abstract Date getStartDate();

    public abstract void setStartDate(Date date);

    public abstract String getActivityStatus();

    public abstract void setActivityStatus(String s);

    public abstract String getComments();

    public abstract void setComments(String s);

    public abstract String getLoginName();

    public abstract void setLoginName(String s);

    public abstract String getEmailAddress();

    public abstract void setEmailAddress(String s);

    public abstract Long getCsmUserId();

    public abstract void setCsmUserId(Long long1);

    public abstract Department getDepartment();

    public abstract void setDepartment(Department department);

    public abstract Address getAddress();

    public abstract void setAddress(Address address);

    public abstract Institution getInstitution();

    public abstract void setInstitution(Institution institution);

    public abstract CancerResearchGroup getCancerResearchGroup();

    public abstract void setCancerResearchGroup(CancerResearchGroup cancerresearchgroup);

    public abstract Collection getCollectionProtocolCollection();

    public abstract void setCollectionProtocolCollection(Collection collection);
}
