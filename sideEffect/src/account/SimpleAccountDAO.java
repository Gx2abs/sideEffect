// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleAccountDAO.java

package account;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

// Referenced classes of package account:
//            AccountDAO, SimpleAccount

public class SimpleAccountDAO
    implements AccountDAO
{

    public SimpleAccountDAO()
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

    public boolean authenticate(String id, String password)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimpleAccount.class);
        c.add(Restrictions.eq("id", id));
        return false;
    }

    private SessionFactory sessionFactory;
}
