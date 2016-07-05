// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JuncSafetyComponentCode.java

package safety;


public class JuncSafetyComponentCode
{

    public JuncSafetyComponentCode()
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

    public long getComponent_id()
    {
        return component_id;
    }

    public void setComponent_id(long component_id)
    {
        this.component_id = component_id;
    }

    private long id;
    private long report_id;
    private long component_id;
}
