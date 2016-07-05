// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncItemLot.java

package safety.renewal.sgi.item;

import abstraction.AbstractIDValuePair;

// Referenced classes of package safety.renewal.sgi.item:
//            JuncItemLot

public class SimpleJuncItemLot extends AbstractIDValuePair
    implements JuncItemLot
{

    public SimpleJuncItemLot()
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

    public long getLot_id()
    {
        return lot_id;
    }

    public void setLot_id(long lot_id)
    {
        this.lot_id = lot_id;
    }

    public int getItem_id()
    {
        return item_id;
    }

    public void setItem_id(int item_id)
    {
        this.item_id = item_id;
    }

    private long id;
    private long lot_id;
    private int item_id;
}
