// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleGender.java

package properties;

import abstraction.IDValuePair;

// Referenced classes of package properties:
//            Gender

public class SimpleGender
    implements IDValuePair, Gender
{

    public SimpleGender()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setID(int iD)
    {
        id = iD;
    }

    public String getPropertyValue()
    {
        return value;
    }

    public void setPropertyValue(String value)
    {
        this.value = value;
    }

    public void setId(long newID)
    {
        id = newID;
    }

    public String getName()
    {
        return getPropertyValue();
    }

    private long id;
    private String value;
}
