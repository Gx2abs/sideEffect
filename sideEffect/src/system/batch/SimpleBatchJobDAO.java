// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleBatchJobDAO.java

package system.batch;

import abstraction.IDValuePair;
import abstraction.SimpleCRUDable;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.*;

// Referenced classes of package system.batch:
//            BatchJobDAO

public class SimpleBatchJobDAO extends SimpleCRUDable
    implements BatchJobDAO
{

    public SimpleBatchJobDAO()
    {
    }

    public IDValuePair update(IDValuePair t)
    {
        return null;
    }

    public boolean delete(IDValuePair t)
    {
        return false;
    }

    public List list(Class t)
    {
        return null;
    }

    public List list(Class t, int page, int maxResults)
    {
        System.out.println("SimpleBatchJobDAO  <T extends IDValuePair> List<T> list(Class<T> t, int page,int maxResults)");
        return null;
    }

    public IDValuePair readCompany(Class clas, long id)
    {
        return null;
    }

    public IDValuePair readProduct(Class clas, long id)
    {
        return null;
    }

    public IDValuePair merge(IDValuePair t)
    {
        return null;
    }

    public List list(IDValuePair t)
    {
        String signature = "SimpleBatchJobDAO.list(Class)";
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.setMaxResults(10);
        c.setFirstResult(1);
        l = c.list();
        if(l != null && l.size() > 0)
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" list.size() : ").append(l.size()).toString());
        else
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" null lis !! ").toString());
        return l;
    }

    public List list(Object t)
    {
        String signature = "SimpleBatchJobDAO.list(T t )";
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.setMaxResults(10);
        c.setFirstResult(1);
        l = c.list();
        if(l != null && l.size() > 0)
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" list.size() : ").append(l.size()).toString());
        else
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" null lis !! ").toString());
        return l;
    }

    public long count(Object t)
    {
        long count = 0L;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.setProjection(Projections.count("id"));
        Example e = Example.create(t);
        e.enableLike(MatchMode.ANYWHERE);
        e.excludeZeroes();
        e.ignoreCase();
        c.add(e);
        if(c.uniqueResult() != null)
            count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public IDValuePair create(IDValuePair t)
    {
        System.out.println("<T extends IDValuePair> SimpleBatchJobDAO.create(T t)");
        return null;
    }

    public Object createAndReturn(Object t)
    {
        System.out.println("SimpleBatchJobDAO.createAndReturn(T t)");
        Session session = sessionFactory.getCurrentSession();
        Serializable s = session.save(t);
        t = session.load(t.getClass(), s);
        if(t == null)
            System.out.println("SimpleBatchJobDAO.create(T t). loaded a null t");
        return t;
    }
}
