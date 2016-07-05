// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSideeffectCause.java

package sideeffect;


// Referenced classes of package sideeffect:
//            SideeffectCause

public class SimpleSideeffectCause
    implements SideeffectCause
{

    public SimpleSideeffectCause()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getProperty_value()
    {
        return property_value;
    }

    public void setProperty_value(String property_value)
    {
        this.property_value = property_value;
    }

    private int id;
    private String property_value;
}
