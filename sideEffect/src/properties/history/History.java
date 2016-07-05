// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   History.java

package properties.history;

import abstraction.IDValuePair;
import java.util.Date;

public interface History
    extends IDValuePair
{

    public abstract long getParentId();

    public abstract void setParentId(long l);

    public abstract long getId();

    public abstract void setId(long l);

    public abstract Date getActiveFrom();

    public abstract void setActiveFrom(Date date);

    public abstract Date getLastModified();

    public abstract void setLastModified(Date date);

    public abstract int getHistoryType();

    public abstract void setHistoryType(int i);

    public abstract String getManager();

    public abstract void setManager(String s);

    public abstract String getHistoryDescription();

    public abstract void setHistoryDescription(String s);
}
