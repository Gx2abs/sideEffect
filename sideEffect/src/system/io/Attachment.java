// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Attachment.java

package system.io;


public interface Attachment
{

    public abstract int getId();

    public abstract void setId(int i);

    public abstract String getFullURL();

    public abstract void setFullURL(String s);

    public abstract String getPhysicalPath();

    public abstract void setPhysicalPath(String s);

    public abstract String getLogicalPath();

    public abstract void setLogicalPath(String s);

    public abstract String getExtension();

    public abstract void setExtension(String s);

    public abstract String getName();

    public abstract void setName(String s);

    public abstract String getRelativeLogicalPath();

    public abstract void setRelativeLogicalPath(String s);

    public abstract int getTable_id();

    public abstract void setTable_id(int i);

    public abstract int getType();

    public abstract void setType(int i);

    public abstract String getFileName();

    public abstract void setFileName(String s);

    public abstract String getLogical_name();

    public abstract void setLogical_name(String s);
}
