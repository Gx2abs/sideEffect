// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AttachmentDAO.java

package system.io;

import java.io.File;
import java.util.List;
import org.hibernate.SessionFactory;

// Referenced classes of package system.io:
//            Attachment, SimpleJuncRepoerAttachment

public interface AttachmentDAO
{

    public abstract SessionFactory getSessionFactory();

    public abstract void setSessionFactory(SessionFactory sessionfactory);

    public abstract File add(File file);

    public abstract int add(Attachment attachment);

    public abstract int fileDelete(int i);

    public abstract int juncReportAttachmentDelete(int i);

    public abstract List get(int i, int j);

    public abstract int add(SimpleJuncRepoerAttachment simplejuncrepoerattachment);

    public abstract double getUploadFileSize();
}
