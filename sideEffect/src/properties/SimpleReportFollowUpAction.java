// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleReportFollowUpAction.java

package properties;


// Referenced classes of package properties:
//            ReportFollowUpAction

public class SimpleReportFollowUpAction
    implements ReportFollowUpAction
{

    public SimpleReportFollowUpAction()
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

    public String getProperty_value()
    {
        return property_value;
    }

    public void setProperty_value(String property_value)
    {
        this.property_value = property_value;
    }

    public String getPropertyValue()
    {
        return null;
    }

    public void setPropertyValue(String s)
    {
    }

    long id;
    String property_value;
}
