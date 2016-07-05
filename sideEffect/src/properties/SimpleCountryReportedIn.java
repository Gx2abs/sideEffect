// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleCountryReportedIn.java

package properties;

import abstraction.IDValuePair;

// Referenced classes of package properties:
//            CountryReportedIn

public class SimpleCountryReportedIn
    implements IDValuePair, CountryReportedIn
{

    public SimpleCountryReportedIn()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId(long iD)
    {
        id = iD;
    }

    public String getPropertyValue()
    {
        return propertyValue;
    }

    public void setPropertyValue(String value)
    {
        propertyValue = value;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    private long id;
    private String propertyValue;
    private Integer type;
}
