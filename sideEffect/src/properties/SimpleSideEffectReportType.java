// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSideEffectReportType.java

package properties;


// Referenced classes of package properties:
//            SideEffectReportType

public class SimpleSideEffectReportType
    implements SideEffectReportType
{

    public SimpleSideEffectReportType()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getPropertyValue()
    {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue)
    {
        this.propertyValue = propertyValue;
    }

    private long id;
    private String propertyValue;
}
