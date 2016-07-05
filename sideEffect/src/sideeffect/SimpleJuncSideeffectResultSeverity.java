// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncSideeffectResultSeverity.java

package sideeffect;


// Referenced classes of package sideeffect:
//            JuncSideEffectResultSeverity

public class SimpleJuncSideeffectResultSeverity
    implements JuncSideEffectResultSeverity
{

    public SimpleJuncSideeffectResultSeverity()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getSeverity_id()
    {
        return severity_id;
    }

    public void setSeverity_id(int severity_id)
    {
        this.severity_id = severity_id;
    }

    public int getHistory_id()
    {
        return history_id;
    }

    public void setHistory_id(int history_id)
    {
        this.history_id = history_id;
    }

    private int id;
    private int severity_id;
    private int history_id;
}
