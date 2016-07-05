// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeviceDAO.java

package device;

import abstraction.CRUDable;
import foreign.Meb_item;
import java.util.List;
import org.hibernate.SessionFactory;

public interface DeviceDAO
    extends CRUDable
{

    public abstract SessionFactory getSessionFactory();

    public abstract void setSessionFactory(SessionFactory sessionfactory);

    public abstract void test();

    public abstract List list(Meb_item meb_item);
}
