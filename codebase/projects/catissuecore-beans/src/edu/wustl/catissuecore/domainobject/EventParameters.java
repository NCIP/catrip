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

import java.util.Date;

// Referenced classes of package edu.wustl.catissuecore.domainobject:
//            User

public interface EventParameters
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract Date getTimestamp();

    public abstract void setTimestamp(Date date);

    public abstract String getComments();

    public abstract void setComments(String s);

    public abstract User getUser();

    public abstract void setUser(User user);
}
