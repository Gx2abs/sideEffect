// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleJuncProductLot.java

package properties.product;

import abstraction.AbstractIDValuePair;

// Referenced classes of package properties.product:
//            JuncProductLot

public class SimpleJuncProductLot extends AbstractIDValuePair
    implements JuncProductLot
{

    public SimpleJuncProductLot()
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

    public int getMeddev_item_seq()
    {
        return meddev_item_seq;
    }

    public void setMeddev_item_seq(int meddev_item_seq)
    {
        this.meddev_item_seq = meddev_item_seq;
    }

    private long id;
    private long lot_id;
    private int meddev_item_seq;
}
