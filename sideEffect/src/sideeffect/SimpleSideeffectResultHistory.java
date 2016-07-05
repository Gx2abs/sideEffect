// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSideeffectResultHistory.java

package sideeffect;

import abstraction.AbstractIDValuePair;
import java.util.Date;

// Referenced classes of package sideeffect:
//            SideeffectResultHistory

public class SimpleSideeffectResultHistory extends AbstractIDValuePair
    implements SideeffectResultHistory
{

    public SimpleSideeffectResultHistory()
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

    public Date getActiveFrom()
    {
        return activeFrom;
    }

    public void setActiveFrom(Date activeFrom)
    {
        this.activeFrom = activeFrom;
    }

    public int getHistoryType()
    {
        return historyType;
    }

    public void setHistoryType(int historyType)
    {
        this.historyType = historyType;
    }

    public String getHistoryDescription()
    {
        return historyDescription;
    }

    public void setHistoryDescription(String historyDescription)
    {
        this.historyDescription = historyDescription;
    }

    public String getManager()
    {
        return manager;
    }

    public void setManager(String manager)
    {
        this.manager = manager;
    }

    public Date getLastModified()
    {
        return lastModified;
    }

    public void setLastModified(Date lastModified)
    {
        this.lastModified = lastModified;
    }

    public long getParentId()
    {
        return 0L;
    }

    public void setParentId(long l)
    {
    }

    private long id;
    private Date activeFrom;
    private int historyType;
    private String historyDescription;
    private String manager;
    private Date lastModified;
}
