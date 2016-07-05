// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleImportOutputDAO.java

package risk.item.importoutput;

import abstraction.IDValuePair;
import foreign.Meb_item;
import java.io.PrintStream;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.*;

// Referenced classes of package risk.item.importoutput:
//            ImportOutputDAO

public class SimpleImportOutputDAO
    implements ImportOutputDAO
{

    public SimpleImportOutputDAO()
    {
    }

    private Session getCurrentSession()
    {
        Session session = null;
        try
        {
            System.out.println("getCurrentSession");
            session = sessionFactory.getCurrentSession();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(session == null)
        {
            System.out.println("openSession");
            session = sessionFactory.openSession();
        }
        return session;
    }

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public IDValuePair read(Class clas, long id)
    {
        String signature = "SimpleImportOutputDAO.list(Class)";
        System.out.println((new StringBuilder("reading with id : ")).append(id).toString());
        Session session = getCurrentSession();
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
        Session session = getCurrentSession();
        session.save(t);
        return t;
    }

    public Object create(Object t)
    {
        Session session = getCurrentSession();
        session.save(t);
        return t;
    }

    public IDValuePair update(IDValuePair t)
    {
        Session session = getCurrentSession();
        session.update(t);
        return t;
    }

    public boolean delete(IDValuePair t)
    {
        Session session = getCurrentSession();
        session.delete(t);
        return true;
    }

    public boolean delete(Object t)
    {
        Session session = getCurrentSession();
        session.delete(t);
        return true;
    }

    public List list(IDValuePair t)
    {
        String signature = "SimpleImportOutputDAO.list(Class)";
        List l = null;
        Session session = getCurrentSession();
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
        String signature = "SimpleImportOutputDAO.list(Class)";
        List l = null;
        System.out.println((new StringBuilder(String.valueOf(signature))).append(" for class : ").append(t.getName()).toString());
        Session session = getCurrentSession();
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
        String signature = "SimpleImportOutputDAO.list(Class)";
        Class target = null;
        List l = null;
        return l;
    }

    public List list(Class t, int page, int maxResults)
    {
        List l = null;
        try
        {
            Session session = getCurrentSession();
            Criteria c = session.createCriteria(t);
            c.setFirstResult((page - 1) * maxResults);
            c.setMaxResults(maxResults);
            c.addOrder(Order.desc("id"));
            l = c.list();
        }
        catch(Exception e)
        {
            System.out.println("SimpleImportOutputDAO.list(Class, page, size).exception : ");
            e.printStackTrace();
        }
        return l;
    }

    public List list(IDValuePair t, int page, int maxResults)
    {
        List l = null;
        try
        {
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
            System.out.println("SimpleImportOutputDAO.list(Class, page, size).exception : ");
            e.printStackTrace();
        }
        return l;
    }

    public List list(Object t, int page, int maxResults)
    {
        List l = null;
        try
        {
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
            System.out.println("SimpleImportOutputDAO.list(Class, page, size).exception : ");
            e.printStackTrace();
        }
        return l;
    }

    public long count(IDValuePair t)
    {
        Session session = getCurrentSession();
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
        Session session = getCurrentSession();
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

    public List listEntpImportOutput(IDValuePair t, int page, int maxResults, String searchColumn, String searchKeyword)
    {
        String criteria[] = searchColumn.split("\\.");
        if(criteria[0].isEmpty())
            criteria[0] = "mea_item";
        if(criteria[0].equals("item"))
            criteria[0] = "mea_item";
        if(searchColumn.isEmpty())
            searchColumn = "mea_item.class_kor_name";
        Session session = sessionFactory.getCurrentSession();
        List list = null;
        Criteria c = session.createCriteria(t.getClass()).addOrder(Order.desc("id")).createCriteria("item", "item").createCriteria(criteria[0], criteria[0]).add(Restrictions.like(searchColumn, searchKeyword, MatchMode.ANYWHERE)).setFirstResult((page - 1) * maxResults).setMaxResults(maxResults);
        list = c.list();
        return list;
    }

    public long listEntpImportOutputCount(IDValuePair t, String searchColumn, String searchKeyword)
    {
        String criteria[] = searchColumn.split("\\.");
        if(criteria[0].isEmpty())
            criteria[0] = "mea_item";
        if(criteria[0].equals("item"))
            criteria[0] = "mea_item";
        if(searchColumn.isEmpty())
            searchColumn = "mea_item.class_kor_name";
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass()).addOrder(Order.desc("id")).createCriteria("item", "item").createCriteria(criteria[0], criteria[0]).add(Restrictions.like(searchColumn, searchKeyword, MatchMode.ANYWHERE));
        c.setProjection(Projections.count("id"));
        long count = ((Long)c.uniqueResult()).longValue();
        return count;
    }

    private SessionFactory sessionFactory;
}
