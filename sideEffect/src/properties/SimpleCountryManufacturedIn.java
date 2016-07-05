// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleCountryManufacturedIn.java

package properties;

import abstraction.IDValuePair;

// Referenced classes of package properties:
//            CountryManufacturedIn

public class SimpleCountryManufacturedIn
    implements IDValuePair, CountryManufacturedIn
{

    public SimpleCountryManufacturedIn()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId(long newID)
    {
        id = newID;
    }

    public String getPropertyValue()
    {
        return value;
    }

    public void setPropertyValue(String newValue)
    {
        value = newValue;
    }

    private long id;
    private String value;
}
