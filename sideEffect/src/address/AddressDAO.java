// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AddressDAO.java

package address;

import java.util.List;
import org.hibernate.SessionFactory;

public interface AddressDAO
{

    public abstract SessionFactory getSessionFactory();

    public abstract void setSessionFactory(SessionFactory sessionfactory);

    public abstract List search(Class class1, String s);

    public abstract List search(Object obj, String s, int i, int j);

    public abstract long count(Object obj, String s);
}
