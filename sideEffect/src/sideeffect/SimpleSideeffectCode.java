// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSideeffectCode.java

package sideeffect;


// Referenced classes of package sideeffect:
//            SideeffectCode

public class SimpleSideeffectCode
    implements SideeffectCode
{

    public SimpleSideeffectCode()
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

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    private int id;
    private String value;
}
