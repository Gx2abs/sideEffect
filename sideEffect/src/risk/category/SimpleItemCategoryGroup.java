// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItemCategoryGroup.java

package risk.category;

import java.util.*;
import properties.SimpleIsInUse;

// Referenced classes of package risk.category:
//            ItemCategoryGroup, SimpleItemCategoryGroupType

public class SimpleItemCategoryGroup
    implements ItemCategoryGroup
{

    public SimpleItemCategoryGroup()
    {
        itemCategoryGroupHistory = new HashSet();
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getPropertyValue()
    {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue)
    {
        this.propertyValue = propertyValue;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public SimpleItemCategoryGroupType getTypeStatus()
    {
        return typeStatus;
    }

    public void setTypeStatus(SimpleItemCategoryGroupType typeStatus)
    {
        this.typeStatus = typeStatus;
    }

    public Date getLastModified()
    {
        return lastModified;
    }

    public void setLastModified(Date lastModified)
    {
        this.lastModified = lastModified;
    }

    public int getIsInUse()
    {
        return isInUse;
    }

    public void setIsInUse(int isInUse)
    {
        this.isInUse = isInUse;
    }

    public int getIsFromExcel()
    {
        return isFromExcel;
    }

    public void setIsFromExcel(int isFromExcel)
    {
        this.isFromExcel = isFromExcel;
    }

    public SimpleIsInUse getUseStatus()
    {
        return useStatus;
    }

    public void setUseStatus(SimpleIsInUse useStatus)
    {
        this.useStatus = useStatus;
    }

    public Set getItemCategoryGroupHistory()
    {
        return itemCategoryGroupHistory;
    }

    public void setItemCategoryGroupHistory(Set itemCategoryGroupHistory)
    {
        this.itemCategoryGroupHistory = itemCategoryGroupHistory;
    }

    public List getItemCategoryGroup()
    {
        return itemCategoryGroup;
    }

    public void setItemCategoryGroup(List itemCategoryGroup)
    {
        this.itemCategoryGroup = itemCategoryGroup;
    }

    private long id;
    private String propertyValue;
    private int type;
    private SimpleItemCategoryGroupType typeStatus;
    private Date lastModified;
    private int isInUse;
    private int isFromExcel;
    private SimpleIsInUse useStatus;
    private Set itemCategoryGroupHistory;
    private List itemCategoryGroup;
}
