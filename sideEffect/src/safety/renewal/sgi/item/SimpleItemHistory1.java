// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItemHistory1.java

package safety.renewal.sgi.item;

import abstraction.AbstractIDValuePair;
import java.util.Date;

// Referenced classes of package safety.renewal.sgi.item:
//            ItemHistory1

public class SimpleItemHistory1 extends AbstractIDValuePair
    implements ItemHistory1
{

    public SimpleItemHistory1()
    {
    }

    public long getProductId()
    {
        return productId;
    }

    public void setProductId(long productId)
    {
        this.productId = productId;
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

    public Date getLastModified()
    {
        return lastModified;
    }

    public void setLastModified(Date lastModified)
    {
        this.lastModified = lastModified;
    }

    public int getHistoryType()
    {
        return historyType;
    }

    public void setHistoryType(int historyType)
    {
        this.historyType = historyType;
    }

    public String getManager()
    {
        return manager;
    }

    public void setManager(String manager)
    {
        this.manager = manager;
    }

    public String getHistoryDescription()
    {
        return historyDescription;
    }

    public void setHistoryDescription(String historyDescription)
    {
        this.historyDescription = historyDescription;
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
    private Date lastModified;
    private int historyType;
    private String manager;
    private String historyDescription;
    private long productId;
}
