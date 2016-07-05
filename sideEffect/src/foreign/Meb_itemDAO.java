// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Meb_itemDAO.java

package foreign;

import abstraction.IDValuePair;
import java.util.List;
import org.hibernate.SessionFactory;

public interface Meb_itemDAO
{

    public abstract SessionFactory getSessionFactory();

    public abstract void setSessionFactory(SessionFactory sessionfactory);

    public abstract List list();

    public abstract List namedList();

    public abstract List namedList2();

    public abstract List getManufImportEntpNameList(int i, int j);

    public abstract List item_list(int i, int j, String s, int k, String s1);

    public abstract List item_search(int i, int j, String s, int k, String s1);

    public abstract List search(int i, int j, int k, String s);

    public abstract List entpNameSearchList(int i, int j, String s);

    public abstract List manufImportEntpNameList(int i, int j);

    public abstract List manufImportEntpNameSearch1(int i, int j, String s);

    public abstract List manufImportEntpNameSearch2(int i, int j, String s, String s1);

    public abstract List manufImportEntpNameSearch3(int i, int j, String s, String s1, String s2);

    public abstract long entpNameSearchCount(IDValuePair idvaluepair, String s);

    public abstract long getManufCount(IDValuePair idvaluepair);

    public abstract long manufCount(IDValuePair idvaluepair);

    public abstract long manufCount1(IDValuePair idvaluepair, String s);

    public abstract long manufCount2(IDValuePair idvaluepair, String s, String s1);

    public abstract long manufCount3(IDValuePair idvaluepair, String s, String s1, String s2);

    public abstract long item_listCount(IDValuePair idvaluepair, int i, int j, String s, int k, String s1);

    public abstract long item_searchCount(IDValuePair idvaluepair, int i, int j, String s, String s1, int k, String s2);

    public abstract long searchCount(IDValuePair idvaluepair, String s, int i);

    public abstract List listPopupMeaCompany(String s);

    public abstract List listPopupMeaClassNo(String s);
}
