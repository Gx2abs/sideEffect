// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleSystemLogDAO.java

package system.log;

import abstraction.IDValuePair;
import abstraction.SimpleCRUDable;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.*;

// Referenced classes of package system.log:
//            SystemLogDAO

public class SimpleSystemLogDAO extends SimpleCRUDable
    implements SystemLogDAO
{

    public SimpleSystemLogDAO()
    {
    }

    public IDValuePair create(IDValuePair t)
    {
        Session session = sessionFactory.getCurrentSession();
        session.save(t);
        return t;
    }

    public IDValuePair update(IDValuePair t)
    {
        return null;
    }

    public boolean delete(IDValuePair t)
    {
        return false;
    }

    public List list(IDValuePair t)
    {
        return null;
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

    public Long count(Object t)
    {
        Long count = Long.valueOf(0L);
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(t.getClass());
        c.setProjection(Projections.count("id"));
        Example e = Example.create(t);
        e.enableLike(MatchMode.ANYWHERE);
        e.excludeZeroes();
        e.ignoreCase();
        c.add(e);
        if(c.uniqueResult() != null)
            count = (Long)c.uniqueResult();
        return count;
    }
}
