// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JuncProductLot.java

package properties.product;

import abstraction.IDValuePair;

public interface JuncProductLot
    extends IDValuePair
{

    public abstract long getId();

    public abstract void setId(long l);

    public abstract long getLot_id();

    public abstract void setLot_id(long l);

    public abstract int getMeddev_item_seq();

    public abstract void setMeddev_item_seq(int i);
}
