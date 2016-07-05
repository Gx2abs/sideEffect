// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleAttachmentDAO.java

package system.io;

import java.io.File;
import java.io.PrintStream;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

// Referenced classes of package system.io:
//            AttachmentDAO, Attachment, SimpleJuncRepoerAttachment, SimpleAttachment

public class SimpleAttachmentDAO
    implements AttachmentDAO
{

    public SimpleAttachmentDAO()
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

    public File add(File f)
    {
        return null;
    }

    public int add(Attachment attachment)
    {
        System.out.println("[SimpleAttachmentDAO].int add()  ");
        int result = -1;
        try
        {
            Session s = sessionFactory.openSession();
            Transaction transaction = s.getTransaction();
            transaction.begin();
            result = ((Integer)s.save(attachment)).intValue();
            transaction.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public List get(int articleId, int type)
    {
        List list = null;
        Session s = sessionFactory.openSession();
        Criteria c = s.createCriteria(Attachment.class);
        c.add(Restrictions.eq("table_id", Integer.valueOf(articleId)));
        c.add(Restrictions.eq("type", Integer.valueOf(type)));
        list = c.list();
        if(list != null && list.size() > 0)
            System.out.println((new StringBuilder("SimpleAttachmentDAO.get().size() : ")).append(list.size()).toString());
        else
            System.out.println("SimpleAttachmentDAO.etg() is empty ! ");
        return list;
    }

    public int add(SimpleJuncRepoerAttachment juncRepoerAttachment)
    {
        System.out.println("[SimpleAttachmentDAO].int add(JuncRepoerAttachment)  ");
        int result = -1;
        long returnId = -1L;
        try
        {
            Session s = sessionFactory.openSession();
            Transaction transaction = s.getTransaction();
            transaction.begin();
            returnId = ((Long)s.save(juncRepoerAttachment)).longValue();
            result = (int)returnId;
            transaction.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public int fileDelete(int id)
    {
        int result = -1;
        try
        {
            Session s = sessionFactory.openSession();
            Transaction transaction = s.getTransaction();
            transaction.begin();
            SimpleAttachment attachment = new SimpleAttachment();
            attachment.setId(id);
            System.out.println("[SimpleSideeffectReportDAO].fileDelete.query  ");
            s.delete(attachment);
            transaction.commit();
            result = 1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public int juncReportAttachmentDelete(int id)
    {
        int result = -1;
        try
        {
            Session s = sessionFactory.openSession();
            Transaction transaction = s.getTransaction();
            transaction.begin();
            result = Integer.valueOf(s.createQuery("DELETE FROM system.io.JuncRepoerAttachment WHERE ATTACHMENT_ID = ? ").setInteger(0, id).executeUpdate()).intValue();
            System.out.println("[SimpleSideeffectReportDAO].juncReportAttachmentDelete.query  ");
            transaction.commit();
            result = 1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public double getUploadFileSize()
    {
        double returnVal = 0.0D;
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT PROPERTY_VALUE FROM SYSTEM_PROPERTY WHERE ID = 1");
        Object tmp = query.uniqueResult();
        System.out.println(tmp.getClass());
        if(tmp != null && (tmp instanceof String))
        {
            String l = (String)tmp;
            returnVal = Double.parseDouble(l);
        } else
        {
            System.out.println("SimpleAttachmentDAO.getUploadFileSize().No match found.");
        }
        try
        {
            session.close();
        }
        catch(Exception exception) { }
        return returnVal;
    }

    private SessionFactory sessionFactory;
}
