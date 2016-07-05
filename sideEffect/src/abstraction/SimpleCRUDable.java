// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleCRUDable.java

package abstraction;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.*;

// Referenced classes of package abstraction:
//            CRUDable, IDValuePair

public abstract class SimpleCRUDable
    implements CRUDable
{

    public SimpleCRUDable()
    {
    }

    private Session getCurrentSession()
    {
        Session session = null;
        try
        {
            session = sessionFactory.getCurrentSession();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(session == null)
            session = sessionFactory.openSession();
        return session;
    }

    public IDValuePair read(Class clas, long id)
    {
        String signature = "SimplePropertiesDAO.list(Class)";
        System.out.println((new StringBuilder("reading with id : ")).append(id).toString());
        Session session = getCurrentSession();
        Criteria c = session.createCriteria(clas);
        Long l = new Long(id);
        long intId = l.intValue();
        c.add(Restrictions.eq("id", Long.valueOf(intId)));
        IDValuePair found = (IDValuePair)c.uniqueResult();
        return found;
    }

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public List list(IDValuePair t, int page, int maxResults)
    {
        List l = null;
        try
        {
            System.out.println("SimpleCRUDable.<T extends IDValuePair> List<T> list(T t, int page, int maxResults)");
            Session session = getCurrentSession();
            Criteria c = session.createCriteria(t.getClass());
            Example e = Example.create(t);
            e.enableLike(MatchMode.ANYWHERE);
            e.excludeZeroes();
            e.ignoreCase();
            c.add(e);
            c.setFirstResult((page - 1) * maxResults);
            c.setMaxResults(maxResults);
            c.addOrder(Order.desc("id"));
            l = c.list();
        }
        catch(Exception e)
        {
            System.out.println("SimplePropertiesDAO.list(Class, page, size).exception : ");
            e.printStackTrace();
        }
        return l;
    }

    public long count(IDValuePair t)
    {
        long count = 0L;
        Session session = getCurrentSession();
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

    public List list(Object t, int page, int maxResults)
    {
        List l = null;
        try
        {
            System.out.println("SimpleCRUDable.<T> List<T> list(T t, int page, int maxResults)");
            Session session = getCurrentSession();
            Criteria c = session.createCriteria(t.getClass());
            Example e = Example.create(t);
            e.enableLike(MatchMode.ANYWHERE);
            e.excludeZeroes();
            e.ignoreCase();
            c.add(e);
            c.setFirstResult((page - 1) * maxResults);
            c.setMaxResults(maxResults);
            c.addOrder(Order.desc("id"));
            l = c.list();
        }
        catch(Exception e)
        {
            System.out.println("SimplePropertiesDAO.list(Class, page, size).exception : ");
            e.printStackTrace();
        }
        return l;
    }

    public Object create(Object t)
    {
        System.out.println("abstraction.SimpleCrudable.create(T t)");
        Session session = sessionFactory.getCurrentSession();
        Serializable s = session.save(t);
        t = session.load(t.getClass(), s);
        if(t == null)
            System.out.println("abstraction.SimpleCrudable.create(T t). loaded a null t");
        return t;
    }

    protected SessionFactory sessionFactory;
}
