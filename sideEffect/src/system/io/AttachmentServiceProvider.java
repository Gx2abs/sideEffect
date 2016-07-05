// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AttachmentServiceProvider.java

package system.io;


// Referenced classes of package system.io:
//            AttachmentDAO

public interface AttachmentServiceProvider
{

    public abstract AttachmentDAO getAttachmentDAO();

    public abstract void setAttachmentDAO(AttachmentDAO attachmentdao);

    public abstract double getUploadSize();
}
