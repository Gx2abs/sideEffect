// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItem.java

package properties.item;

import abstraction.Pageable;
import java.lang.reflect.Field;
import java.util.List;

// Referenced classes of package properties.item:
//            Item

public class SimpleItem
    implements Item, Pageable
{

    public SimpleItem()
    {
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getFirstResult()
    {
        return firstResult;
    }

    public void setFirstResult(int firstResult)
    {
        this.firstResult = firstResult;
    }

    public int getMaxResults()
    {
        return maxResults;
    }

    public void setMaxResults(int maxResults)
    {
        this.maxResults = maxResults;
    }

    public List getItemHistory()
    {
        return itemHistory;
    }

    public void setItemHistory(List itemHistory)
    {
        this.itemHistory = itemHistory;
    }

    public String getItemCode()
    {
        return itemCode;
    }

    public void setItemCode(String itemCode)
    {
        this.itemCode = itemCode;
    }

    public Integer getGrade()
    {
        return grade;
    }

    public void setGrade(Integer grade)
    {
        this.grade = grade;
    }

    public String getItemNameKr()
    {
        return itemNameKr;
    }

    public void setItemNameKr(String itemNameKr)
    {
        this.itemNameKr = itemNameKr;
    }

    public String getItemNameEn()
    {
        return itemNameEn;
    }

    public void setItemNameEn(String itemNameEn)
    {
        this.itemNameEn = itemNameEn;
    }

    public String getItemDesc()
    {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc)
    {
        this.itemDesc = itemDesc;
    }

    public Integer getIsTraceable()
    {
        return isTraceable;
    }

    public void setIsTraceable(Integer isTraceable)
    {
        this.isTraceable = isTraceable;
    }

    public Integer getIsInUse()
    {
        return isInUse;
    }

    public void setIsInUse(Integer isInUse)
    {
        this.isInUse = isInUse;
    }

    public Integer getCodeType()
    {
        return codeType;
    }

    public void setCodeType(Integer codeType)
    {
        this.codeType = codeType;
    }

    public Integer getLevelDepth()
    {
        return levelDepth;
    }

    public void setLevelDepth(Integer levelDepth)
    {
        this.levelDepth = levelDepth;
    }

    public Integer getParentId()
    {
        return parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
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

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        Field fields[] = getClass().getDeclaredFields();
        try
        {
            Field afield[];
            int j = (afield = fields).length;
            for(int i = 0; i < j; i++)
            {
                Field f = afield[i];
                sb.append((new StringBuilder(String.valueOf(f.getName()))).append("=").append(f.get(this)).append("\n").toString());
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private long id;
    private String propertyValue;
    private Integer parentId;
    private Integer levelDepth;
    private Integer grade;
    private String itemNameKr;
    private String itemNameEn;
    private String itemDesc;
    private Integer isTraceable;
    private Integer isInUse;
    private Integer codeType;
    private String itemCode;
    private int firstResult;
    private int maxResults;
    private int page;
    private List itemHistory;
}
