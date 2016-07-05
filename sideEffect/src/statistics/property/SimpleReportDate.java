// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleReportDate.java

package statistics.property;

import java.io.Serializable;

// Referenced classes of package statistics.property:
//            ReportDate

public class SimpleReportDate
    implements ReportDate, Serializable
{

    public SimpleReportDate()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getReport_year()
    {
        return report_year;
    }

    public void setReport_year(String report_year)
    {
        this.report_year = report_year;
    }

    public String getReport_month()
    {
        return report_month;
    }

    public void setReport_month(String report_month)
    {
        this.report_month = report_month;
    }

    private Integer id;
    private String report_year;
    private String report_month;
}
