// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemCategoryDAO.java

package safety.renewal.sgi.category;

import abstraction.CRUDable;
import abstraction.IDValuePair;
import java.util.List;
import org.hibernate.SessionFactory;

// Referenced classes of package safety.renewal.sgi.category:
//            SimpleItemCategory

public interface ItemCategoryDAO
    extends CRUDable
{

    public abstract SessionFactory getSessionFactory();

    public abstract void setSessionFactory(SessionFactory sessionfactory);

    public abstract List list();

    public abstract List namedList();

    public abstract List namedList2();

    public abstract List getManufImportEntpNameList(int i, int j);

    public abstract List manufImportEntpNameList(int i, int j);

    public abstract List manufImportEntpNameSearch1(int i, int j, String s);

    public abstract List manufImportEntpNameSearch2(int i, int j, String s, String s1);

    public abstract List manufImportEntpNameSearch3(int i, int j, String s, String s1, String s2);

    public abstract long getManufCount(IDValuePair idvaluepair);

    public abstract long manufCount(IDValuePair idvaluepair);

    public abstract long manufCount1(IDValuePair idvaluepair, String s);

    public abstract long manufCount2(IDValuePair idvaluepair, String s, String s1);

    public abstract long manufCount3(IDValuePair idvaluepair, String s, String s1, String s2);

    public abstract List safetyMeaCompany(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7, String s8);

    public abstract List sideeffectMeaCompany(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7, String s8);

    public abstract List safetyMeaClassNo(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7, String s8);

    public abstract List sideeffectMeaClassNo(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7, String s8);

    public abstract IDValuePair getParent(long l);

    public abstract int clearJunction(String s, String s1, long l);

    public abstract List list(Class class1, int i, int j);

    public abstract List list(IDValuePair idvaluepair, int i, int j);

    public abstract long count(IDValuePair idvaluepair);

    public abstract List listItemCategoryNo(SimpleItemCategory simpleitemcategory, int i);

    public abstract Object create(Object obj, boolean flag);

    public abstract List listMeaClassNo(int i, int j);

    public abstract IDValuePair readMeaClassNo(SimpleItemCategory simpleitemcategory);
}
