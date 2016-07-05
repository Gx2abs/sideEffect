// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSystemPropertyDAO.java

package system.property;

import abstraction.CRUDable;
import abstraction.IDValuePair;
import java.io.PrintStream;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.*;

// Referenced classes of package system.property:
//            SystemPropertyDAO

public class SimpleSystemPropertyDAO
    implements CRUDable, SystemPropertyDAO
{

    public SimpleSystemPropertyDAO()
    {
    }

    public IDValuePair create(IDValuePair t)
    {
        return null;
    }

    public IDValuePair update(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        session.update(t);
        return null;
    }

    public boolean delete(IDValuePair t)
    {
        return false;
    }

    public List list(IDValuePair t)
    {
        String signature = "SimplePropertiesDAO.list(Class)";
        System.out.println(signature);
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

    public List list(IDValuePair t, int page, int maxResults)
    {
        List l = null;
        try
        {
            Session session = sessionFactory.getCurrentSession();
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

    public List list(Class t)
    {
        return null;
    }

    public List list(Class t, int page, int maxResults)
    {
        return null;
    }

    public IDValuePair read(Class clas, long id)
    {
        System.out.println((new StringBuilder("reading with id : ")).append(id).toString());
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(clas);
        Long l = new Long(id);
        long intId = l.intValue();
        c.add(Restrictions.eq("id", Long.valueOf(intId)));
        IDValuePair found = (IDValuePair)c.uniqueResult();
        return found;
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

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public long count(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.setProjection(Projections.count("id"));
        Example e = Example.create(t);
        e.enableLike(MatchMode.ANYWHERE);
        e.excludeZeroes();
        e.ignoreCase();
        c.add(e);
        Object tmpObj = c.uniqueResult();
        long count = 0L;
        if(tmpObj != null)
            count = ((Long)tmpObj).longValue();
        return count;
    }

    private SessionFactory sessionFactory;
}
