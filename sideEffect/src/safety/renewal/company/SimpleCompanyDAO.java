// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleCompanyDAO.java

package safety.renewal.company;

import abstraction.IDValuePair;
import foreign.Meb_item;
import java.io.PrintStream;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.*;
import properties.SimplePropertiesDAO;

// Referenced classes of package safety.renewal.company:
//            CompanyDAO, SimpleCompany1

public class SimpleCompanyDAO
    implements CompanyDAO
{

    public SimpleCompanyDAO()
    {
    }

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public IDValuePair read(Class clas, long id)
    {
        String signature = "SimpleCompanyDAO.list(Class)";
        System.out.println((new StringBuilder("reading with id : ")).append(id).toString());
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(clas);
        Long l = new Long(id);
        long intId = l.intValue();
        c.add(Restrictions.eq("id", Long.valueOf(intId)));
        IDValuePair found = (IDValuePair)c.uniqueResult();
        return found;
    }

    public IDValuePair merge(IDValuePair t)
    {
        return null;
    }

    public IDValuePair create(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        session.save(t);
        return t;
    }

    public IDValuePair update(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        session.update(t);
        return t;
    }

    public boolean delete(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        session.delete(t);
        return true;
    }

    public List list(IDValuePair t)
    {
        String signature = "SimplePropertiesDAO.list(Class)";
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

    public List list(Class t)
    {
        String signature = "SimpleCompanyDAO.list(Class)";
        List l = null;
        System.out.println((new StringBuilder(String.valueOf(signature))).append(" for class : ").append(t.getName()).toString());
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t);
        l = c.list();
        if(l != null && l.size() > 0)
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" list.size() : ").append(l.size()).toString());
        else
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" null lis !! ").toString());
        return l;
    }

    public List list(Meb_item meb_item)
    {
        String signature = "SimpleCompanyDAO.list(Class)";
        Class target = null;
        List l = null;
        System.out.println((new StringBuilder(String.valueOf(signature))).append(" for class : ").append(Meb_item.class.getName()).toString());
        properties.SimplePropertiesDAO.Property aproperty[];
        int j = (aproperty = properties.SimplePropertiesDAO.Property.values()).length;
        for(int i = 0; i < j; i++)
        {
            properties.SimplePropertiesDAO.Property p = aproperty[i];
            p.name().equalsIgnoreCase(Meb_item.class.getName());
            target = Meb_item.class;
        }

        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(target);
        l = c.list();
        if(l != null && l.size() > 0)
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" list.size() : ").append(l.size()).toString());
        else
            System.out.println((new StringBuilder(String.valueOf(signature))).append(" null lis !! ").toString());
        return l;
    }

    public List list(Class t, int page, int maxResults)
    {
        List l = null;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Criteria c = session.createCriteria(t);
            c.setFirstResult((page - 1) * maxResults);
            c.setMaxResults(maxResults);
            c.addOrder(Order.desc("id"));
            l = c.list();
        }
        catch(Exception e)
        {
            System.out.println("SimpleCompanyDAO.list(Class, page, size).exception : ");
            e.printStackTrace();
        }
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
            System.out.println("SimpleCompanyDAO.list(Class, page, size).exception : ");
            e.printStackTrace();
        }
        return l;
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
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    public int clearJunction(String table, String masterColumn, long masterId)
    {
        int affected = -1;
        Session session = sessionFactory.getCurrentSession();
        String queryString = (new StringBuilder("DELETE FROM ")).append(table).append(" where ").append(masterColumn).append(" = :masterId").toString();
        Query query = session.createSQLQuery(queryString);
        query.setParameter("masterId", Long.valueOf(masterId));
        affected = query.executeUpdate();
        return affected;
    }

    public IDValuePair readCompany(Class clas, long id)
    {
        return null;
    }

    public IDValuePair readProduct(Class clas, long id)
    {
        return null;
    }

    public long countCompany(String s1, String s2)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleCompany1.class);
        c.setProjection(Projections.count("id"));
        c.add(Restrictions.like("meddev_entp_no", s1));
        c.add(Restrictions.like("cob_flag_code", s2));
        System.out.println("query..");
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    private SessionFactory sessionFactory;
}
