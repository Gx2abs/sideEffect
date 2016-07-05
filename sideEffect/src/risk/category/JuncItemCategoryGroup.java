// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JuncItemCategoryGroup.java

package risk.category;


// Referenced classes of package risk.category:
//            SimpleItemCategoryGroup

public interface JuncItemCategoryGroup
{

    public abstract long getCategory_id();

    public abstract void setCategory_id(long l);

    public abstract long getGroup_id();

    public abstract void setGroup_id(long l);

    public abstract int getIsFromExcel();

    public abstract void setIsFromExcel(int i);

    public abstract SimpleItemCategoryGroup getItemCategoryGroup();

    public abstract void setItemCategoryGroup(SimpleItemCategoryGroup simpleitemcategorygroup);
}
