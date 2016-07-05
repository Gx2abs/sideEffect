// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleItemLot.java

package safety.renewal.sgi.item;

import abstraction.AbstractIDValuePair;

// Referenced classes of package safety.renewal.sgi.item:
//            ItemLot

public class SimpleItemLot extends AbstractIDValuePair
    implements ItemLot
{

    public SimpleItemLot()
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

    public int getLot_id()
    {
        return lot_id;
    }

    public void setLot_id(int lot_id)
    {
        this.lot_id = lot_id;
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
    private int lot_id;
    private String lot_no;
}
