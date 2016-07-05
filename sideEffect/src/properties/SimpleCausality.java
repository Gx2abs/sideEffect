// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleCausality.java

package properties;


// Referenced classes of package properties:
//            Causality

public class SimpleCausality
    implements Causality
{

    public SimpleCausality()
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
        return value;
    }

    public void setPropertyValue(String value)
    {
        this.value = value;
    }

    public int getIntId()
    {
        return (int)id;
    }

    public void setIntId(int id)
    {
        this.id = id;
    }

    private long id;
    private String value;
    private int intId;
}
