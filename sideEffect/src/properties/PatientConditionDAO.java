// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatientConditionDAO.java

package properties;

import java.util.List;
import org.hibernate.SessionFactory;

// Referenced classes of package properties:
//            SimplePatientCondition

public interface PatientConditionDAO
{

    public abstract SessionFactory getSessionFactory();

    public abstract void setSessionFactory(SessionFactory sessionfactory);

    public abstract List list(Object obj);

    public abstract List list(SimplePatientCondition simplepatientcondition);
}
