// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SystemLogDAO.java

package system.log;

import abstraction.CRUDable;
import org.hibernate.SessionFactory;

public interface SystemLogDAO
    extends CRUDable
{

    public abstract void setSessionFactory(SessionFactory sessionfactory);

    public abstract SessionFactory getSessionFactory();

    public abstract Long count(Object obj);
}
