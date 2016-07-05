// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSafetyReportTypeDate.java

package safety;

import java.util.Date;
import statistics.property.SimpleReportDate;

// Referenced classes of package safety:
//            SafetyReportTYpeDate

public class SimpleSafetyReportTypeDate
    implements SafetyReportTYpeDate
{

    public SimpleSafetyReportTypeDate()
    {
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

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getReport_id()
    {
        return report_id;
    }

    public void setReport_id(long report_id)
    {
        this.report_id = report_id;
    }

    public long getJunc_id()
    {
        return junc_id;
    }

    public void setJunc_id(long junc_id)
    {
        this.junc_id = junc_id;
    }

    public Date getReport_date()
    {
        return report_date;
    }

    public void setReport_date(Date report_date)
    {
        this.report_date = report_date;
    }

    public int getReport_type_id()
    {
        return report_type_id;
    }

    public void setReport_type_id(int report_type_id)
    {
        this.report_type_id = report_type_id;
    }

    public SimpleReportDate getReportYM()
    {
        return reportYM;
    }

    public void setReportYM(SimpleReportDate reportYM)
    {
        this.reportYM = reportYM;
    }

    private long id;
    private long report_id;
    private long junc_id;
    private Date report_date;
    private int report_type_id;
    private String report_year;
    private String report_month;
    private SimpleReportDate reportYM;
}
