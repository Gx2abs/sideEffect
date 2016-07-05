// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JuncSideEffectReportTypes2.java

package properties;


public class JuncSideEffectReportTypes2
{

    public JuncSideEffectReportTypes2()
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

    public int getReport_id()
    {
        return report_id;
    }

    public void setReport_id(int report_id)
    {
        this.report_id = report_id;
    }

    public int getReport_type_id()
    {
        return report_type_id;
    }

    public void setReport_type_id(int report_type_id)
    {
        this.report_type_id = report_type_id;
    }

    private long id;
    private int report_id;
    private int report_type_id;
}
