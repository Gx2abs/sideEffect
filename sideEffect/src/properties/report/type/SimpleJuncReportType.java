// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncReportType.java

package properties.report.type;

import abstraction.AbstractIDValuePair;

// Referenced classes of package properties.report.type:
//            JuncReportType

public class SimpleJuncReportType extends AbstractIDValuePair
    implements JuncReportType
{

    public SimpleJuncReportType()
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

    public int getReport_Type_id()
    {
        return report_Type_id;
    }

    public void setReport_Type_id(int report_Type_id)
    {
        this.report_Type_id = report_Type_id;
    }

    public int getHistory_id()
    {
        return history_id;
    }

    public void setHistory_id(int history_id)
    {
        this.history_id = history_id;
    }

    private long id;
    private int report_Type_id;
    private int history_id;
}
