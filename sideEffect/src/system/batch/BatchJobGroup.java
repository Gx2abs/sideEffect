// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BatchJobGroup.java

package system.batch;

import abstraction.IDValuePair;
import java.util.Date;

public interface BatchJobGroup
    extends IDValuePair
{

    public abstract Long getServiceId();

    public abstract void setServiceId(Long long1);

    public abstract Long getIsActive();

    public abstract void setIsActive(Long long1);

    public abstract Date getActiveFrom();

    public abstract void setActiveFrom(Date date);

    public abstract String getPhysicalSourcePath();

    public abstract void setPhysicalSourcePath(String s);

    public abstract String getLogicalSourcePath();

    public abstract void setLogicalSourcePath(String s);
}
