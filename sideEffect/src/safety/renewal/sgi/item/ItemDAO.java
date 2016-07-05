// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemDAO.java

package safety.renewal.sgi.item;

import abstraction.CRUDable;
import abstraction.IDValuePair;
import java.util.List;

public interface ItemDAO
    extends CRUDable
{

    public abstract long count(IDValuePair idvaluepair);

    public abstract List listEntpItem(int i, int j, String s);

    public abstract Long listEntpItemCnt(String s);

    public abstract List listCategoryItemNo(int i, int j, String s);

    public abstract Long listCategoryItemCntNo(String s);

    public abstract List listCategoryItem(int i, int j, String s);

    public abstract Long listCategoryItemCnt(String s);

    public abstract List listItem(Class class1, int i, int j);

    public abstract int clearJunction(String s, String s1, long l);

    public abstract long countProduct(String s);

    public abstract long count(Object obj);
}
