// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.EventParameters;
import edu.wustl.catissuecore.domainobject.User;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EventParametersImpl
    implements Serializable, EventParameters
{

    public EventParametersImpl()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public Date getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Date date)
    {
        timestamp = date;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String s)
    {
        comments = s;
    }

    public User getUser()
    {

        return user;
    }

    public void setUser(User user1)
    {
        user = user1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof EventParameters)
        {
            EventParameters eventparameters = (EventParameters)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(eventparameters.getId()))
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
    protected Date timestamp;
    protected String comments;
    private User user;
}
