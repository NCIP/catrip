// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject.impl;

import edu.wustl.catissuecore.domainobject.Address;
import java.io.Serializable;

public class AddressImpl
    implements Serializable, Address
{

    public AddressImpl()
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

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String s)
    {
        street = s;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String s)
    {
        city = s;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String s)
    {
        state = s;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String s)
    {
        country = s;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String s)
    {
        zipCode = s;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String s)
    {
        phoneNumber = s;
    }

    public String getFaxNumber()
    {
        return faxNumber;
    }

    public void setFaxNumber(String s)
    {
        faxNumber = s;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof Address)
        {
            Address address = (Address)obj;
            Long long1 = getId();
            if(long1 != null && long1.equals(address.getId()))
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
    protected String street;
    protected String city;
    protected String state;
    protected String country;
    protected String zipCode;
    protected String phoneNumber;
    protected String faxNumber;
}
