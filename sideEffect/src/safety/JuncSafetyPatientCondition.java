// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JuncSafetyPatientCondition.java

package safety;


public class JuncSafetyPatientCondition
{

    public JuncSafetyPatientCondition()
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

    public long getCondition_id()
    {
        return condition_id;
    }

    public void setCondition_id(long condition_id)
    {
        this.condition_id = condition_id;
    }

    private long id;
    private long report_id;
    private long condition_id;
}
