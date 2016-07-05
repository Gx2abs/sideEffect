// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemCategoryGroup.java

package risk.category;

import abstraction.IDValuePair;
import java.util.*;
import properties.SimpleIsInUse;

// Referenced classes of package risk.category:
//            SimpleItemCategoryGroupType

public interface ItemCategoryGroup
    extends IDValuePair
{

    public abstract long getId();

    public abstract void setId(long l);

    public abstract String getPropertyValue();

    public abstract void setPropertyValue(String s);

    public abstract int getType();

    public abstract void setType(int i);

    public abstract SimpleItemCategoryGroupType getTypeStatus();

    public abstract void setTypeStatus(SimpleItemCategoryGroupType simpleitemcategorygrouptype);

    public abstract Date getLastModified();

    public abstract void setLastModified(Date date);

    public abstract int getIsInUse();

    public abstract void setIsInUse(int i);

    public abstract int getIsFromExcel();

    public abstract void setIsFromExcel(int i);

    public abstract SimpleIsInUse getUseStatus();

    public abstract void setUseStatus(SimpleIsInUse simpleisinuse);

    public abstract Set getItemCategoryGroupHistory();

    public abstract void setItemCategoryGroupHistory(Set set);

    public abstract List getItemCategoryGroup();

    public abstract void setItemCategoryGroup(List list);
}
