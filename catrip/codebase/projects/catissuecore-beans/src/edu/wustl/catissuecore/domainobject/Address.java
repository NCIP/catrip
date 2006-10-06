// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package edu.wustl.catissuecore.domainobject;


public interface Address
{

    public abstract Long getId();

    public abstract void setId(Long long1);

    public abstract String getStreet();

    public abstract void setStreet(String s);

    public abstract String getCity();

    public abstract void setCity(String s);

    public abstract String getState();

    public abstract void setState(String s);

    public abstract String getCountry();

    public abstract void setCountry(String s);

    public abstract String getZipCode();

    public abstract void setZipCode(String s);

    public abstract String getPhoneNumber();

    public abstract void setPhoneNumber(String s);

    public abstract String getFaxNumber();

    public abstract void setFaxNumber(String s);
}
