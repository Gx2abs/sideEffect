// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncItemCategoryGroup.java

package risk.category;


// Referenced classes of package risk.category:
//            JuncItemCategoryGroup, SimpleItemCategoryGroup

public class SimpleJuncItemCategoryGroup
    implements JuncItemCategoryGroup
{

    public SimpleJuncItemCategoryGroup()
    {
    }

    public long getCategory_id()
    {
        return category_id;
    }

    public void setCategory_id(long category_id)
    {
        this.category_id = category_id;
    }

    public long getGroup_id()
    {
        return group_id;
    }

    public void setGroup_id(long group_id)
    {
        this.group_id = group_id;
    }

    public int getIsFromExcel()
    {
        return isFromExcel;
    }

    public void setIsFromExcel(int isFromExcel)
    {
        this.isFromExcel = isFromExcel;
    }

    public SimpleItemCategoryGroup getItemCategoryGroup()
    {
        return itemCategoryGroup;
    }

    public void setItemCategoryGroup(SimpleItemCategoryGroup itemCategoryGroup)
    {
        this.itemCategoryGroup = itemCategoryGroup;
    }

    private long category_id;
    private long group_id;
    private int isFromExcel;
    private SimpleItemCategoryGroup itemCategoryGroup;
}
