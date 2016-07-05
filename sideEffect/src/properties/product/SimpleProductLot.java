// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleProductLot.java

package properties.product;

import abstraction.AbstractIDValuePair;

// Referenced classes of package properties.product:
//            ProductLot

public class SimpleProductLot extends AbstractIDValuePair
    implements ProductLot
{

    public SimpleProductLot()
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

    public int getMeddev_item_seq()
    {
        return meddev_item_seq;
    }

    public void setMeddev_item_seq(int meddev_item_seq)
    {
        this.meddev_item_seq = meddev_item_seq;
    }

    public String getLot_no()
    {
        return lot_no;
    }

    public void setLot_no(String lot_no)
    {
        this.lot_no = lot_no;
    }

    private long id;
    private int meddev_item_seq;
    private String lot_no;
}
