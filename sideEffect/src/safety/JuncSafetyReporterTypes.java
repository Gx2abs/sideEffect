// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JuncSafetyReporterTypes.java

package safety;

import abstraction.IDValuePair;

public class JuncSafetyReporterTypes
    implements IDValuePair
{

    public JuncSafetyReporterTypes()
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

    public int getReporter_type()
    {
        return reporter_type;
    }

    public void setReporter_type(int reporter_type)
    {
        this.reporter_type = reporter_type;
    }

    public int getSafety_report()
    {
        return safety_report;
    }

    public void setSafety_report(int safety_report)
    {
        this.safety_report = safety_report;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public String getPropertyValue()
    {
        return null;
    }

    public void setPropertyValue(String s)
    {
    }

    private long id;
    private int reporter_type;
    private int safety_report;
    private int type;
}
