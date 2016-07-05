// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleReportStatus.java

package properties;

import java.io.PrintStream;

// Referenced classes of package properties:
//            ReportStatus

public class SimpleReportStatus
    implements ReportStatus
{

    public SimpleReportStatus()
    {
        System.out.println("Building a SimpleReportStatus");
    }

    public long getId()
    {
        return id;
    }

    public void setId(long iD)
    {
        try
        {
            id = iD;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
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
