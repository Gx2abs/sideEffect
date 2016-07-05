// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleDeviceDAO.java

package device;

import abstraction.IDValuePair;
import foreign.Meb_item;
import java.io.PrintStream;
import java.util.List;
import org.hibernate.*;

// Referenced classes of package device:
//            DeviceDAO

public class SimpleDeviceDAO
    implements DeviceDAO
{

    public SimpleDeviceDAO()
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

    public void test()
    {
        sessionFactory.getCurrentSession().createCriteria(getClass());
    }

    public List list(Meb_item searchExample)
    {
        List l = null;
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(Meb_item.class);
            l = criteria.list();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println((new StringBuilder("List<Meb_item>.size() : ")).append(l.size()).toString());
        return l;
    }

    public IDValuePair create(IDValuePair t)
    {
        return null;
    }

    public IDValuePair read(Class clas, Object obj)
    {
        return null;
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

    public List list(IDValuePair t, int page, int maxResults)
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

    public IDValuePair merge(IDValuePair t)
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

    SessionFactory sessionFactory;
}
