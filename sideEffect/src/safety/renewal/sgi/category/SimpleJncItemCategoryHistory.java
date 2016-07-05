// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJncItemCategoryHistory.java

package safety.renewal.sgi.category;

import abstraction.IDValuePair;

public class SimpleJncItemCategoryHistory
    implements IDValuePair
{

    public SimpleJncItemCategoryHistory()
    {
    }

    public long getHistoryId()
    {
        return historyId;
    }

    public void setHistoryId(long historyId)
    {
        this.historyId = historyId;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(long categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getPropertyValue()
    {
        return null;
    }

    public void setPropertyValue(String s)
    {
    }

    private long historyId;
    private long id;
    private long categoryId;
}
