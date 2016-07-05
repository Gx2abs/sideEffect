// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SideeffectReport.java

package report;

import java.util.List;
import org.hibernate.SessionFactory;

// Referenced classes of package report:
//            SimpleSideeffectReport

public interface SideeffectReport
{

    public abstract SessionFactory getSessionFactory();

    public abstract void setSessionFactory(SessionFactory sessionfactory);

    public abstract List list(SimpleSideeffectReport simplesideeffectreport);
}
