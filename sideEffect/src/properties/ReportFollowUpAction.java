// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReportFollowUpAction.java

package properties;

import abstraction.IDValuePair;

public interface ReportFollowUpAction
    extends IDValuePair
{

    public abstract long getId();

    public abstract void setId(long l);

    public abstract String getProperty_value();

    public abstract void setProperty_value(String s);
}
