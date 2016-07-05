// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleAddressDAO.java

package address;

import java.io.PrintStream;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.*;

// Referenced classes of package address:
//            AddressDAO

public class SimpleAddressDAO
    implements AddressDAO
{

    public SimpleAddressDAO()
    {
        sessionFactory = null;
    }

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public List search(Class t, String key)
    {
        List list = null;
        System.out.println((new StringBuilder("[SimpleAddressDAO].Class<T> t  ")).append(t).toString());
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t);
        c.add(Restrictions.like("doro_nm", (new StringBuilder("%")).append(key).append("%").toString()));
        System.out.println((new StringBuilder("[SimpleAddressDAO].search.key  ")).append(key).toString());
        System.out.println("[SimpleAddressDAO].search.query  ");
        System.out.println((new StringBuilder("[SimpleAddressDAO].search.c  ")).append(c.toString()).toString());
        list = c.list();
        if(list != null && list.size() > 0)
            System.out.println((new StringBuilder("[SimpleAddressDAO].search().size() : ")).append(list.size()).toString());
        else
            System.out.println("[SimpleAddressDAO].search() is empty ! ");
        return list;
    }

    public List search(Object t, String key, int pg, int maxResults)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.setFirstResult((pg - 1) * maxResults);
        c.setMaxResults(maxResults);
        c.addOrder(Order.desc("id"));
        c.add(Restrictions.like("doro_nm", key, MatchMode.ANYWHERE));
        l = c.list();
        return l;
    }

    public long count(Object t, String key)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.add(Restrictions.like("doro_nm", key, MatchMode.ANYWHERE));
        c.setProjection(Projections.count("id"));
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    private SessionFactory sessionFactory;
}
