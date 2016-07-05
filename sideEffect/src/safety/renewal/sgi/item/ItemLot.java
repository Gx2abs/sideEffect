// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemLot.java

package safety.renewal.sgi.item;

import abstraction.IDValuePair;

public interface ItemLot
    extends IDValuePair
{

    public abstract int getLot_id();

    public abstract void setLot_id(int i);

    public abstract long getId();

    public abstract void setId(long l);

    public abstract String getLot_no();

    public abstract void setLot_no(String s);
}
