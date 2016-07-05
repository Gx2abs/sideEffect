// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JuncSideEffectCause.java

package sideeffect;


public class JuncSideEffectCause
{

    public JuncSideEffectCause()
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

    public int getCause_id()
    {
        return cause_id;
    }

    public void setCause_id(int cause_id)
    {
        this.cause_id = cause_id;
    }

    private long id;
    private int report_id;
    private int cause_id;
}
