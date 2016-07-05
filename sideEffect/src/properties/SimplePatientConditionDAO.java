// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimplePatientConditionDAO.java

package properties;

import java.util.List;
import org.hibernate.*;

// Referenced classes of package properties:
//            PatientConditionDAO, SimplePatientCondition

public class SimplePatientConditionDAO
    implements PatientConditionDAO
{

    public SimplePatientConditionDAO()
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

    public List list(Object obj)
    {
        return null;
    }

    public List list(SimplePatientCondition pCondition)
    {
        List l = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SimplePatientCondition.class);
        l = c.list();
        return l;
    }

    private SessionFactory sessionFactory;
}
