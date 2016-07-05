// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncSafetyReportType2.java

package safety;


// Referenced classes of package safety:
//            JuncSafetyReportType2

public class SimpleJuncSafetyReportType2
    implements JuncSafetyReportType2
{

    public SimpleJuncSafetyReportType2()
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

    public long getReport_id()
    {
        return report_id;
    }

    public void setReport_id(long report_id)
    {
        this.report_id = report_id;
    }

    public long getReport_type_id()
    {
        return report_type_id;
    }

    public void setReport_type_id(long report_type_id)
    {
        this.report_type_id = report_type_id;
    }

    private long id;
    private long report_id;
    private long report_type_id;
}
