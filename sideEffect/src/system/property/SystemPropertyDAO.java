// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SystemPropertyDAO.java

package system.property;

import abstraction.CountSupport;
import abstraction.IDValuePair;
import java.util.List;
import org.hibernate.SessionFactory;

public interface SystemPropertyDAO
    extends CountSupport
{

    public abstract IDValuePair create(IDValuePair idvaluepair);

    public abstract IDValuePair update(IDValuePair idvaluepair);

    public abstract boolean delete(IDValuePair idvaluepair);

    public abstract List list(IDValuePair idvaluepair);

    public abstract List list(IDValuePair idvaluepair, int i, int j);

    public abstract List list(Class class1);

    public abstract List list(Class class1, int i, int j);

    public abstract IDValuePair read(Class class1, long l);

    public abstract IDValuePair readCompany(Class class1, long l);

    public abstract IDValuePair readProduct(Class class1, long l);

    public abstract IDValuePair merge(IDValuePair idvaluepair);

    public abstract SessionFactory getSessionFactory();

    public abstract void setSessionFactory(SessionFactory sessionfactory);
}
