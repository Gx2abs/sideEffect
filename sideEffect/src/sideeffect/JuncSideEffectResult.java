// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JuncSideEffectResult.java

package sideeffect;


public class JuncSideEffectResult
{

    public JuncSideEffectResult()
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

    public long getResult_id()
    {
        return result_id;
    }

    public void setResult_id(long result_id)
    {
        this.result_id = result_id;
    }

    private long id;
    private long report_id;
    private long result_id;
}
