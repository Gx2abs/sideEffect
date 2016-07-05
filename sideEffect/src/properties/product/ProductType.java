// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProductType.java

package properties.product;

import abstraction.IDValuePair;

public interface ProductType
    extends IDValuePair
{

    public abstract int getMeddev_item_seq();

    public abstract void setMeddev_item_seq(int i);

    public abstract long getId();

    public abstract void setId(long l);

    public abstract String getType_name();

    public abstract void setType_name(String s);

    public abstract int getDelete_yn();

    public abstract void setDelete_yn(int i);
}
