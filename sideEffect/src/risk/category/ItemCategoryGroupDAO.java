// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemCategoryGroupDAO.java

package risk.category;

import abstraction.CRUDable;
import abstraction.IDValuePair;
import java.util.List;

public interface ItemCategoryGroupDAO
    extends CRUDable
{

    public abstract long count(IDValuePair idvaluepair);

    public abstract List list(Class class1, int i, int j);

    public abstract int clearJunction(String s, String s1, long l);

    public abstract Object create(Object obj);

    public abstract boolean delete(Object obj);

    public abstract List ItemCateogoryList(Class class1, int i, int j, String s, String s1);

    public abstract boolean chechkType(Class class1);

    public abstract Object update(Object obj);

    public abstract List backupList(Object obj, long l);

    public abstract Object readBack(Class class1, long l);

    public abstract long count(Object obj);

    public abstract List list(Object obj);

    public abstract boolean startDateCheck(Object obj, long l);

    public abstract long itemCategoryGroupId(long l);

    public abstract List groupBackList(String s, String s1, long l);

    public abstract List groupLatestBackList(String s, String s1, long l);

    public abstract Long getCategoryType(long l);
}
