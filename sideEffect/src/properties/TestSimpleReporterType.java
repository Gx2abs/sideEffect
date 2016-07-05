// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TestSimpleReporterType.java

package properties;


// Referenced classes of package properties:
//            ReporterType

public class TestSimpleReporterType
    implements ReporterType
{

    public TestSimpleReporterType()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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

    public Integer getType()
    {
        return null;
    }

    public void setType(Integer integer)
    {
    }

    private long id;
    private String value;
    private String name;
}
