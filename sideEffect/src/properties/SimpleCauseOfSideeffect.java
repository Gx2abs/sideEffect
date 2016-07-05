// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleCauseOfSideeffect.java

package properties;

import abstraction.IDValuePair;

// Referenced classes of package properties:
//            CauseOfSideeffect

public class SimpleCauseOfSideeffect
    implements IDValuePair, CauseOfSideeffect
{

    public SimpleCauseOfSideeffect()
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

    private long id;
    private String propertyValue;
}
