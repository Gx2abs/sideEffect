// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompanyDAO.java

package safety.renewal.company;

import abstraction.CRUDable;
import abstraction.IDValuePair;
import java.util.List;

public interface CompanyDAO
    extends CRUDable
{

    public abstract long count(IDValuePair idvaluepair);

    public abstract List list(Class class1, int i, int j);

    public abstract int clearJunction(String s, String s1, long l);

    public abstract long countCompany(String s, String s1);
}
