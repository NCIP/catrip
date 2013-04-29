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

public class UserImpl
    implements Serializable, User
{

    public UserImpl()
    {
        collectionProtocolCollection = new HashSet();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String s)
    {
        lastName = s;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String s)
    {
        firstName = s;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String s)
    {
        password = s;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date date)
    {
        startDate = date;
    }

    public String getActivityStatus()
    {
        return activityStatus;
    }

    public void setActivityStatus(String s)
    {
        activityStatus = s;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String s)
    {
        comments = s;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String s)
    {
        loginName = s;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String s)
    {
        emailAddress = s;
    }

    public Long getCsmUserId()
    {
        return csmUserId;
    }

    public void setCsmUserId(Long long1)
    {
        csmUserId = long1;
    }

    public Department getDepartment()
    {
     
        return department;
    }

    public void setDepartment(Department department1)
    {
        department = department1;
    }

    public Address getAddress()
    {
      
        return address;
    }

    public void setAddress(Address address1)
    {
        address = address1;
    }

    public Institution getInstitution()
    {
       
        return institution;
    }

    public void setInstitution(Institution institution1)
    {
        institution = institution1;
    }

    public CancerResearchGroup getCancerResearchGroup()
    {
        
        return cancerResearchGroup;
    }

    public void setCancerResearchGroup(CancerResearchGroup cancerresearchgroup)
    {
        cancerResearchGroup = cancerresearchgroup;
    }

    public Collection getCollectionProtocolCollection()
    {
       
        return collectionProtocolCollection;
    }

    public void setCollectionProtocolCollection(Collection collection)
    {
        collectionProtocolCollection = collection;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof User)
        {
            User user = (User)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(user.getId()))
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
    protected String lastName;
    protected String firstName;
    protected String password;
    protected Date startDate;
    protected String activityStatus;
    protected String comments;
    protected String loginName;
    protected String emailAddress;
    protected Long csmUserId;
    private Department department;
    private Address address;
    private Institution institution;
    private CancerResearchGroup cancerResearchGroup;
    private Collection collectionProtocolCollection;
}
