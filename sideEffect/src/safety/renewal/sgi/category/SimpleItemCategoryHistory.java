// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItemCategoryHistory.java

package safety.renewal.sgi.category;

import abstraction.AbstractIDValuePair;
import java.util.Date;
import safety.renewal.sgi.item.Item1;

// Referenced classes of package safety.renewal.sgi.category:
//            ItemCategoryHistory

public class SimpleItemCategoryHistory extends AbstractIDValuePair
    implements ItemCategoryHistory
{

    public SimpleItemCategoryHistory()
    {
    }

    public long getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(long categoryId)
    {
        this.categoryId = categoryId;
    }

    public long getItemId()
    {
        return itemId;
    }

    public void setItemId(long itemId)
    {
        this.itemId = itemId;
    }

    public Item1 getSimpleItem()
    {
        return simpleItem;
    }

    public void setSimpleItem(Item1 simpleItem)
    {
        this.simpleItem = simpleItem;
    }

    public long getId()
    {
        return id;
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

    public void setId(long newID)
    {
        id = newID;
    }

    public String getPropertyValue()
    {
        return null;
    }

    public void setPropertyValue(String s)
    {
    }

    public long getParentId()
    {
        return getItemId();
    }

    public void setParentId(long parentId)
    {
        setItemId(parentId);
    }

    private long id;
    private Date activeFrom;
    private Date lastModified;
    private int historyType;
    private String manager;
    private String historyDescription;
    private Item1 simpleItem;
    private long categoryId;
    private long itemId;
}
