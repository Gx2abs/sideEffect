// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncRepoerAttachment.java

package system.io;


// Referenced classes of package system.io:
//            JuncRepoerAttachment

public class SimpleJuncRepoerAttachment
    implements JuncRepoerAttachment
{

    public SimpleJuncRepoerAttachment()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getTable_id()
    {
        return table_id;
    }

    public void setTable_id(int table_id)
    {
        this.table_id = table_id;
    }

    public int getAttachment_id()
    {
        return attachment_id;
    }

    public void setAttachment_id(int attachment_id)
    {
        this.attachment_id = attachment_id;
    }

    public String getPropertyValue()
    {
        return null;
    }

    public void setPropertyValue(String s)
    {
    }

    public void setId(int i)
    {
    }

    private long id;
    private int type;
    private int table_id;
    private int attachment_id;
}
