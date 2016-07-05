// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemType.java

package safety.renewal.sgi.item;

import abstraction.IDValuePair;

public interface ItemType
    extends IDValuePair
{

    public abstract int getItem_id();

    public abstract void setItem_id(int i);

    public abstract long getId();

    public abstract void setId(long l);

    public abstract String getType_name();

    public abstract void setType_name(String s);

    public abstract int getDelete_yn();

    public abstract void setDelete_yn(int i);
}
