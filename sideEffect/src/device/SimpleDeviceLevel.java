// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleDeviceLevel.java

package device;

import abstraction.IDValuePair;

// Referenced classes of package device:
//            DeviceLevel

public class SimpleDeviceLevel
    implements IDValuePair, DeviceLevel
{

    public SimpleDeviceLevel()
    {
    }

    public long getId()
    {
        return ID;
    }

    public void setId(long iD)
    {
        ID = iD;
    }

    public String getPropertyValue()
    {
        return value;
    }

    public void setPropertyValue(String value)
    {
        this.value = value;
    }

    private long ID;
    private String value;
}
