// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SideeffectResult.java

package sideeffect;

import abstraction.IDValuePair;
import java.util.Date;
import java.util.Set;
import properties.SimpleIsInUse;

public interface SideeffectResult
    extends IDValuePair
{

    public abstract long getId();

    public abstract void setId(long l);

    public abstract String getPropertyValue();

    public abstract void setPropertyValue(String s);

    public abstract String getSeverityName();

    public abstract void setSeverityName(String s);

    public abstract int getValue();

    public abstract void setValue(int i);

    public abstract Date getStartDate();

    public abstract void setStartDate(Date date);

    public abstract Date getLastModified();

    public abstract void setLastModified(Date date);

    public abstract int getIsFromExcel();

    public abstract void setIsFromExcel(int i);

    public abstract int getIsInUse();

    public abstract void setIsInUse(int i);

    public abstract SimpleIsInUse getUseStatus();

    public abstract void setUseStatus(SimpleIsInUse simpleisinuse);

    public abstract long getParentId();

    public abstract void setParentId(long l);

    public abstract Integer getDepthLevel();

    public abstract void setDepthLevel(Integer integer);

    public abstract Set getSeverityHistory();

    public abstract void setSeverityHistory(Set set);
}
