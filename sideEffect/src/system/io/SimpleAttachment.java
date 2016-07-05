// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleAttachment.java

package system.io;


// Referenced classes of package system.io:
//            Attachment

public class SimpleAttachment
    implements Attachment
{

    public SimpleAttachment()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFullURL()
    {
        return fullURL;
    }

    public void setFullURL(String fullURL)
    {
        this.fullURL = fullURL;
    }

    public String getPhysicalPath()
    {
        return physicalPath;
    }

    public void setPhysicalPath(String physicalPath)
    {
        this.physicalPath = physicalPath;
    }

    public String getLogicalPath()
    {
        return logicalPath;
    }

    public void setLogicalPath(String logicalPath)
    {
        this.logicalPath = logicalPath;
    }

    public String getExtension()
    {
        return extension;
    }

    public void setExtension(String extension)
    {
        this.extension = extension;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getRelativeLogicalPath()
    {
        return relativeLogicalPath;
    }

    public void setRelativeLogicalPath(String relativeLogicalPath)
    {
        this.relativeLogicalPath = relativeLogicalPath;
    }

    public int getTable_id()
    {
        return table_id;
    }

    public void setTable_id(int table_id)
    {
        this.table_id = table_id;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getLogical_name()
    {
        return logical_name;
    }

    public void setLogical_name(String logical_name)
    {
        this.logical_name = logical_name;
    }

    private int id;
    private String fullURL;
    private String physicalPath;
    private String logicalPath;
    private String relativeLogicalPath;
    private String extension;
    private String name;
    private int table_id;
    private int type;
    private String fileName;
    private String logical_name;
}
